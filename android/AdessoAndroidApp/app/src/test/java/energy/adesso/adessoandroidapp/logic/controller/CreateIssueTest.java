package energy.adesso.adessoandroidapp.logic.controller;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import energy.adesso.adessoandroidapp.logic.model.exception.CredentialException;
import energy.adesso.adessoandroidapp.logic.model.exception.NetworkException;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Issue;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateIssueTest {

  @Test
  public void testCreateIssueMock() throws CredentialException, NetworkException {

    String username = "aogas";
    String password = "aosuhgas afvaouvh";
    String email = "eine@email.com";
    String name = "Dieter Dietrichsen";
    String subject = "object";
    String message = "according to all known laws of aviation, there's no way a bee can fly";

    MockWebServer server = new MockWebServer();

    Map<String, Boolean> replyMap = new HashMap<>();
    replyMap.put("success", true);
    server.enqueue(new MockResponse().setBody(new Gson().toJson(replyMap, replyMap.getClass())));

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

    // set credentials through reflection since we're not here to test that
    try {
      Field usernameReflection = NetworkController.class.getDeclaredField("username");
      Field passwordReflection = NetworkController.class.getDeclaredField("password");

      usernameReflection.setAccessible(true);
      passwordReflection.setAccessible(true);

      usernameReflection.set(null, username);
      passwordReflection.set(null, password);
    } catch (NoSuchFieldException | IllegalAccessException e) {
      e.printStackTrace();
    }

    Issue i = new Issue(null);
    i.setEmail(email);
    i.setName(name);
    i.setSubject(subject);
    i.setMessage(message);

    i.send();


    RecordedRequest recorded = null;
    try {
      recorded = server.takeRequest();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    Issue r = Issue.deserialize(recorded.getBody().readUtf8());
    assertEquals(i.getEmail(), r.getEmail());
    assertEquals(i.getMessage(), r.getMessage());
    assertEquals(i.getName(), r.getName());
    assertEquals(i.getSubject(), r.getSubject());

    try {
      server.shutdown();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testCreateIssue() throws CredentialException, NetworkException {

    String username = "jd172";
    String password = "password";

    String email = "eine@email.com";
    String name = "Dieter Dietrichsen";
    String subject = "object";
    String message = "according to all known laws of aviation, there's no way a bee can fly";

    String baseUrl = "http://adesso.energy:8080/sopro";

    MainController.setUsePersistence(false);
    SharedPreferences sp = Mockito.mock(SharedPreferences.class);
    MainController.loadSharedPreferences(sp);
    MainController.setServer(baseUrl.toString());

    // set credentials through reflection since we're not here to test that
    try {
      Field usernameReflection = NetworkController.class.getDeclaredField("username");
      Field passwordReflection = NetworkController.class.getDeclaredField("password");

      usernameReflection.setAccessible(true);
      passwordReflection.setAccessible(true);

      usernameReflection.set(null, username);
      passwordReflection.set(null, password);
    } catch (NoSuchFieldException | IllegalAccessException e) {
      e.printStackTrace();
    }

    Issue i = new Issue(null);
    i.setEmail(email);
    i.setName(name);
    i.setSubject(subject);
    i.setMessage(message);

    // if this doesn't throw an exception were golden
    i.send();

  }
}
