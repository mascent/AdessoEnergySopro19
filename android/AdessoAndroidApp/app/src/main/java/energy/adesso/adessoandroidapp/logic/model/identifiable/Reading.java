package energy.adesso.adessoandroidapp.logic.model.identifiable;


public class Reading extends IdentifiableObject {

  private final String meterId;
  private final String ownerId;
  private String value;
  private String lastEditorName;
  private String lastEditReason;
  private int trend;

  public Reading(String id, String meterId, String ownerId, String value) {
    super(id);
    this.meterId = meterId;
    this.ownerId = ownerId;
    this.value = value;
  }

  public void correct(String newValue) {
//    TODO
  }

  public String getMeterId() {
    return meterId;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public String getValue() {
    return value;
  }

  public String getLastEditorName() {
    return lastEditorName;
  }

  public String getLastEditReason() {
    return lastEditReason;
  }

  public int getTrend() {
    return trend;
  }
}
