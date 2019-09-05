package energy.adesso.adessoandroidapp.logic.controller;

import android.content.SharedPreferences;

public class PersistanceController {
  private SharedPreferences prefs;

  private static PersistanceController instance;


  private PersistanceController() {

  }

  public synchronized void init(SharedPreferences prefs){
    this.prefs = prefs;
  }

  public synchronized static PersistanceController getInstance() {
    if (instance != null) return instance;
    instance = new PersistanceController();
    return instance;
  }

  public synchronized void save(String key, String value){
    prefs.edit().putString(key,value).apply();
  }

  public synchronized String load(String key){
    return prefs.getString(key,null);
  }

  public synchronized void delete(String key){
    prefs.edit().remove(key).apply();
  }
}
