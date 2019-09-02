package energy.adesso.adessoandroidapp.logic.model.internal;

import androidx.annotation.Nullable;

import org.joda.time.DateTime;

public abstract class InternalObject {
  private final DateTime createdAt;
  @Nullable
  private DateTime updatedAt;
  @Nullable
  private DateTime deletedAt;
  private final long id;

  public InternalObject(long id, String createdAt, String updatedAt, String deletedAt) {
    // TODO: check if this parse works ^^
    this.createdAt = DateTime.parse(createdAt);
    this.updatedAt = DateTime.parse(updatedAt);
    this.deletedAt = DateTime.parse(deletedAt);
    this.id = id;
  }

  public InternalObject(long id, DateTime createdAt, DateTime updatedAt, DateTime deletedAt) {

    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.deletedAt = deletedAt;
    this.id = id;
  }


  public DateTime getCreatedAt() {
    return createdAt;
  }

  public long getId() {
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
