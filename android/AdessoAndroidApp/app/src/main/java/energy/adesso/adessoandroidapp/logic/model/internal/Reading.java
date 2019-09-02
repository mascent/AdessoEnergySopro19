package energy.adesso.adessoandroidapp.logic.model.internal;

import energy.adesso.adessoandroidapp.logic.model.exception.CooldownException;
import energy.adesso.adessoandroidapp.logic.model.exception.CredentialException;
import energy.adesso.adessoandroidapp.logic.model.exception.NetworkException;

public class Reading extends InternalObject{

  String value;

  public Reading(long id, String createdAt, String updatedAt, String deletedAt, String value){
    super(id,createdAt,updatedAt,deletedAt);
    this.value = value;
  }

  public void correct(String newValue) throws NetworkException, CredentialException, CooldownException {

  }

}
