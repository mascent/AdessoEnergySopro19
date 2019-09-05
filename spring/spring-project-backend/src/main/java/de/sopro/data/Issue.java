package de.sopro.data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Issue {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String issueId;

    @NotNull
	private String name;
	
	@NotNull
    private String email;
    
    @NotNull
	private String subject;

	@NotNull
	private String description;
	
	private String closerId;
	
	@NotNull
	private String issuerId;

	public Issue(String name, String email, String subject, String description, String issuerId) {
		this.name = name;
		this.email = email;
		this.subject = subject;
		this.description = description;
		this.issuerId = issuerId;
	}


	public String getIssueId() {
		return issueID;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCloserId() {
		return closerId;
	}

	public void setCloserId(String closerId) {
		this.closerId = closerId;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}
	
	


}