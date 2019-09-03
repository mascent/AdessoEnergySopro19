package energy.adesso.adessoandroidapp.logic.model.internal;

import org.joda.time.DateTime;

import energy.adesso.adessoandroidapp.logic.model.transfer.IssueDTO;

public class Issue extends InternalObject {
  private String email;
  private String name;
  private String subject;
  private String message;
  private ResolvedStatus status;

  public Issue(String id, String createdAt, String updatedAt, String deletedAt) {
    super(id, createdAt, updatedAt, deletedAt);
  }

  public Issue(IssueDTO dto) {
    super(dto);
    this.email = dto.email;
    this.name = dto.name;
    this.subject = dto.subject;
    this.message = dto.message;
    switch (dto.status) {
      case "RESOLVED":
        this.status = ResolvedStatus.RESOLVED;
        break;
      case "UNRESOLVED":
        this.status = ResolvedStatus.UNRESOLVED;
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + dto.status);
    }
  }

  public Issue(String id, DateTime createdAt, DateTime updatedAt, DateTime deletedAt) {
    super(id, createdAt, updatedAt, deletedAt);
  }

  public String getEmail() {
    return email;
  }

  public String getName() {
    return name;
  }

  public String getSubject() {
    return subject;
  }

  public String getMessage() {
    return message;
  }

  public ResolvedStatus getStatus() {
    return status;
  }

  public void setStatus(ResolvedStatus status) {
    this.status = status;
  }
}
