package energy.adesso.adessoandroidapp.logic.model.transfer;


public class ReadingDTO extends DTO {

  String meterId;
  String ownerId;
  String value;
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
