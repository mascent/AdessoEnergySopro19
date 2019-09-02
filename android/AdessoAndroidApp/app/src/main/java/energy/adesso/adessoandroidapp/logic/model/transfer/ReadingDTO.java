package energy.adesso.adessoandroidapp.logic.model.transfer;


public class ReadingDTO extends DTO {

  public String meterId;
  public String ownerId;
  public String value;
  int trend;
  String lastEditorName;
  String lastEditReason;

  @Override
  protected boolean validateSpecifics() {
    if (!stringCheck(meterId, ownerId, value))
      return false;
    return true;
  }

}
