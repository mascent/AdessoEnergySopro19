package de.sopro.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Issue {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long issueId;

	@NotNull
	private String name;

	@NotNull
	private String email;

	@NotNull
	private String subject;

	@NotNull
	private String message;

	private Long closerId;

	@NotNull
	private Long issuerId;

	public Issue(String name, String email, String subject, String message, Long issuerId) {
		this.name = name;
		this.email = email;
		this.subject = subject;
		this.message = message;
		this.issuerId = issuerId;
	}

	public Issue() {

	}

	public Long getIssueId() {
		return issueId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Long getCloserId() {
		return closerId;
	}

	public void setCloserId(Long closerId) {
		this.closerId = closerId;
	}

	public Long getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(Long issuerId) {
		this.issuerId = issuerId;
	}

}