package energy.adesso.app.logic.model.transfer;


public class UserDTO extends DTO {

  String customerId;
  String firstName;
  String lastName;
  String email;

  @Override
  protected boolean validateSpecifics() {
    if (!stringCheck(customerId, firstName, lastName, email))
      return false;
    return true;
  }

}
