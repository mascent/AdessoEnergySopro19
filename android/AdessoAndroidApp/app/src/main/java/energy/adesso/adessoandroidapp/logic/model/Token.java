package energy.adesso.adessoandroidapp.logic.model;

public class Token extends SerializableObject {
  String access_token;
  String expires_in;
  String token_type;
  String scope;

  public boolean hasExpired() {
    // TODO: implement
    return false;
  }

  public String getToken() {
    return access_token;
  }

  public String getExpiration() {
    return expires_in;
  }

  public String getRole() {
    return token_type;
  }
}
