package de.sopro.dto;

import java.util.Date;

import de.sopro.data.User;

public class UserDTO {

	private Long id;
	private String customerNumber;
	private String firstName;
	private String lastName;
	private String email;
	private Date createdAt;
	private Date updateAt;
	private Date deletedAt;

	public UserDTO(User u) {
		this.id = u.getPersonID();
		this.customerNumber = u.getUsername();
		this.firstName = u.getName();
		this.lastName = u.getSurname();
		this.email = u.getEMailAddress();
		this.createdAt = u.getCreatedAt();
		this.updateAt = u.getUpdatedAt();
		this.deletedAt = u.getDeletedAt();

	}

	public Long getId() {
		return id;
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public Date getDeletedAt() {
		return deletedAt;
	}
}
