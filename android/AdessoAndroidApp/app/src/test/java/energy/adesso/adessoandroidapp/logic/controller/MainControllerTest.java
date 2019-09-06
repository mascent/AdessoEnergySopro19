package energy.adesso.adessoandroidapp.logic.controller;

import android.content.SharedPreferences;

import org.junit.Test;

import org.mockito.Mockito;

import energy.adesso.adessoandroidapp.logic.model.identifiable.User;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static org.junit.Assert.assertTrue;

public class MainControllerTest {

  @Test
  public void testMainControllerLogin() {
    try {

      // Create a MockWebServer. These are lean enough that you can create a new
      // instance for every unit test.
      MockWebServer server = new MockWebServer();

      User exampleUser = new User("diesistdieID", "diesistdiecustomernumber");
      server.enqueue(new MockResponse().setBody(exampleUser.serialize()));
      // Start the server.

      server.start();

      // Ask the server for its URL. You'll need this to make HTTP requests.
      HttpUrl baseUrl = server.url("");

      // Exercise your application code, which should make those HTTP requests.
      // Responses are returned in the same order that they are enqueued.
      MainController.setUsePersistence(false);
      SharedPreferences sp = Mockito.mock(SharedPreferences.class);
      MainController.init(sp);
      MainController.setServer(baseUrl.toString());

      MainController.login("hallo", "wasgeht");

      RecordedRequest loginRequest = server.takeRequest();
      // initial login should not send a token (since there is none saved)


      assertTrue(MainController.isLoggedIn());

      server.shutdown();

    } catch (Exception e) {
      throw new IllegalStateException();
    }
  }

}
