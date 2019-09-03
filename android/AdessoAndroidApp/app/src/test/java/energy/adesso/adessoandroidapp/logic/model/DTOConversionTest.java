package energy.adesso.adessoandroidapp.logic.model;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;

import energy.adesso.adessoandroidapp.logic.model.internal.Reading;
import energy.adesso.adessoandroidapp.logic.model.transfer.ReadingDTO;

public class DTOConversionTest {
  static String json;

  static Gson gson;

  @Before
  public void init(){
    json = "{\n" +
            "  \"email\": \"email@email.com\",\n" +
            "  \"createdAt\": \"2015-12-03T10:15:30.120Z\",\n" +
            "  \"id\":\"lol omg cool\",\n" +
            "  \"meterId\": \"dfa\",\n" +
            "  \"ownerId\": \"oufhas\"\n" +
            "}";
    gson = new Gson();
  }

  @Test
  public void testToDto() {
    ReadingDTO dto = gson.fromJson(json, ReadingDTO.class);
    int i = 2;
  }

}
