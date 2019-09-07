package de.sopro.dto;

import de.sopro.data.Issue;

public class IssueDTO {

	private Long id;
	private String name;
	private String email;
	private String subject;
	private String  description;
	private Long issuerId;
	private Long closerId;

	public IssueDTO(Issue i) {
		this.id = i.getIssueId();
		this.name = i.getName();
		this.email = i.getEmail();
		this.subject = i.getSubject();
		this.description = i.getDescription();
		this.issuerId = i.getIssuerId();
		this.closerId = i.getCloserId();
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

	public String getDescription() {
		return description;
	}

	public Long getIssuerId() {
		return issuerId;
	}

	public Long getCloserId() {
		return closerId;
	}

}
