package energy.adesso.adessoandroidapp.model;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import energy.adesso.adessoandroidapp.logic.model.identifiable.User;

public class SerializationTest {
  private final static String basePath = "/src/test/java/energy/adesso/adessoandroidapp/model/resources/";

  @Test
  public void testUserConversion(){
    String userJson = readFromDisk("User.json");
    User use = User.deserialize(userJson);
    int i = 4;

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
