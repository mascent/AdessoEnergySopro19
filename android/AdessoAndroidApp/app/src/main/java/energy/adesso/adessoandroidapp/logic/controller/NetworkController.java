package energy.adesso.adessoandroidapp.logic.controller;

import java.io.IOException;

import energy.adesso.adessoandroidapp.logic.model.exception.NetworkException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetworkController {
  private String baseURL = "124.245.1.240:3001";

  OkHttpClient ok = new OkHttpClient();


  private static NetworkController instance;

  public static final MediaType JSON
          = MediaType.get("application/json; charset=utf-8");


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
    }catch (IOException | NullPointerException e){
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
    }catch (IOException | NullPointerException e){
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
    }catch (IOException  | NullPointerException e){
      throw new NetworkException();
    }
  }


  public void setBaseURL(String baseURL) {
    this.baseURL = baseURL;
  }
}
