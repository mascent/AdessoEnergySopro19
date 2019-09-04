package energy.adesso.adessoandroidapp.logic.model;

import com.google.gson.Gson;

public class SerializableObject {
  protected static Gson gson = new Gson();

  public String serialize(){
    return gson.toJson(this, this.getClass());
  }

  public static Object deserialize(String source){
    Class currentClass = new Object(){}.getClass().getEnclosingClass();
    return gson.fromJson(source, currentClass);
  }

}
