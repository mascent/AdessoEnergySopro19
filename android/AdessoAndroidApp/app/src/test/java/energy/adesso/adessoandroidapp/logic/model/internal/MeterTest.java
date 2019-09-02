package energy.adesso.adessoandroidapp.logic.model.internal;

import org.junit.Test;

import java.util.Date;

import energy.adesso.adessoandroidapp.logic.model.internal.Meter.Builder;

public class MeterTest {
  @Test
  public void TestAll() {
    Date createdAt = new Date(1999, 02, 05, 12, 07, 59);
    Date updatedAt = new Date(2007, 4, 23, 16, 7, 6);
    Date deletedAt = new Date(2008, 7, 4, 13, 5, 2);
    long id = 371923875;
    String meterId = "i bims 1 reader";
    MeterKind kind = MeterKind.WATER;
    String name = "batman";
    String lastReading = "384729,9";

    Meter m = new Meter.Builder(id)
            .createdAt(createdAt)
            .deletedAt(deletedAt)
            .updatedAt(updatedAt)
            .meterId(meterId)
            .kind(kind)
            .name(name)
            .lastReading(lastReading)
            .build();

  }
}
