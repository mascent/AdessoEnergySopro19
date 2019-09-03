package energy.adesso.adessoandroidapp.logic.model.transfer;


import androidx.annotation.Nullable;

import energy.adesso.adessoandroidapp.logic.model.internal.Reading;

public class ReadingDTO extends DTO {

  // TODO: make final
  public String meterId;
  public String ownerId;
  public String value;
  public String lastEditorName;
  public String lastEditReason;
  public int trend;
  // to get rid of the @Nullable

  /**
   * Minimum Viable Constructor
   * @param id
   * @param createdAt
   * @param meterId
   * @param ownerId
   * @param value
   */
  public ReadingDTO(String id, @Nullable String createdAt,String meterId, String ownerId, String value, String lastEditorName, String lastEditReason) {
    super(id, createdAt, null, null);
    this.meterId = meterId;
    this.ownerId = ownerId;
    this.value = value;
    this.lastEditorName = lastEditorName;
    this.lastEditReason = lastEditReason;
    this.trend = -1;
  }

  /**
   * Constructor with all Arguments
   * @param id
   * @param createdAt
   * @param updatedAt
   * @param deletedAt
   * @param meterId
   * @param ownerId
   * @param value
   * @param trend
   * @param lastEditorName
   * @param lastEditReason
   */
  public ReadingDTO(String id, @Nullable String createdAt, @Nullable String updatedAt, @Nullable String deletedAt, String meterId, String ownerId, String value, int trend, @Nullable String lastEditorName, @Nullable String lastEditReason) {
    super(id, createdAt, updatedAt, deletedAt);
    this.meterId = meterId;
    this.ownerId = ownerId;
    this.value = value;
    this.trend = trend;
    this.lastEditorName = lastEditorName;
    this.lastEditReason = lastEditReason;
  }

  ReadingDTO(Reading r){
    super(r);
    this.value = r.getValue();
  }

  /**
   * Minimum Viable Constructor
   * @return
   */

  @Override
  protected boolean validateSpecifics() {
    if (!stringCheck(value,ownerId,meterId))
      return false;

    return true;
  }

}
