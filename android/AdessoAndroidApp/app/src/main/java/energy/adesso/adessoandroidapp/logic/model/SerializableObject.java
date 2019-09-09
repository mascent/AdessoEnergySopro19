package energy.adesso.adessoandroidapp.logic.model;

import com.google.gson.Gson;

import java.io.Serializable;

public class SerializableObject implements Serializable {
  protected static Gson gson = new Gson();

  public String serialize() {
    return gson.toJson(this, this.getClass());
  }

  public static Object deserialize(String source) {
    try {
      Class currentClass = new Object() {
      }.getClass().getEnclosingClass();
      return gson.fromJson(source, currentClass);
    } catch (Exception e) {
      return null;
    }
  }

}
