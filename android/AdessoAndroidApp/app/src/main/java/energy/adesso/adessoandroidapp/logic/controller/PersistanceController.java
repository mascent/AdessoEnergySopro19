package energy.adesso.adessoandroidapp.logic.controller;

public class PersistanceController {
  private PersistanceController instance;

  private PersistanceController() {

  }

  public PersistanceController getInstance() {
    if (instance == null) return new PersistanceController();
    return instance;
  }

  public void save(String key, String value){

  }

  public String retrieve(String key){
    return null;
  }

  public void delete(String key){

  }
}
