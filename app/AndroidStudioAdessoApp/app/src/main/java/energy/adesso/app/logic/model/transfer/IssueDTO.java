package energy.adesso.app.logic.model.transfer;


public class IssueDTO extends energy.adesso.app.logic.model.transfer.DTO {

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
