package energy.adesso.adessoandroidapp.logic.model.exception;

/**
 * A class representing an exception where the User is trying to do something too often.
 */
public class CooldownException extends AdessoException {
  public CooldownException(String message) {
    super(message);
  }

  public CooldownException(){
    super("");
  }
}
