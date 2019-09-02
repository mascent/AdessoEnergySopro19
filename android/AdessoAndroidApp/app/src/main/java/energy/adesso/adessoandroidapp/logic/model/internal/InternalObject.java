package energy.adesso.adessoandroidapp.logic.model.internal;


import java.util.Date;

public abstract class InternalObject {
  private final Date createdAt;
  private Date updatedAt;
  private Date deletedAt;
  private final long id;

  public static class Builder{
    protected Date createdAt;
    protected Date updatedAt;
    protected Date deletedAt;
    protected long id;

    public Builder(long id){
      // ID is the only _really_ required field imo
      this.id = id;
    }

    public Builder createdAt(Date createdAt){
      this.createdAt = createdAt;
      return this;
    }

    public Builder updatedAt(Date updatedAt){
      this.updatedAt = updatedAt;
      return this;
    }

    public Builder deletedAt(Date deletedAt) {
      this.deletedAt = deletedAt;
      return this;
    }

  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public long getId() {
    return id;
  }


  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Date getDeletedAt() {
    return deletedAt;
  }

  public void setDeletedAt(Date deletedAt) {
    this.deletedAt = deletedAt;
  }


  public InternalObject(Builder builder){
    this.createdAt = builder.createdAt;
    this.deletedAt = builder.deletedAt;
    this.updatedAt = builder.updatedAt;
    this.id = builder.id;
  }

}
