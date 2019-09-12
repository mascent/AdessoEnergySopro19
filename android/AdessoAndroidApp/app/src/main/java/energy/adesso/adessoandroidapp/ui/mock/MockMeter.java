package energy.adesso.adessoandroidapp.ui.mock;

import androidx.annotation.Nullable;

import org.joda.time.DateTime;

import java.util.Arrays;
import java.util.List;

import energy.adesso.adessoandroidapp.logic.model.MeterKind;
import energy.adesso.adessoandroidapp.logic.model.exception.CredentialException;
import energy.adesso.adessoandroidapp.logic.model.exception.NetworkException;
import energy.adesso.adessoandroidapp.logic.model.identifiable.IdentifiableObject;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Meter;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Reading;

public class MockMeter extends Meter {

  public MockMeter(Long id) {
    super(id);
  }

  public MockMeter(Long id, DateTime createdAt, DateTime updatedAt, DateTime deletedAt, String name, String meterNumber, MeterKind kind, Long ownerId, Reading lastReading) {
    super(id, createdAt, updatedAt, deletedAt, name, meterNumber, kind, ownerId, lastReading);
  }
}
