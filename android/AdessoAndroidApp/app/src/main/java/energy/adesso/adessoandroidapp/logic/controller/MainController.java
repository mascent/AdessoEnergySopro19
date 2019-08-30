package energy.adesso.adessoandroidapp.logic.controller;

import java.util.List;

import energy.adesso.adessoandroidapp.logic.model.Meter;

public class MainController {
  private static MainController instance;
  private String ip;
  private String token;

  private MainController() {

  }

  public static MainController getInstance() {
    if (instance != null)

      return instance;
    return new MainController();
  }

  public static void login() {

  }

  public String doStuff(){
    return "zufjzcgh";
  }

  public static List<Meter> getOverview() {
    return null;
  }
}
