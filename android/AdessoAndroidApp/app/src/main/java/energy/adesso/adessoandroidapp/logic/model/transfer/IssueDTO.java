package energy.adesso.adessoandroidapp.logic.model.transfer;


public class IssueDTO extends DTO {

  String email;
  String name;
  String subject;
  String message;
  String status;

  @Override
  protected boolean validateSpecifics() {
    if (!stringCheck(email, name, subject, message, status))
      return false;
    return true;
  }

}
