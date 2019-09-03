package energy.adesso.adessoandroidapp.logic.model.internal;

import androidx.annotation.Nullable;

import org.joda.time.DateTime;

import energy.adesso.adessoandroidapp.logic.model.transfer.DTO;

public abstract class InternalObject {
  private final DateTime createdAt;
  @Nullable
  private DateTime updatedAt;
  @Nullable
  private DateTime deletedAt;
  private final String id;

  public InternalObject(String id, String createdAt, String updatedAt, String deletedAt) {
    // TODO: check if this parse works ^^
    this.createdAt = DateTime.parse(createdAt);
    this.updatedAt = DateTime.parse(updatedAt);
    this.deletedAt = DateTime.parse(deletedAt);
    this.id = id;
  }

  public InternalObject(DTO dto){
    // TODO: check if this parse works ^^
    this.createdAt = DateTime.parse(dto.createdAt);
    this.updatedAt = DateTime.parse(dto.updatedAt);
    this.deletedAt = DateTime.parse(dto.deletedAt);
    this.id = dto.id;
  }

  public InternalObject(String id, DateTime createdAt, DateTime updatedAt, DateTime deletedAt) {

    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.deletedAt = deletedAt;
    this.id = id;
  }


  public DateTime getCreatedAt() {
    return createdAt;
  }

  public String getId() {
    return id;
  }


  @Nullable
  public DateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(DateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Nullable
  public DateTime getDeletedAt() {
    return deletedAt;
  }

  public void setDeletedAt(DateTime deletedAt) {
    this.deletedAt = deletedAt;
  }


}
