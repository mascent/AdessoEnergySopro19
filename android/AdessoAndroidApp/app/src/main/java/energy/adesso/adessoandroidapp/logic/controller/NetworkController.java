package energy.adesso.adessoandroidapp.logic.controller;

import android.content.SharedPreferences;

import com.squareup.okhttp.OkHttpClient;

public class NetworkController {
  private String baseURL = "124.245.1.240:3001";

  OkHttpClient ok = new OkHttpClient();


  private static NetworkController instance;

  private NetworkController() {

  }

  public synchronized void init(String baseURL) {
    this.baseURL = baseURL;
  }


  public void setBaseURL(String baseURL) {
    this.baseURL = baseURL;
  }
}
