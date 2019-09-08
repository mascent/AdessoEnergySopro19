package energy.adesso.adessoandroidapp.model;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import energy.adesso.adessoandroidapp.logic.model.identifiable.Issue;
import energy.adesso.adessoandroidapp.logic.model.identifiable.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SerializationTest {
  private final static String basePath = "/src/test/java/energy/adesso/adessoandroidapp/model/resources/";

  @Test
  public void testUserConversion(){
    String userJson = readFromDisk("User.json");

    DateTime createdAt = new DateTime(320, 4,2,16,23,37);
    DateTime updatedAt = new DateTime(1938, 3,11,11,43,21);
    DateTime deletedAt = new DateTime(1999, 11,21,8,11,17);
    String time = createdAt.toString(ISODateTimeFormat.dateTime());

    User u = User.deserialize(userJson);
    int i = 4;

    assertEquals(u.getId(), "gjasgk");
    assertEquals(u.getCustomerNumber(), "337189857");
    assertEquals(u.getFirstName(), "Hanß");
    assertEquals(u.getLastName(), "Heinrich");
    assertEquals(u.getEmail(), "hanssheinrichist@cool.yeah");
    assertEquals(u.getCreatedAt(), createdAt);
    assertEquals(u.getUpdatedAt(), updatedAt);
    assertEquals(u.getDeletedAt(), deletedAt);
  }





  private String readFromDisk(String name){
    String wd = System.getProperty("user.dir");
    String re = "";
    try {
      re = new String(Files.readAllBytes(Paths.get(wd + basePath + name)));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return re;
  }

}
