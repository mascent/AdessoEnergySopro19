package de.sopro.dto;

import java.time.LocalDateTime;

import de.sopro.data.User;

public class UserDTO {

	private Long id;
	private String customerNumber;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	public String getPassword() {
		return password;
	}

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDateTime deletedAt;

	public UserDTO() {
		
	}
	
	public UserDTO(User u) {
		this.id = u.getPersonId();
		this.customerNumber = u.getUsername();
		this.firstName = u.getFirstName();
		this.lastName = u.getLastName();
		this.email = u.getEMailAddress();
		this.createdAt = u.getCreatedAt();
		this.updatedAt = u.getUpdatedAt();
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}

}
