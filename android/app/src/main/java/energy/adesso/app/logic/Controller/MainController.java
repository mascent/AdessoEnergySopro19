package energy.adesso.app.logic.controller;

import java.util.List;
import energy.adesso.app.logic.*;

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

  public static List<Meter> getOverview() {
    return null;
  }
}
