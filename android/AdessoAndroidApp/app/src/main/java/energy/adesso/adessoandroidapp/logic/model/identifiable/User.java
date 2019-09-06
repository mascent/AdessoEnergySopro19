package energy.adesso.adessoandroidapp.logic.model.identifiable;


public class User extends IdentifiableObject {
  private String customerNumber;
  private String firstName;
  private String lastName;
  private String email;

  public User(String id) {
    super(id);
  }

  public static User deserialize(String source) {
    return gson.fromJson(source, User.class);
  }

  public String getCustomerNumber() {
    return customerNumber;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }
}
