package energy.adesso.adessoandroidapp.logic.model.transfer;

import androidx.annotation.Nullable;

import java.util.Arrays;
import java.util.List;

public class MeterDTO extends DTO {
  public String type;
  public String name;
  @Nullable
  public String ownerId;
  public ReadingDTO lastReading; // nullable
  public String meterNumber;

  /**
   *
   * @param id as given by us
   * @param createdAt
   * @param updatedAt
   * @param deletedAt
   * @param meterNumber
   * @param type
   * @param ownerId
   * @param lastReading
   */
  public MeterDTO(String id, String createdAt, String updatedAt, String deletedAt, String meterNumber, String type, @Nullable String ownerId, ReadingDTO lastReading) {
    super(id,createdAt,updatedAt,deletedAt);
    this.meterNumber = meterNumber;
    this.type = type = type;
    this.ownerId = ownerId;
    this.lastReading = lastReading;
  }

  @Override
  protected boolean validateSpecifics() {
    // check Strings
    if (!stringCheck(type, name, meterNumber))
      return false;


    // check right type
    String[] typeArray = {"water", "electricity", "gas"};
    List<String> types = Arrays.asList(typeArray);
    return types.contains("type");
  }
}
