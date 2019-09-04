package energy.adesso.adessoandroidapp.logic.model;

import com.google.gson.Gson;

public abstract class SerializableObject {
  private Gson gson = new Gson();

  public String serialize(){
    return gson.toJson(this, this.getClass());
  }

}
