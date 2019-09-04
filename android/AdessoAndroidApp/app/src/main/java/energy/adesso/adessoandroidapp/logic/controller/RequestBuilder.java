package energy.adesso.adessoandroidapp.logic.controller;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class RequestBuilder {
  private String toBase64(Bitmap image){
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    image.compress(Bitmap.CompressFormat.PNG, 100,baos);
    return Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
  }
}
