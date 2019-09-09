package energy.adesso.adessoandroidapp.logic.model.identifiable;


import org.joda.time.DateTime;

import java.io.Serializable;

import energy.adesso.adessoandroidapp.logic.controller.MainController;
import energy.adesso.adessoandroidapp.logic.model.exception.CredentialException;
import energy.adesso.adessoandroidapp.logic.model.exception.NetworkException;
import energy.adesso.adessoandroidapp.ui.mock.MockController;

public class Reading extends IdentifiableObject implements Serializable {

  private final String meterId;
  private final String ownerId;
  private String value;
  private int trend;
  private String lastEditorName;
  private String lastEditReason;

  public Reading(String id, String meterId, String ownerId, String value) {
    super(id);
    this.meterId = meterId;
    this.ownerId = ownerId;
    this.value = value;
  }

  public Reading(String id, String meterId, String ownerId, String value, DateTime createdAt) {
    super(id);
    this.meterId = meterId;
    this.ownerId = ownerId;
    this.value = value;

    this.createdAt = createdAt.toString(dateTimeStrategy);
  }

  public static Reading deserialize(String source){
    return gson.fromJson(source, Reading.class);
  }

  public void correct(String newValue) throws NetworkException, CredentialException {
    this.value = newValue;
    MainController.correctReading(this);
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
