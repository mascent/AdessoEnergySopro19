package de.sopro.dto;

import de.sopro.data.Issue;

public class IssueDTO {

	private Long id;
	private String name;
	private String email;
	private String subject;
	private String  message;
	private Long issuerId;
	private Boolean isClosed;

	public IssueDTO(Issue i) {
		this.id = i.getIssueId();
		this.name = i.getName();
		this.email = i.getEmail();
		this.subject = i.getSubject();
		this.message = i.getMessage();
		this.issuerId = i.getIssuerId();
		this.isClosed = i.getCloserId() != null;
	}

	public IssueDTO() {
		
	}
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getSubject() {
		return subject;
	}

	public String getMessage() {
		return message;
	}

	public Long getIssuerId() {
		return issuerId;
	}

	public Boolean getIsClosed() {
		return isClosed;
	}

}
