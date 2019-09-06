package energy.adesso.adessoandroidapp.logic.controller;

import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import org.mockito.Mockito;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

public class MainControllerTest {
  SharedPreferences sp;

  @Before
  public void init() {
    sp = Mockito.mock(SharedPreferences.class);
    Mockito.when(sp.getString("token", null)).thenReturn(null);
  }


  @Test
  public void testMainControllerLogin() {
    try {
      // Create a MockWebServer. These are lean enough that you can create a new
      // instance for every unit test.
      MockWebServer server = new MockWebServer();

      // Schedule some responses.
      // TODO build some responses
      server.enqueue(new MockResponse().setBody("hello, world!"));

      // Start the server.

      server.start();


      // Ask the server for its URL. You'll need this to make HTTP requests.
      HttpUrl baseUrl = server.url("/api/");

      // Exercise your application code, which should make those HTTP requests.
      // Responses are returned in the same order that they are enqueued.
      MainController.init(sp);
      MainController.setServer(baseUrl.toString());

      MainController.login("hallo", "wasgeht");

      RecordedRequest loginRequest = server.takeRequest();
      // initial login should not send a token (since there is none saved)
      assertNull(loginRequest.getHeader("Token"));
      server.shutdown();


    } catch (Exception e) {
    }
  }

}
