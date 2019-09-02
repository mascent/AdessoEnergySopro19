package energy.adesso.adessoandroidapp.logic.model.internal;

import androidx.annotation.Nullable;

import java.util.Date;
import java.util.List;

import energy.adesso.adessoandroidapp.logic.controller.MainController;
import energy.adesso.adessoandroidapp.logic.model.exception.CredentialException;
import energy.adesso.adessoandroidapp.logic.model.exception.NetworkException;

public class Meter extends InternalObject {
  @Nullable
  List<Reading> readings;
  private String name;
  private String meterId;
  private MeterKind kind;
  private String lastReading;

  public Meter(Builder builder) {
    super(builder);
    this.lastReading = builder.lastReading;
    this.name = builder.name;
    this.meterId = builder.meterId;
    this.kind = builder.kind;
  }


  public static class Builder extends InternalObject.Builder {
    Builder(InternalObject.Builder builder) {
      super(builder.id);
      this.deletedAt = builder.deletedAt;
      this.createdAt = builder.createdAt;
      this.updatedAt = builder.updatedAt;
    }

    private String lastReading;
    private String name;
    private String meterId;
    private MeterKind kind;

    public Builder(long id) {
      super(id);
    }

    public Meter.Builder lastReading(String lastReading) {
      this.lastReading = lastReading;
      return this;
    }

    public Meter.Builder name(String name) {
      this.name = name;
      return this;
    }

    public Meter.Builder meterId(String meterId) {
      this.meterId = meterId;
      return this;
    }

    public Meter.Builder kind(MeterKind kind) {
      this.kind = kind;
      return this;
    }

    // Methods that stay the same but I need to change the return type
    @Override
    public Meter.Builder createdAt(Date createdAt){
      super.createdAt(createdAt);
      return this;
    }
    @Override
    public Meter.Builder updatedAt(Date updatedAt){
      super.updatedAt(updatedAt);
      return this;
    }
    @Override
    public Meter.Builder deletedAt(Date deletedAt) {
      super.deletedAt(deletedAt);
      return this;
    }


    public Meter build() {
      return new Meter(this);
    }

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

  public String getMeterId() {
    return meterId;
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
