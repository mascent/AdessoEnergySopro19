package energy.adesso.adessoandroidapp.logic.model.identifiable;


import energy.adesso.adessoandroidapp.logic.controller.MainController;
import energy.adesso.adessoandroidapp.logic.model.exception.NetworkException;

public class Issue extends IdentifiableObject {

  private String email;
  private String name;
  private String subject;
  private String message;
  private String status;

  public Issue(String id) {
    super(id);
  }


  public void send() throws NetworkException {
    MainController.getInstance().sendIssue(this);
  }

  public static Issue deserialize(String source){
    return gson.fromJson(source, Issue.class);
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getStatus() {
    return status;
  }

}
