package energy.adesso.adessoandroidapp.model;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import energy.adesso.adessoandroidapp.logic.model.MeterKind;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Issue;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Meter;
import energy.adesso.adessoandroidapp.logic.model.identifiable.Reading;
import energy.adesso.adessoandroidapp.logic.model.identifiable.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// TODO: prevent nullpointerExceptions with samesame everywhere
public class SerializableTest {
  private final static String basePath = "/src/test/java/energy/adesso/adessoandroidapp/model/resources/";

  @Test
  public void testUserConversion() {
    String json = readFromDisk("User.json");

    DateTime createdAt = new DateTime(1990, 6, 21, 16, 23, 37, DateTimeZone.forID("Europe/Berlin"));
    DateTime updatedAt = new DateTime(1938, 3, 11, 11, 43, 21, DateTimeZone.forID("Europe/Berlin"));
    DateTime deletedAt = new DateTime(1999, 11, 21, 8, 11, 17, DateTimeZone.forID("Europe/Berlin"));

    // Test if converting the json back-and forth causes any issues
    User u = User.deserialize(json);
    String fromObject = u.serialize();
    assertEquals(json, fromObject);

    // Test the getters (including those with logic)
    assertEquals(u.getId(), "gjasgk");
    assertEquals(u.getUsername(), "337189857");
    assertEquals(u.getFirstName(), "Hanß");
    assertEquals(u.getLastName(), "Heinrich");
    assertEquals(u.getEmail(), "hanssheinrichist@cool.yeah");
    assertEquals(u.getCreatedAt(), createdAt);
    assertEquals(u.getUpdatedAt(), updatedAt);
    assertEquals(u.getDeletedAt(), deletedAt);
  }

  @Test
  public void testReadingConversion() {
    String json = readFromDisk("Reading.json");

    DateTime createdAt = new DateTime(1897, 01, 17, 12, 16, 25, DateTimeZone.forID("Europe/Berlin"));
    DateTime updatedAt = new DateTime(1938, 03, 11, 11, 43, 21, DateTimeZone.forID("Europe/Berlin"));
    DateTime deletedAt = new DateTime(1999, 11, 21, 8, 11, 17, DateTimeZone.forID("Europe/Berlin"));


    // Test if converting the json back-and forth causes any issues
    Reading r = Reading.deserialize(json);
    String fromObject = r.serialize();
    assertEquals(json, fromObject);

    // Test the getters (including those with logic)
    assertEquals(r.getId(), "asdgwhgwo");
    assertEquals(r.getMeterId(), "beibeva");
    assertEquals(r.getOwnerId(), "kkanaöböa");
    assertEquals(r.getValue(), "86384,956");
    assertEquals(r.getTrend(), 1000);
    assertEquals(r.getLastEditorName(), "Dieter");
    assertEquals(r.getLastEditReason(), "Brutale Killerspiele");
    assertEquals(r.getCreatedAt(), createdAt);
    assertEquals(r.getUpdatedAt(), updatedAt);
    assertEquals(r.getDeletedAt(), deletedAt);

  }

  @Test
  public void testIssueConversion() {
    String json = readFromDisk("Issue.json");

    DateTime createdAt = new DateTime(2200, 4, 2, 16, 23, 37, DateTimeZone.forID("Europe/Berlin"));
    DateTime updatedAt = null;
    DateTime deletedAt = null;

    // Test if converting the json back-and forth causes any issues
    Issue i = Issue.deserialize(json);
    String fromObject = i.serialize();
    assertEquals(json, fromObject);

    // Test the getters (including those with logic)
    assertEquals(i.getId(), "ohfgoasgoag");
    assertEquals(i.getEmail(), "citizen23571825@rome.net");
    assertEquals(i.getName(), "Roman Citizen");
    assertEquals(i.getSubject(), "to constantine");
    assertEquals(i.getMessage(), "Hi, I live in the new Roman Empire, and I was wondering: IS LOVING JESUS LEGAL YET?");
    assertEquals(i.getStatus(), "UNRESOLVED");
    assertEquals(i.getCreatedAt(), createdAt);
    assertTrue(samesame(i.getUpdatedAt(), updatedAt));
    assertTrue(samesame(i.getDeletedAt(), deletedAt));

  }

  @Test
  public void testMeterConversion() {
    String json = readFromDisk("Meter.json");

    DateTime createdAtMeter = new DateTime(1897, 1, 17, 12, 16, 25, DateTimeZone.forID("Europe/Berlin"));
    DateTime updatedAtMeter = new DateTime(1938, 3, 11, 11, 43, 21, DateTimeZone.forID("Europe/Berlin"));
    DateTime deletedAtMeter = new DateTime(2010, 10, 21, 9, 30, 1, DateTimeZone.forID("Europe/Berlin"));

    DateTime createdAtReading = new DateTime(1938, 3, 11, 11, 43, 21, DateTimeZone.forID("Europe/Berlin"));
    DateTime updatedAtReading = new DateTime(1938, 3, 11, 11, 43, 21, DateTimeZone.forID("Europe/Berlin"));
    DateTime deletedAtReading = new DateTime(2007, 3, 4, 10, 26, 45, DateTimeZone.forID("Europe/Berlin"));

    // Test if converting the json back-and forth causes any issues
    Meter m = Meter.deserialize(json);
    String fromObject = m.serialize();
    assertEquals(json, fromObject);

    // Test the getters (including those with logic)
    assertEquals(m.getId(), "h8aghawo");
    assertEquals(m.getMeterNumber(), "123456");
    assertEquals(m.getKind(), MeterKind.WATER);
    assertEquals(m.getName(), "Hauptsitz");
    assertEquals(m.getOwnerId(), "asdihawig");
    assertTrue(samesame(m.getCreatedAt(), createdAtMeter));
    assertTrue(samesame(m.getUpdatedAt(), updatedAtMeter));
    assertTrue(samesame(m.getDeletedAt(), deletedAtMeter));

    // Internal Readings
    assertTrue(samesame(m.getLastReading().getId(), "asdgwhgwo"));
    assertTrue(samesame(m.getLastReading().getMeterId(), "beibeva"));
    assertTrue(samesame(m.getLastReading().getOwnerId(), "kkanaöböa"));
    assertTrue(samesame(m.getLastReading().getValue(), "86384,956"));
    assertEquals(m.getLastReading().getTrend(), 1000);
    assertTrue(samesame(m.getLastReading().getLastEditorName(), "Coldmirror"));
    assertTrue(samesame(m.getLastReading().getLastEditReason(), "Brutale Killerspiele"));
    assertTrue(samesame(m.getLastReading().getCreatedAt(), createdAtReading));
    assertTrue(samesame(m.getLastReading().getUpdatedAt(), updatedAtReading));
    assertTrue(samesame(m.getLastReading().getDeletedAt(), deletedAtReading));

  }


  private String readFromDisk(String name) {
    String wd = System.getProperty("user.dir");
    String re = "";
    try {
      re = new String(Files.readAllBytes(Paths.get(wd + basePath + name)));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return re;
  }

  /**
   * like assertEquals, but doesn't throw NullPointerExceptions when both are null
   */
  private boolean samesame(Object actual, Object expected) {
    if (actual == null && expected == null)
      return true;

    if (actual.equals(expected))
      return true;

    return false;
  }
}
