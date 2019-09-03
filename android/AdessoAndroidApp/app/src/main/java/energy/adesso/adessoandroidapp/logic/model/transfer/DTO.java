package energy.adesso.adessoandroidapp.logic.model.transfer;


import androidx.annotation.Nullable;

import energy.adesso.adessoandroidapp.logic.model.internal.InternalObject;

public abstract class DTO {
  public final String id;
  @Nullable
  public String updatedAt;
  @Nullable
  public String createdAt;
  @Nullable
  public String deletedAt;


  /**
   * Constructor with all parameters
   *
   * @param id
   * @param createdAt
   * @param updatedAt
   * @param deletedAt
   */
  public DTO(String id, @Nullable String createdAt, @Nullable String updatedAt, @Nullable String deletedAt) {
    this.id = id;
    this.updatedAt = updatedAt;
    this.createdAt = createdAt;
    this.deletedAt = deletedAt;
  }

  /**
   * Minimum Viable Constructor
   *
   * @param id
   */
  public DTO(String id) {
    this.id = id;
  }

  protected abstract boolean validateSpecifics();

  public boolean validate() {

    if (!stringCheck(createdAt, id))
      return false;

    if (!validateSpecifics())
      return false;

    return true;
  }

  public DTO(InternalObject o) {
    this.id = o.getId();
    this.createdAt = o.getCreatedAt().toString();
    try {
      this.updatedAt = o.getUpdatedAt().toString();
    } catch (NullPointerException e) {
      this.updatedAt = null;
    }
    try {
      this.deletedAt = o.getDeletedAt().toString();
    } catch (NullPointerException e) {
      this.deletedAt = null;
    }
  }

  protected boolean stringCheck(String... strings) {
    for (String s : strings) {
      if (s == null || s.contentEquals(""))
        return false;
    }
    return true;
  }

}
