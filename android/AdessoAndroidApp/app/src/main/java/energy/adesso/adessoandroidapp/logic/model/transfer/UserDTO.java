package energy.adesso.adessoandroidapp.logic.model.transfer;


import androidx.annotation.Nullable;

import energy.adesso.adessoandroidapp.logic.model.transfer.DTO;

public class UserDTO extends DTO {
  String customerNumber;
  String firstName;
  String lastName;
  String email;


  public UserDTO(String id, @Nullable String createdAt, @Nullable String updatedAt, @Nullable String deletedAt, String customerId, String firstName, String lastName, String email) {
    super(id, createdAt, updatedAt, deletedAt);
    this.customerNumber = customerNumber;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  @Override
  protected boolean validateSpecifics() {
    if (!stringCheck(customerNumber, firstName, lastName, email))
      return false;
    return true;
  }

}
