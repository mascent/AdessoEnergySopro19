// TODO: throws and param annotation
// TODO set ip

package energy.adesso.adessoandroidapp.logic.controller;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Pair;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import energy.adesso.adessoandroidapp.logic.model.Paging;
import energy.adesso.adessoandroidapp.logic.model.Token;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Meter;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Reading;
import energy.adesso.adessoandroidapp.logic.model.exception.*;


public class MainController {
  private static MainController instance;
  private String ip;
  private SharedPreferences prefs;
  private Token token = null;

  private MainController() {

  }

  public void init(SharedPreferences prefs) {
    PersistanceController.getInstance().init(prefs);

    String tokenString = PersistanceController.getInstance().load("token");
    if (tokenString != null)
      token = (Token) Token.deserialize(tokenString);

    NetworkController.setAddress(PersistanceController.getInstance().load("address"));
  }

  /**
   * Singleton-getInstance.
   *
   * @return
   */
  public static MainController getInstance() {
    if (instance != null)
      return instance;
    instance = new MainController();
    return instance;
  }

  /**
   * gets all readings associated with the meter with the given ID
   *
   * @param readingId the meter to get readings for
   * @return the list of readings
   */
  public List<Reading> getDetails(String readingId) throws NetworkException {
    String request = "/api/users/me/readings/" + readingId;
    List<Reading> readingList = new PagingHelper<Reading>().getAll(request,token);
    return readingList;
  }


  /**
   * tries to log in with the given username and password.
   *
   * @param username
   * @param password
   * @return
   * @throws NetworkException
   */
  public boolean login(String username, String password) throws NetworkException {
    HashMap<String, String> map = new HashMap<String, String>();
    map.put("username", username);
    map.put("password", password);
    String json = new Gson().toJson(map);
    String tokenString = NetworkController.post("/api/login", json, null);
    PersistanceController.getInstance().save("token", tokenString);
    token = (Token) Token.deserialize(tokenString);

    return true;
  }

  public void logOut() throws NetworkException {
    String tokenString = token.getToken();
    HashMap<String, String> map = new HashMap<String, String>();
    map.put("token", tokenString);
    String json = new Gson().toJson(map);
    NetworkController.put("/api/logout", json, token.getToken());
    PersistanceController.getInstance().delete("token");
  }

  /**
   * analyzes an image for readerNumber and current reading
   *
   * @param image the image to analyze
   * @return a Tuple of number, reading
   */
  public Pair<Integer, Integer> azureAnalyze(Bitmap image) {
    return null;
  }

  /**
   * Sets the ip to send requests to
   *
   * @param address the ip tp connect to
   * @throws NetworkException when the Network is faulty
   */
  public void setServer(String address) {
    PersistanceController.getInstance().save("address", address);
    NetworkController.setAddress(address);
  }

  public void createReading(String mid, String value) throws NetworkException, CredentialException {
    String url = "/api/meters";
    Reading reading = new Reading(null, mid, token.getUserId(), value);
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
  public List<Meter> getOverview() throws NetworkException, CredentialException {
    String request = "/api/users/me/meters/";
    List<Meter> meterList = new PagingHelper<Meter>().getAll(request,token);
    return meterList;

  }

  private String toBase64(Bitmap bitmap) {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
    byte[] byteArray = byteArrayOutputStream.toByteArray();
    String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
    return encoded;
  }
}
