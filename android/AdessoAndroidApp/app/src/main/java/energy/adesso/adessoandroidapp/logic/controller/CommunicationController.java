package energy.adesso.adessoandroidapp.logic.controller;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import energy.adesso.adessoandroidapp.logic.model.exception.NetworkException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CommunicationController {
  private String baseURL = "124.245.1.240:3001";

  OkHttpClient ok = new OkHttpClient();


  private static CommunicationController instance;

  private static final MediaType JSON
          = MediaType.get("application/json; charset=utf-8");

  private CommunicationController() {

  }

  public static CommunicationController getInstance() {
    if (instance != null) return instance;

    instance = new CommunicationController();
    return instance;
  }

  public synchronized void init(String baseURL) {
    this.baseURL = baseURL;
  }

  private String get(Request request) throws NetworkException {
    try {
      Response response = ok.newCall(request).execute();
      return response.body().string();
    } catch (IOException | NullPointerException e) {
      throw new NetworkException();
    }
  }

  private String post(Request request) throws NetworkException {
    try {
      Response response = ok.newCall(request).execute();
      return response.body().string();
    } catch (IOException | NullPointerException e) {
      throw new NetworkException();
    }
  }

  private String put(Request request) throws NetworkException {
    try {
      Response response = ok.newCall(request).execute();
      return response.body().string();
    } catch (IOException | NullPointerException e) {
      throw new NetworkException();
    }
  }


  public void setBaseURL(String baseURL) {
    this.baseURL = baseURL;
  }

  private String toBase64(Bitmap image) {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    image.compress(Bitmap.CompressFormat.PNG, 100, baos);
    return Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
  }
}
