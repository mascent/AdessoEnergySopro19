package energy.adesso.adessoandroidapp.logic.model.identifiable;


import org.joda.time.DateTime;

import java.io.Serializable;

import energy.adesso.adessoandroidapp.logic.controller.MainController;
import energy.adesso.adessoandroidapp.logic.model.exception.CredentialException;
import energy.adesso.adessoandroidapp.logic.model.exception.NetworkException;

public class Reading extends IdentifiableObject implements Serializable {

  private final Long meterId;
  private final Long ownerId;
  private String value;
  private int trend;
  private String lastEditorName;
  private String lastEditReason;

  public Reading(Long id, Long meterId, Long ownerId, String value) {
    super(id);
    this.meterId = meterId;
    this.ownerId = ownerId;
    this.value = value;
  }

  public Reading(Long id, Long meterId, Long ownerId, String value, DateTime createdAt) {
    super(id);
    this.meterId = meterId;
    this.ownerId = ownerId;
    this.value = value;

    this.createdAt = createdAt.toString(dateTimeStrategy);
  }

  public static Reading deserialize(String source) {
    return gson.fromJson(source, Reading.class);
  }

  public void correct(String newValue) throws NetworkException, CredentialException {
    this.value = newValue;
    MainController.correctReading(this);
  }

  public Long getMeterId() {
    return meterId;
  }

  public Long getOwnerId() {
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
