package energy.adesso.adessoandroidapp.logic.model.internal;


import androidx.annotation.Nullable;

import org.joda.time.DateTime;

import java.util.Calendar;
import java.util.Date;

public abstract class InternalObject {
  private final DateTime createdAt;
  @Nullable
  private DateTime updatedAt;
  @Nullable
  private DateTime deletedAt;
  private long id;

  public InternalObject(long id, String createdAt, String updatedAt, String deletedAt) {
    // TODO: check if this parse works ^^
    this.createdAt = DateTime.parse(createdAt);
    this.updatedAt = DateTime.parse(updatedAt);
    this.deletedAt = DateTime.parse(deletedAt);
  }


  public DateTime getCreatedAt() {
    return createdAt;
  }

  public long getId() {
    return id;
  }


  public DateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(DateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public DateTime getDeletedAt() {
    return deletedAt;
  }

  public void setDeletedAt(DateTime deletedAt) {
    this.deletedAt = deletedAt;
  }


}
