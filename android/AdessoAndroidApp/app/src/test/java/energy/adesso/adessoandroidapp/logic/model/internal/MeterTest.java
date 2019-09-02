package energy.adesso.adessoandroidapp.logic.model.internal;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;


public class MeterTest {
  @Before
  public void initTime(){

  }


  @Test
  public void testConstructor() {
    DateTime createdAt = new DateTime(1999, 02, 05, 12, 07, 59);
    DateTime updatedAt = new DateTime(2007, 4, 23, 16, 7, 6);
    DateTime deletedAt = new DateTime(2008, 7, 4, 13, 5, 2);
    long id = 371923875;
    String meterNumber = "I CONTROL THE SPEED AT WHICH LOBSTERS DIE";
    MeterKind kind = MeterKind.WATER;
    String name = "Aquaman";
    String lastReading = "384729,9";

    Meter m = new Meter(createdAt, updatedAt, deletedAt, id, name, meterNumber, kind, lastReading);
    assertEquals(m.getId(), id);
    assertEquals(m.getCreatedAt(), createdAt);
    assertEquals(m.getUpdatedAt(), updatedAt);
    assertEquals(m.getDeletedAt(), deletedAt);
    assertEquals(m.getMeterNumber(), meterNumber);
    assertEquals(m.getKind(), kind);
    assertEquals(m.getName(), name);
    assertEquals(m.getLastReading(), lastReading);
  }
}
