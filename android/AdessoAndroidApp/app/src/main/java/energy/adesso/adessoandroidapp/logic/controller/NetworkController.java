package energy.adesso.adessoandroidapp.logic.controller;

import android.content.SharedPreferences;

import java.io.IOException;

import energy.adesso.adessoandroidapp.logic.model.exception.NetworkException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetworkController {
  private final static String defaultURL = "124.245.1.240:3001";
  private static String baseURL = defaultURL;

  private static OkHttpClient ok = new OkHttpClient();


  private static NetworkController instance;

  public static final MediaType JSON
          = MediaType.get("application/json; charset=utf-8");


  private NetworkController() {

  }

  public static String get(String path, String token) throws NetworkException {
    //TODO accept token
    Request request = new Request.Builder()
            .url(baseURL + path)
            .build();
    try {
      Response response = ok.newCall(request).execute();
      return response.body().string();
    } catch (IOException | NullPointerException e) {
      throw new NetworkException();
    }
  }

  public static String post(String path, String json, String token) throws NetworkException {
    //TODO accept token
    RequestBody body = RequestBody.create(JSON, json);
    Request request = new Request.Builder()
            .url(baseURL + path)
            .post(body)
            .build();
    try {
      Response response = ok.newCall(request).execute();
      return response.body().string();
    } catch (IOException | NullPointerException e) {
      throw new NetworkException();
    }
  }

  public static String put(String path, String json, String token) throws NetworkException {
    //TODO accept token
    RequestBody body = RequestBody.create(JSON, json);
    Request request = new Request.Builder()
            .url(baseURL + path)
            .put(body)
            .build();
    try {
      Response response = ok.newCall(request).execute();
      return response.body().string();
    } catch (IOException | NullPointerException e) {
      throw new NetworkException();
    }
  }


  public static void setAddress(String address) {
    if (address != null)
      address = baseURL;
    else
      address = defaultURL;
  }
}
