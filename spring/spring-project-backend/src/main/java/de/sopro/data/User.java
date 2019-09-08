package de.sopro.data;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="person")
public class User extends Person {

	public User(String firstName, String lastName, String eMailAddress, String username, String password,
			Role role) {
		super(username, password, role);
		this.firstName = firstName;
		this.lastName = lastName;
		this.eMailAddress = eMailAddress;
		createdAt = LocalDateTime.now();
	}

	public User() {
		super();
	}

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	private String eMailAddress;

	@NotNull
	private LocalDateTime createdAt;

	private LocalDateTime deletedAt;

	private LocalDateTime updatedAt;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEMailAddress() {
		return eMailAddress;
	}

	public void setEMailAddress(String eMailAddress) {
		this.eMailAddress = eMailAddress;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
