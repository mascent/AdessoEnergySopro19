package energy.adesso.adessoandroidapp.logic.model.internal;

import energy.adesso.adessoandroidapp.logic.model.exception.CooldownException;
import energy.adesso.adessoandroidapp.logic.model.exception.CredentialException;
import energy.adesso.adessoandroidapp.logic.model.exception.NetworkException;

public class Reading extends InternalObject{

  String value;
  private Reading(Builder builder){
    super(builder);
    this.value = builder.value;
  }

  public static class Builder extends InternalObject.Builder{
    public Builder(long id){
      super(id);
    }

    private String value;

    public Builder value(String value){
      this.value = value;
      return this;
    }

    public Reading build(){
      return new Reading(this);
    }
  }

  public void correct(String newValue) throws NetworkException, CredentialException, CooldownException {

  }

}
