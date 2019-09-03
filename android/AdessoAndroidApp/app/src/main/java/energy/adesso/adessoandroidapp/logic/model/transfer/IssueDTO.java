package energy.adesso.adessoandroidapp.logic.model.transfer;


import energy.adesso.adessoandroidapp.logic.model.internal.Issue;

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

  public IssueDTO(Issue i) {
    super(i);
    this.email = i.getEmail();
    this.name = i.getName();
    this.subject = i.getSubject();
    this.message = i.getMessage();
    this.status = i.getStatus().name();
  }

  @Override
  protected boolean validateSpecifics() {
    if (!stringCheck(email, name, subject, message, status))
      return false;

    if (!(status.equals("UNRESOLVED") || status.equals("RESOLVED")))
      return false;

    return true;
  }

}
