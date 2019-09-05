package de.sopro.data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Issue {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String issueID;

	private String name;

	private String subject;

	private String message;
	
	private String closerId;
	
	private String issuerId;

	public Issue(String issueID, String name, String subject, String message, String issuerId) {
		super();
		this.issueID = issueID;
		this.name = name;
		this.subject = subject;
		this.message = message;
		this.issuerId = issuerId;
	}


	public String getIssueID() {
		return issueID;
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