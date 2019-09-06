package de.sopro.data;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class User extends Person {

	public User(String name, String surname, String eMailAddress, String userNumber, String username, String password,
			Role role) {
		super(username, password, role);
		this.name = name;
		this.surname = surname;
		this.eMailAddress = eMailAddress;
		this.userNumber = userNumber;
		createdAt = LocalDateTime.now();
	}

	@NotNull
	private String name;

	@NotNull
	private String surname;

	@NotNull
	private String eMailAddress;

	@NotNull
	private String userNumber;

	@NotNull
	private LocalDateTime createdAt;

	private LocalDateTime deletedAt;

	private LocalDateTime updatedAt;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEMailAddress() {
		return eMailAddress;
	}

	public void setEMailAddress(String eMailAddress) {
		this.eMailAddress = eMailAddress;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
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
