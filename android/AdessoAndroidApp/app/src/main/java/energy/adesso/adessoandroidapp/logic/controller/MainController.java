package energy.adesso.adessoandroidapp.logic.controller;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import energy.adesso.adessoandroidapp.logic.model.AzureResponse;
import energy.adesso.adessoandroidapp.logic.model.Pair;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Issue;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Meter;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Reading;
import energy.adesso.adessoandroidapp.logic.model.exception.*;
import energy.adesso.adessoandroidapp.logic.model.identifiable.User;


public class MainController {
  private static PersistenceController persistence;

  private static boolean usePersistence = true;
  private static Long uId;

  // Private because of static class
  public MainController() {

  }

  public static void sendIssue(Issue issue) throws NetworkException, CredentialException {
    String json = issue.serialize();
    String url = "/api/issues";
    NetworkController.post(url, json);
  }

  public static void loadSharedPreferences(SharedPreferences prefs) {
    // init Persistence
    persistence = new PersistenceController(prefs);
    if (usePersistence) {
      String username = persistence.load("username");
      String password = persistence.load("password");
      NetworkController.setCredentials(username, password);
    }
  }


  /**
   * gets all readings associated with the meter with the given ID
   *
   * @param meterId the meter to get readings for
   * @return the list of readings
   * @throws NetworkException
   * @throws CredentialException when not logged in
   */
  public static List<Reading> getReadings(Long meterId) throws NetworkException, CredentialException {
    String request = "/api/meters/" + meterId + "/readings";
    String response = NetworkController.get(request);
    Type type = new TypeToken<List<Reading>>() {
    }.getType();
    return new Gson().fromJson(response, type);
  }

  public static List<Reading> getReadingsPaging(Long meterId) throws NetworkException, CredentialException {
    String request = "/api/users/me/readings/" + meterId;
    List<Reading> readingList = new PagingHelper<Reading>().getAll(request);
    return readingList;
  }


  /**
   * tries to log in with the given username and password.
   *
   * @param username
   * @param password
   * @throws NetworkException
   */
  public static void login(String username, String password) throws NetworkException, CredentialException {
    if (username == null || password == null)
      throw new CredentialException();

    // Send
    NetworkController.setCredentials(username, password);
    String reString = NetworkController.get("/api/login");

    User user = User.deserialize(reString);
    uId = user.getId();

    // Save persistently
    if (usePersistence) {
      persistence.save("username", username);
      persistence.save("password", password);
      persistence.save("uId", Long.toString(uId));
    }

  }

  public static void logOut() throws NetworkException, AdessoException {
    NetworkController.setCredentials(null, null);
    uId = null;
    if (usePersistence) {
      persistence.delete("username");
      persistence.delete("password");
      persistence.delete("uId");
    }
  }

  /**
   * analyzes an image for readerNumber and current reading
   *
   * @param image the image to analyze
   * @return a Tuple of the affected Meter, and scanned Value
   * @throws CredentialException when not logged in
   */
  public static Pair<Meter, String> azureAnalyze(Bitmap image) throws NetworkException, CredentialException {
    String url = "/api/picture";

    String sendString = toBase64(image);

    // casting answerString to pair of mid, value
    String answerString = NetworkController.postImage(url, sendString);
    Type castType = new AzureResponse(1L,"").getClass();
    AzureResponse response = new Gson().fromJson(answerString, castType);

    Meter m = getMeter(response.mid);

    // returning the meter and the proposed entryvalue
    return new Pair<Meter, String>(m, response.value);
  }

  private static Meter getMeter(Long mid) throws NetworkException, CredentialException {
    String url = "" + mid; // TODO:
    String json = NetworkController.get(url);
    return (Meter) Meter.deserialize(json);
  }

  /**
   * Sets the ip to send requests to
   *
   * @param newAddress the ip tp connect to
   * @throws NetworkException when the Network is faulty
   */
  public static void setServer(String newAddress) {
    if (usePersistence) {
      if (newAddress == null)
        persistence.delete("address");
      else
        persistence.save("address", newAddress);
    }
    NetworkController.setAddress(newAddress);
  }

  public static void createReading(Long mId, String value) throws NetworkException, CredentialException {
    String url = "/api/meters/" + mId + "/readings";
    Reading reading = new Reading(null, mId, uId, value);
    String readingString = reading.serialize();
    NetworkController.post(url, readingString);
  }

  /**
   * Fetches all Meters associated with the logged in User.
   * These Meters are shallow, which means their Readings-List is still empty.
   *
   * @return A List of all Meters associated with the User.
   * @throws NetworkException    when the Network is faulty
   * @throws CredentialException when the User is not logged in
   */

  public static List<Meter> getOverview() throws NetworkException, CredentialException {
    String request = "/api/users/me/meters";
    String response = NetworkController.get(request);
    Type type = new TypeToken<List<Meter>>() {
    }.getType();
    return new Gson().fromJson(response, type);
  }

  public static List<Meter> getOverviewPaged() throws NetworkException, CredentialException {
    String request = "/api/users/me/meters";
    return new PagingHelper<Meter>().getAll(request);
  }

  /**
   * Converts a bitmap to Base64 String
   */
  private static String toBase64(Bitmap bitmap) {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
    byte[] byteArray = byteArrayOutputStream.toByteArray();
    return Base64.encodeToString(byteArray, Base64.DEFAULT);
  }

  public static void correctReading(Reading reading) throws NetworkException, CredentialException {
    String url = "/api/meters/<mid>/readings/" + reading.getId();
    String json = reading.serialize();
    NetworkController.put(url, json);
  }

  public static void updateMeterName(Meter meter) throws NetworkException, CredentialException {
    String url = "/api/meters/" + meter.getId();
    String json = meter.serialize();
    NetworkController.put(url, json);
  }

  public static boolean isLoggedIn() {
    if (NetworkController.getUsername() == null)
      return false;

    // check if persistent state and local copy are identical
    boolean meLoggedIn = persistence.load("username") != null;
    boolean netLoggedIn = NetworkController.isLoggedIn();
    if (usePersistence && (meLoggedIn!=netLoggedIn))
      // Logged in information must be synced between parts of the controller
      throw new IllegalStateException();
    return NetworkController.isLoggedIn();
  }

  public static void setUsePersistence(boolean usePersistence) {
    MainController.usePersistence = usePersistence;
  }
}
