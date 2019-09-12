package energy.adesso.adessoandroidapp.logic.model.exception;

/**
 * A class representing an exception where the User is not logged in properly.
 */
public class CredentialException extends AdessoException {
  public CredentialException(String message) {
    super(message);
  }

  public CredentialException() {
    super("");
  }
}
