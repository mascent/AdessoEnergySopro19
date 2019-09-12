package energy.adesso.adessoandroidapp.logic.model.identifiable;


import androidx.annotation.Nullable;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.io.Serializable;

import energy.adesso.adessoandroidapp.logic.model.SerializableObject;

public abstract class IdentifiableObject extends SerializableObject implements Serializable {
  private long id;
  @Nullable
  protected String updatedAt;
  @Nullable
  protected String createdAt;
  @Nullable
  protected String deletedAt;

  //TODO: FLOCKING TIME ZONES
  final static transient DateTimeFormatter dateTimeStrategy = ISODateTimeFormat.dateTime().withZone(DateTimeZone.forID("Europe/Berlin"));

  public IdentifiableObject(long id) {
    this.id = id;
  }

  public IdentifiableObject(long id, DateTime createdAt, DateTime updatedAt, DateTime deletedAt) {
    this.id = id;
    this.createdAt = createdAt.toString(dateTimeStrategy);
    this.updatedAt = updatedAt.toString(dateTimeStrategy);
    this.deletedAt = deletedAt.toString(dateTimeStrategy);
  }

  public DateTime getCreatedAt() {
    return parse(createdAt);
  }

  public DateTime getUpdatedAt() {
    return parse(updatedAt);
  }

  public DateTime getDeletedAt() {
    return parse(deletedAt);
  }

  public long getId() {
    return id;
  }

  public static IdentifiableObject deserialize(String source) {
    return gson.fromJson(source, IdentifiableObject.class);
  }

  public static DateTimeFormatter getDateTimeStrategy() {
    return dateTimeStrategy;
  }

  private DateTime parse(String s) {
    if (s == null || s.equals(""))
      return null;
    else return DateTime.parse(s, dateTimeStrategy);
  }


}
