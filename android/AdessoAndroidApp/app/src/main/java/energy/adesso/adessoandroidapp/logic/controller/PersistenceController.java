package energy.adesso.adessoandroidapp.logic.controller;

import android.content.SharedPreferences;

public class PersistenceController {
  private SharedPreferences prefs;

  PersistenceController(SharedPreferences prefs) {
    this.prefs = prefs;
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
