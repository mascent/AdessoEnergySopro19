package energy.adesso.adessoandroidapp.logic.model.identifiable;

import androidx.annotation.Nullable;

import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.List;

import energy.adesso.adessoandroidapp.logic.controller.MainController;
import energy.adesso.adessoandroidapp.logic.model.MeterKind;
import energy.adesso.adessoandroidapp.logic.model.exception.CredentialException;
import energy.adesso.adessoandroidapp.logic.model.exception.NetworkException;
import energy.adesso.adessoandroidapp.ui.mock.MockController;

public class Meter extends IdentifiableObject implements Serializable {
  private String meterNumber;
  private String type;
  private String name;
  @Nullable
  private long ownerId;
  private Reading lastReading; // nullable

  public Meter(long id) {
    super(id);
  }

  public Meter(long id, DateTime createdAt, DateTime updatedAt, DateTime deletedAt, String name, String meterNumber, MeterKind kind, long ownerId, Reading lastReading) {
    super(id, createdAt, updatedAt, deletedAt);
    this.name = name;
    this.ownerId = ownerId;
    this.lastReading = lastReading;
    this.meterNumber = meterNumber;
    this.type = kind.name().toLowerCase();
  }

  public static Meter deserialize(String source) {
    return gson.fromJson(source, Meter.class);
  }

  public MeterKind getKind() {
    MeterKind re;
    switch (type) {
      case "water":
        re = MeterKind.WATER;
        break;
      case "electric":
        re = MeterKind.ELECTRIC;
        break;
      case "gas":
        re = MeterKind.GAS;
        break;
      default:
        throw new Error(); //TODO: find a better exception to throw
    }
    return re;
  }

  // TODO change return to MeterKind
  public String getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  @Nullable
  public long getOwnerId() {
    return ownerId;
  }

  public Reading getLastReading() {
    return lastReading;
  }

  public String getMeterNumber() {
    return meterNumber;
  }

  public void createReading(String value) throws NetworkException, CredentialException {
    MainController.createReading(this.getId(), value);
  }

  public void setName(String newName) throws NetworkException, CredentialException {
    this.name = newName;
    MainController.updateMeterName(this);
  }

  public List<Reading> getReadings() throws CredentialException, NetworkException {
    return MainController.getReadings(this.getId());
  }


}
