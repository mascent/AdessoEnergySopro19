package energy.adesso.adessoandroidapp.logic.controller;

import android.content.SharedPreferences;

import org.junit.Test;

import org.mockito.Mockito;

import java.io.IOException;
import java.lang.reflect.Field;

import energy.adesso.adessoandroidapp.logic.model.exception.AdessoException;
import energy.adesso.adessoandroidapp.logic.model.exception.CredentialException;
import energy.adesso.adessoandroidapp.logic.model.exception.NetworkException;
import energy.adesso.adessoandroidapp.logic.model.identifiable.User;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {

  @Test
  public void testMainControllerLoginMock() throws AdessoException {

    String uid = "ichbineineuidichbincool";
    String username = "thelegend27";
    String password = "password1";

    // Create a MockWebServer. These are lean enough that you can create a new
    // instance for every unit test.
    MockWebServer server = new MockWebServer();

    User exampleUser = new User(uid, "diesistdiecustomernumber");
    server.enqueue(new MockResponse().setBody(exampleUser.serialize()));
    // Start the server.

    try {
      server.start();
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Ask the server for its URL. You'll need this to make HTTP requests.
    HttpUrl baseUrl = server.url("");

    // Exercise your application code, which should make those HTTP requests.
    // Responses are returned in the same order that they are enqueued.
    MainController.setUsePersistence(false);
    SharedPreferences sp = Mockito.mock(SharedPreferences.class);
    MainController.loadSharedPreferences(sp);
    MainController.setServer(baseUrl.toString());

    MainController.login(username, password);

    try {
      RecordedRequest loginRequest = server.takeRequest();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      // check if things behaved as they should
      // Hack into the mainframe and disable their algorithms https://www.reddit.com/r/antimeme/comments/9s1zld/when_you_hack_into_the_mainframe_and_disable/
      Field uidField = MainController.class.getDeclaredField("uid");
      Field usernameField = NetworkController.class.getDeclaredField("username");
      Field passwordField = NetworkController.class.getDeclaredField("password");

      uidField.setAccessible(true);
      usernameField.setAccessible(true);
      passwordField.setAccessible(true);
      String reflectedUid = (String) uidField.get(null);
      String reflectedUsername = (String) usernameField.get(null);
      String reflectedPassword = (String) passwordField.get(null);

      assertEquals(uid, reflectedUid);
      assertEquals(username, reflectedUsername);
      assertEquals(password, reflectedPassword);
      assertTrue(MainController.isLoggedIn());

      // test for correctLogout
      MainController.logOut();
      assertFalse(MainController.isLoggedIn());
      assertNull(usernameField.get(null));
      assertNull(passwordField.get(null));
      assertNull(uidField.get(null));

    } catch (NoSuchFieldException | IllegalAccessException e) {
      e.printStackTrace();
    }

    try {
      server.shutdown();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testMainControllerLogin() throws CredentialException, NetworkException {

    String username = "jd172";
    String password = "password";

    String baseUrl = "http://adesso.energy:8080/sopro";

    // Exercise your application code, which should make those HTTP requests.
    // Responses are returned in the same order that they are enqueued.
    MainController.setUsePersistence(false);
    SharedPreferences sp = Mockito.mock(SharedPreferences.class);
    MainController.loadSharedPreferences(sp);
    MainController.setServer(baseUrl);

    MainController.login(username, password);

    try {
      // check if things behaved as they should
      // Hack into the mainframe and disable their algorithms https://www.reddit.com/r/antimeme/comments/9s1zld/when_you_hack_into_the_mainframe_and_disable/
      Field uidField = MainController.class.getDeclaredField("uid");
      Field usernameField = NetworkController.class.getDeclaredField("username");
      Field passwordField = NetworkController.class.getDeclaredField("password");

      uidField.setAccessible(true);
      usernameField.setAccessible(true);
      passwordField.setAccessible(true);
      String reflectedUid = (String) uidField.get(null);
      String reflectedUsername = (String) usernameField.get(null);
      String reflectedPassword = (String) passwordField.get(null);

      assertNotNull(reflectedUid);
      assertEquals(username, reflectedUsername);
      assertEquals(password, reflectedPassword);
      assertTrue(MainController.isLoggedIn());
    } catch (NoSuchFieldException | IllegalAccessException e) {
      e.printStackTrace();
    }

  }

}
