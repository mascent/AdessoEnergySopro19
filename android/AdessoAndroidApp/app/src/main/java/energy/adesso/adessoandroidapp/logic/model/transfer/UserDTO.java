package energy.adesso.adessoandroidapp.logic.model.transfer;


import energy.adesso.adessoandroidapp.logic.model.transfer.DTO;

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
