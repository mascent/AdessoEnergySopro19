package energy.adesso.adessoandroidapp.logic.model.identifiable;


import energy.adesso.adessoandroidapp.logic.controller.MainController;
import energy.adesso.adessoandroidapp.logic.model.exception.NetworkException;

public class Reading extends IdentifiableObject {

  private final String meterId;
  private final String ownerId;
  private String value;
  private String lastEditorName;
  private String lastEditReason;
  private int trend;

  public Reading(String id, String meterId, String ownerId, String value) {
    super(id);
    this.meterId = meterId;
    this.ownerId = ownerId;
    this.value = value;
  }

  public static Reading deserialize(String source){
    return gson.fromJson(source, Reading.class);
  }

  public void correct(String newValue) throws NetworkException {
    this.value = newValue;
    MainController.getInstance().correctReading(this);
  }

  public String getMeterId() {
    return meterId;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public String getValue() {
    return value;
  }

  public String getLastEditorName() {
    return lastEditorName;
  }

  public String getLastEditReason() {
    return lastEditReason;
  }

  public int getTrend() {
    return trend;
  }
}
