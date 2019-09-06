// TODO: throws and param annotation

package energy.adesso.adessoandroidapp.logic.controller;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Pair;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import energy.adesso.adessoandroidapp.logic.model.Token;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Issue;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Meter;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Reading;
import energy.adesso.adessoandroidapp.logic.model.exception.*;

import static energy.adesso.adessoandroidapp.ui.activities.MainActivity.getMeter;


public class MainController {
  private static MainController instance;
  private static String ip;
  private static SharedPreferences prefs;

  private static Token token = null;
  private static Token refreshToken = null;
  private static String uid;

  // Private because of static class
  private MainController() {

  }

  public static void sendIssue(Issue issue) throws NetworkException {
    String json = issue.serialize();
    String url = "api/issues";
    NetworkController.post(url,json,token.getToken());
  }

  public static void init(SharedPreferences prefs) {
    // init Persistance
    PersistanceController.getInstance().init(prefs);


    NetworkController.setAddress(PersistanceController.getInstance().load("address"));
    token = (Token) Token.deserialize(PersistanceController.getInstance().load("token"));
    refreshToken = (Token) Token.deserialize(PersistanceController.getInstance().load("refreshToken"));
    uid = PersistanceController.getInstance().load("uid");
  }


  /**
   * gets all readings associated with the meter with the given ID
   *
   * @param meterId the meter to get readings for
   * @return the list of readings
   * @throws NetworkException
   * @throws CredentialException when not logged in
   */
  public static List<Reading> getReadings(String meterId) throws NetworkException, CredentialException {
    String request = "/api/users/me/readings/" + meterId;
    List<Reading> readingList = new PagingHelper<Reading>().getAll(request, token);
    return readingList;
  }


  /**
   * tries to log in with the given username and password.
   *
   * @param username
   * @param password
   * @throws NetworkException
   */
  public static void login(String username, String password) throws NetworkException {
    // Send
    HashMap<String, String> map = new HashMap<String, String>();
    map.put("username", username);
    map.put("password", password);
    String json = new Gson().toJson(map);
    String reString = NetworkController.post("/api/login", json, null);

    // Save locally
    Type castType = new HashMap<String, String>() {
    }.getClass();
    HashMap<String, String> re = new Gson().fromJson(reString, castType);
    token = (Token) Token.deserialize(re.get("token"));
    refreshToken = (Token) Token.deserialize(re.get("refreshToken"));
    uid = re.get("uid");

    // Save persistently
    PersistanceController.getInstance().save("token", re.get("token"));
    PersistanceController.getInstance().save("refreshToken", re.get("refreshToken"));
    PersistanceController.getInstance().save("uid", re.get("uid"));

  }

  public static void logOut() throws NetworkException {
    // Send Request to Server
    String tokenString = token.getToken();
    HashMap<String, String> map = new HashMap<String, String>();
    map.put("token", tokenString);
    String json = new Gson().toJson(map);
    NetworkController.put("/api/logout", json, token.getToken());

    // Clear Local Vars
    token = null;
    refreshToken = null;
    uid = null;

    // Clear Persistant Vars
    PersistanceController.getInstance().delete("token");
    PersistanceController.getInstance().delete("refreshToken");
    PersistanceController.getInstance().delete("uid");
  }

  /**
   * analyzes an image for readerNumber and current reading
   *
   * @param image the image to analyze
   * @return a Tuple of number, reading
   * @throws CredentialException when not logged in
   */
  public static Pair<Meter, String> azureAnalyze(Bitmap image) throws NetworkException, CredentialException {
    // TODO this is def. wrong
    String url = "api/picture";
    String string = NetworkController.post(url, toBase64(image), token.getToken());
    Type castType = new Pair<String, String>("", "") {
    }.getClass();
    Pair<String, String> answer1 = new Gson().fromJson(string, castType);
    Meter m = getMeter(answer1.first);
    return new Pair<Meter, String>(m,answer1.second);
  }

  private static Meter getMeter(String mid) throws NetworkException {
    String url = ""+mid; // TODO:
    String json = NetworkController.get(url,token.getToken());
    return (Meter) Meter.deserialize(json);
  }

  /**
   * Sets the ip to send requests to
   *
   * @param newAddress the ip tp connect to
   * @throws NetworkException when the Network is faulty
   */
  public static void setServer(String newAddress) {
    if(newAddress == null)
      PersistanceController.getInstance().delete("address");
    else
      PersistanceController.getInstance().save("address", newAddress);

    NetworkController.setAddress(newAddress);
  }

  public static void createReading(String mid, String value) throws NetworkException, CredentialException {
    String url = "/api/meters";
    Reading reading = new Reading(null, mid, uid, value);
    String readingString = reading.serialize();
    NetworkController.post(url, readingString, token.getToken());
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

    String request = "/api/users/me/meters/";
    return new PagingHelper<Meter>().getAll(request, token);
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

  public static void correctReading(Reading reading) throws NetworkException {
    String url = "/api/meters/<mid>/readings/" + reading.getId();
    String json = reading.serialize();
    NetworkController.put(url,json,token.getToken());
  }

  public static void updateMeterName(Meter meter) throws NetworkException {
    String url = "/api/meters/" + meter.getId();
    String json = meter.serialize();
    NetworkController.put(url,json,token.getToken());
  }

    public boolean isLoggedIn() {
    return false;
    }
}
