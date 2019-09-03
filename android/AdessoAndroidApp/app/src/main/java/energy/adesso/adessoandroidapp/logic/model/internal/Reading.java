package energy.adesso.adessoandroidapp.logic.model.internal;

import energy.adesso.adessoandroidapp.logic.model.exception.CooldownException;
import energy.adesso.adessoandroidapp.logic.model.exception.CredentialException;
import energy.adesso.adessoandroidapp.logic.model.exception.NetworkException;
import energy.adesso.adessoandroidapp.logic.model.transfer.ReadingDTO;

public class Reading extends InternalObject {

  String value;

  public Reading(String id, String createdAt, String updatedAt, String deletedAt, String value) {
    super(id, createdAt, updatedAt, deletedAt);
    this.value = value;
  }

  public Reading(ReadingDTO dto) {
    super(dto.id, dto.createdAt, dto.updatedAt, dto.deletedAt);
    this.value = dto.value;
  }

  public void correct(String newValue) throws NetworkException, CredentialException, CooldownException {

  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
