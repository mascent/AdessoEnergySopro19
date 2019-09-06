package energy.adesso.adessoandroidapp.ui.mock;

import androidx.annotation.Nullable;

import org.joda.time.DateTime;

import energy.adesso.adessoandroidapp.logic.model.MeterKind;
import energy.adesso.adessoandroidapp.logic.model.identifiable.IdentifiableObject;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Reading;

public class MockMeter extends IdentifiableObject {
    private String kind;
    public String name;
    @Nullable
    public String ownerId;
    public Reading lastReading; // nullable
    public String meterNumber;

    public MockMeter(String id) {
        super(id);
    }

    public MockMeter(String id, DateTime createdAt, DateTime updatedAt, DateTime deletedAt, String name, String meterNumber, MeterKind kind, String ownerId, Reading lastReading){
        super(id,createdAt,updatedAt,deletedAt);
        this.name = name;
        this.ownerId = ownerId;
        this.lastReading = lastReading;
        this.meterNumber = meterNumber;
        this.kind = kind.name();
    }

    public MockMeter(String id, DateTime createdAt, DateTime updatedAt, DateTime deletedAt, String name, String meterNumber, MeterKind kind,  String ownerId, String lastReading){
        super(id,createdAt,updatedAt,deletedAt);
        this.name = name;
        this.ownerId = ownerId;
        //TODO: remove this constructor when no longer needed
        this.lastReading = new Reading("eineIDlol","eineID2lol","einownerLol", lastReading);
        this.meterNumber = meterNumber;
        this.kind = kind.name();
    }


}
