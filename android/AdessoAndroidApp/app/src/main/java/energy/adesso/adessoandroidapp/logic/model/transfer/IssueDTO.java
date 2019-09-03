package energy.adesso.adessoandroidapp.logic.model.transfer;


public class IssueDTO extends DTO {

  public String email;
  public String name;
  public String subject;
  public String message;
  public String status;

  public IssueDTO(String id, String createdAt, String updatedAt, String deletedAt, String email, String name, String subject, String message, String status) {
    super(id, createdAt, updatedAt, deletedAt);
    this.email = email;
    this.name = name;
    this.subject = subject;
    this.message = message;
    this.status = status;
  }

  @Override
  protected boolean validateSpecifics() {
    if (!stringCheck(email, name, subject, message, status))
      return false;

    if (!(status.equals("UNRESOLVED")||status.equals("RESOLVED")))
      return false;

    return true;
  }

}
