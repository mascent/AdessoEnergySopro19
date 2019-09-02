package energy.adesso.adessoandroidapp.logic.model.internal;

import androidx.annotation.Nullable;

import java.util.List;

import energy.adesso.adessoandroidapp.logic.controller.MainController;
import energy.adesso.adessoandroidapp.logic.model.exception.CredentialException;
import energy.adesso.adessoandroidapp.logic.model.exception.NetworkException;
import energy.adesso.adessoandroidapp.logic.model.transfer.MeterDTO;

public class Meter extends InternalObject {
  @Nullable
  List<Reading> readings;
  private String name;
  private String meterNumber;
  private MeterKind kind;
  private String lastReading;

  public Meter(String createdAt, String updatedAt, String deletedAt, long id, String name, String meterNumber, MeterKind kind, String lastReading){
    super(id, createdAt, updatedAt,deletedAt);
    this.name = name;
    this.meterNumber = meterNumber;
    this.kind = kind;
    this.lastReading = lastReading;
  }

  public Meter(MeterDTO dto){
    super(dto.id,dto.createdAt, dto.updatedAt, dto.deletedAt);
    this.name = name;
    this.meterNumber = dto.meterNumber;
    switch(dto.type){
      case "water":
        kind = MeterKind.WATER;
        break;
      case "electric":
        kind = MeterKind.ELECTRIC;
        break;
      case "gas":
        kind = MeterKind.GAS;
        break;
    }
    this.lastReading = dto.lastReading.value;
  }

  public void populateReadings() throws NetworkException, CredentialException {

  }

  public void createEntry(String reading) throws NetworkException, CredentialException {
    MainController.getInstance().createReading(this.getId(), reading);
  }

  // Getters

  @Nullable
  public List<Reading> getReadings() {
    return readings;
  }

  public String getName() {
    return name;
  }

  public String getMeterNumber() {
    return meterNumber;
  }

  public MeterKind getKind() {
    return kind;
  }

  public String getLastReading() {
    return lastReading;
  }

  // Setters

  public void setName(String name) {
    // TODO network stuff
    this.name = name;
  }
}
