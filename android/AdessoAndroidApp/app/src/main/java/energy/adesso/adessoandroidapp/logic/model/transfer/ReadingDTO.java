package energy.adesso.adessoandroidapp.logic.model.transfer;


import androidx.annotation.Nullable;

public class ReadingDTO extends DTO {

  public final String meterId;
  public final String ownerId;
  public final String value;
  public final String lastEditorName;
  public final String lastEditReason;
  public final int trend;
  // to get rid of the @Nullable
  public String createdAt;

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
