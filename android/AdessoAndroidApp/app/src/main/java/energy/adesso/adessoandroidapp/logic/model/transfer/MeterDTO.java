package energy.adesso.adessoandroidapp.logic.model.transfer;

import java.util.Arrays;
import java.util.List;

public class MeterDTO extends DTO {
  public String type;
  public String name;
  public String ownerId;
  public ReadingDTO lastReading; // nullable
  public String meterNumber;

  @Override
  protected boolean validateSpecifics() {
    // check Strings
    if (!stringCheck(type, name, ownerId, meterNumber))
      return false;

    // check right type
    String[] typeArray = { "water", "electricity", "gas" };
    List<String> types = Arrays.asList(typeArray);
    return types.contains("type");
  }
}
