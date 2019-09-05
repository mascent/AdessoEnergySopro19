package energy.adesso.adessoandroidapp.logic.model;

public class Token extends SerializableObject{
  String token;
  String ExpirationDate;
  String renew;
  String Role;
  public boolean hasExpired() {
    // TODO: implement
    return false;
  }

  public String getToken() {
    return token;
  }

  public String getExpirationDate() {
    return ExpirationDate;
  }

  public String getRenew() {
    return renew;
  }

  public String getRole() {
    return Role;
  }
}
