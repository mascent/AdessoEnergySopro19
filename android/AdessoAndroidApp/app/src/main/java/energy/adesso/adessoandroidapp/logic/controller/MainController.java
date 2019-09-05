// TODO: throws and param annotation

package energy.adesso.adessoandroidapp.logic.controller;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Pair;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.List;

import energy.adesso.adessoandroidapp.logic.model.Token;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Meter;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Reading;
import energy.adesso.adessoandroidapp.logic.model.exception.*;


public class MainController {
  private static MainController instance;
  private String ip;
  private String token;
  private SharedPreferences prefs;

  private MainController() {

  }

  public void init(SharedPreferences prefs) {
    PersistanceController.getInstance().init(prefs);
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
   * @param meterId the meter to get readings for
   * @return the list of readings
   */
  public List<Reading> getDetails(String meterId) {
    return null;
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
    String tokenString = NetworkController.getInstance().post("/api/login",json);
    PersistanceController.getInstance().save("token", tokenString);
    Token theToken = new Gson().fromJson(tokenString, Token.class);

    return true;
  }

  public void logOut() throws NetworkException {
    String token = PersistanceController.getInstance().load("token");
    HashMap<String, String> map = new HashMap<String, String>();
    map.put("token", token);
    String json = new Gson().toJson(map);
    NetworkController.getInstance().put("/api/logout", json);
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
   * @param ip the ip tp connect to
   * @throws NetworkException when the Network is faulty
   */
  public void setServer(String ip) throws NetworkException {

  }

  public Reading createReading(String mid, String reading) throws NetworkException, CredentialException {
    return null;
  }

  public String doStuff() {
    PersistanceController.getInstance().save("hello", "how are you");
    return PersistanceController.getInstance().load("hello");
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
    return null;
  }
}
