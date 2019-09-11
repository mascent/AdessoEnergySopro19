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

    public MockMeter(String id) {
        super(id);
    }
    public MockMeter(String name, String meterNumber, MeterKind kind, Reading lastReading){
        super("123",MockController.time,MockController.time,MockController.time,name,
            meterNumber,kind,"12345",lastReading);
    }
    public MockMeter(String id, DateTime createdAt, DateTime updatedAt, DateTime deletedAt,
                     String name, String meterNumber, MeterKind kind, String ownerId, Reading lastReading){
        super(id,createdAt,updatedAt,deletedAt,name,meterNumber,kind,ownerId,lastReading);
    }

    @Override
    public Reading getLastReading() {
        return MockController.lastReading;
    }

    @Override
    public List<Reading> getReadings() throws CredentialException, NetworkException {
        int i = 12345;
        return Arrays.asList(new Reading[] {
            new Reading("12345", getId(), getOwnerId(), Integer.toString(i++), MockController.getTime()),
            new Reading("12345", getId(), getOwnerId(), Integer.toString(i++), MockController.getTime()),
            new Reading("12345", getId(), getOwnerId(), Integer.toString(i++), MockController.getTime()),
            new Reading("12345", getId(), getOwnerId(), Integer.toString(i++), MockController.getTime()),
            new Reading("12345", getId(), getOwnerId(), Integer.toString(i++), MockController.getTime()),
            new Reading("12345", getId(), getOwnerId(), Integer.toString(i++), MockController.getTime()),
            new Reading("12345", getId(), getOwnerId(), Integer.toString(i++), MockController.getTime()),
            new Reading("12345", getId(), getOwnerId(), Integer.toString(i++), MockController.getTime()),
            new Reading("12345", getId(), getOwnerId(), Integer.toString(i++), MockController.getTime()),
            new Reading("12345", getId(), getOwnerId(), Integer.toString(i++), MockController.getTime()),
            new Reading("12345", getId(), getOwnerId(), Integer.toString(i++), MockController.getTime()),
            new Reading("12345", getId(), getOwnerId(), Integer.toString(i++), MockController.getTime()),
            new Reading("12345", getId(), getOwnerId(), Integer.toString(i++), MockController.getTime()),
            new Reading("12345", getId(), getOwnerId(), Integer.toString(i++), MockController.getTime()),
            new Reading("12345", getId(), getOwnerId(), Integer.toString(i++), MockController.getTime()),
            new Reading("12345", getId(), getOwnerId(), Integer.toString(i++), MockController.getTime()),
            new Reading("12345", getId(), getOwnerId(), Integer.toString(i++), MockController.getTime()),
            new Reading("12345", getId(), getOwnerId(), Integer.toString(i++), MockController.getTime()),
            new Reading("12345", getId(), getOwnerId(), Integer.toString(i++), MockController.getTime()),
            new Reading("12345", getId(), getOwnerId(), Integer.toString(i++), MockController.getTime()),
            new Reading("12345", getId(), getOwnerId(), Integer.toString(i++), MockController.getTime()),
            new Reading("12345", getId(), getOwnerId(), Integer.toString(i++), MockController.getTime()),
            new Reading("12345", getId(), getOwnerId(), Integer.toString(i++), MockController.getTime()),
            new Reading("12345", getId(), getOwnerId(), Integer.toString(i++), MockController.getTime()),
            new Reading("12345", getId(), getOwnerId(), Integer.toString(i++), MockController.getTime()),
            new Reading("12345", getId(), getOwnerId(), Integer.toString(i++), MockController.getTime()),
            new Reading("12345", getId(), getOwnerId(), Integer.toString(i++), MockController.getTime()),
            new Reading("12345", getId(), getOwnerId(), Integer.toString(i++), MockController.getTime()),
        });
    }

    public void setName(String newName) throws NetworkException {
        try {
            super.setName(newName);
        } catch (CredentialException e) { }
    }

    public Meter toMeter() {
        return new Meter(super.getId(),MockController.time,MockController.time,MockController.time,
            getName(),getMeterNumber(),super.getKind(),getOwnerId(),getLastReading());
    }
}
