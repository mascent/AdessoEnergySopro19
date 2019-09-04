package energy.adesso.adessoandroidapp.logic.model.identifiable;

import androidx.annotation.Nullable;

import org.joda.time.DateTime;

import energy.adesso.adessoandroidapp.logic.model.MeterKind;

public class Meter extends IdentifiableObject {
  private String kind;
  public String name;
  @Nullable
  public String ownerId;
  public String lastReading; // nullable
  public String meterNumber;

  public Meter(String id) {
    super(id);
  }

  public Meter(String id, DateTime createdAt, DateTime updatedAt, DateTime deletedAt, String name, String meterNumber, MeterKind kind,  String ownerId, String lastReading){
    super(id,createdAt,updatedAt,deletedAt);
    this.name = name;
    this.ownerId = ownerId;
    this.lastReading = lastReading;
    this.meterNumber = meterNumber;
    this.kind = kind.name();
  }

  public MeterKind getKind(){
    MeterKind re;
    switch(kind){
      case "water":
        re=MeterKind.WATER;
        break;
      case "electric":
        re=MeterKind.ELECTRIC;
        break;
      case "gas":
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

  public String getLastReading() {
    return lastReading;
  }

  public String getMeterNumber() {
    return meterNumber;
  }
}
