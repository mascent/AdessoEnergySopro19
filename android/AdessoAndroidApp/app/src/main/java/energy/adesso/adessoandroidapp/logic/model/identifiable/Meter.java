package energy.adesso.adessoandroidapp.logic.model.identifiable;

import android.net.Network;

import androidx.annotation.Nullable;

import org.joda.time.DateTime;

import java.util.List;

import energy.adesso.adessoandroidapp.logic.controller.MainController;
import energy.adesso.adessoandroidapp.logic.model.MeterKind;
import energy.adesso.adessoandroidapp.logic.model.exception.CredentialException;
import energy.adesso.adessoandroidapp.logic.model.exception.NetworkException;

public class Meter extends IdentifiableObject {
  private String kind;
  private String name;
  @Nullable
  private String ownerId;
  private Reading lastReading; // nullable
  private String meterNumber;

  public Meter(String id) {
    super(id);
  }

  public Meter(String id, DateTime createdAt, DateTime updatedAt, DateTime deletedAt, String name, String meterNumber, MeterKind kind,  String ownerId, Reading lastReading){
    super(id,createdAt,updatedAt,deletedAt);
    this.name = name;
    this.ownerId = ownerId;
    this.lastReading = lastReading;
    this.meterNumber = meterNumber;
    this.kind = kind.name();
  }

  public Meter(String id, DateTime createdAt, DateTime updatedAt, DateTime deletedAt, String name, String meterNumber, MeterKind kind,  String ownerId, String lastReading){
    super(id,createdAt,updatedAt,deletedAt);
    this.name = name;
    this.ownerId = ownerId;
    //TODO: remove this constructor when no longer needed
    this.lastReading = new Reading("eineIDlol","eineID2lol","einownerLol", lastReading);
    this.meterNumber = meterNumber;
    this.kind = kind.name();
  }

  public static Meter deserialize(String source){
    return gson.fromJson(source, Meter.class);
  }

  public MeterKind getKind(){
    MeterKind re;
    switch(kind){
      case "WATER":
        re=MeterKind.WATER;
        break;
      case "ELECTRIC":
        re=MeterKind.ELECTRIC;
        break;
      case "GAS":
        re=MeterKind.GAS;
        break;
      default: throw new Error(); //TODO: find a better exception to throw
    }
    return re;
  }

  // TODO change return to MeterKind
  public String getType() {
    return kind;
  }

  public String getName() {
    return name;
  }

  @Nullable
  public String getOwnerId() {
    return ownerId;
  }

  public Reading getLastReading() {
    return lastReading;
  }

  public String getMeterNumber() {
    return meterNumber;
  }

  public void createReading(String value) throws NetworkException, CredentialException {
    MainController.createReading(this.getId(),value);
  }

  public void setName(String newName) throws NetworkException, CredentialException {
    this.name = newName;
    MainController.updateMeterName(this);
  }

  public List<Reading> getReadings() throws CredentialException, NetworkException {
    return MainController.getReadings(this.getId());
  }


}
