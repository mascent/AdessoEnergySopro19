package energy.adesso.adessoandroidapp.logic.model.transfer;

import java.util.Arrays;
import java.util.List;

public class MeterDTO extends DTO {
  String type;
  String name;
  String ownerId;
  energy.adesso.app.logic.model.transfer.ReadingDTO lastReading; // nullable
  String meterNumber;

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
