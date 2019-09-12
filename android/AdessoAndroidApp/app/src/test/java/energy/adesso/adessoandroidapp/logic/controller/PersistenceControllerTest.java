package energy.adesso.adessoandroidapp.logic.controller;

import android.content.SharedPreferences;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PersistenceControllerTest {

  // @Test
  public void TestPersistence() {
    String username = "dobiko";
    String password = "guofdshgfsuhougorhsa";

    SharedPreferences prefs = Mockito.mock(SharedPreferences.class);
    //TODO: NO idea why this doesn't work
    Mockito.doNothing().when(prefs).edit().putString(Mockito.any(String.class), Mockito.any(String.class)).apply();

    Mockito.when(prefs.getString("username", null)).thenReturn(username);
    Mockito.when(prefs.getString("password", null)).thenReturn(password);

    PersistenceController persistence = new PersistenceController(prefs);
    persistence.save("username", username);
    persistence.save("password", password);

    assertEquals(persistence.load("username"), username);
    assertEquals(persistence.load("password"), password);
  }

  private void doStuff() {

  }
}