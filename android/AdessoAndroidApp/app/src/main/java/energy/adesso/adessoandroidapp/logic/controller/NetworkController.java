package energy.adesso.adessoandroidapp.logic.controller;

import android.content.SharedPreferences;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

import energy.adesso.adessoandroidapp.logic.model.exception.NetworkException;

public class NetworkController {
  private String baseURL = "124.245.1.240:3001";

  OkHttpClient ok = new OkHttpClient();


  private static NetworkController instance;

  public static final MediaType JSON
          = MediaType.parse("application/json; charset=utf-8");


  private NetworkController() {

  }

  public synchronized void init(String baseURL) {
    this.baseURL = baseURL;
  }

  public String get(String path) throws NetworkException {
    Request request = new Request.Builder()
            .url(baseURL + path)
            .build();
    try{
      Response response = ok.newCall(request).execute();
      return response.body().string();
    }catch (IOException e){
      throw new NetworkException();
    }
  }

  public String post(String path, String json) throws NetworkException {
    RequestBody body = RequestBody.create(JSON, json);
    Request request = new Request.Builder()
            .url(baseURL + path)
            .post(body)
            .build();
    try{
      Response response = ok.newCall(request).execute();
      return response.body().string();
    }catch (IOException e){
      throw new NetworkException();
    }
  }

  public String put(String path, String json) throws NetworkException {
    RequestBody body = RequestBody.create(JSON, json);
    Request request = new Request.Builder()
            .url(baseURL + path)
            .put(body)
            .build();
    try{
      Response response = ok.newCall(request).execute();
      return response.body().string();
    }catch (IOException e){
      throw new NetworkException();
    }
  }


  public void setBaseURL(String baseURL) {
    this.baseURL = baseURL;
  }
}
