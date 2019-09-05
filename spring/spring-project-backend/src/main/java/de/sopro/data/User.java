package de.sopro.data;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class User extends Person{

public User(String name, 
			String surname, 
			String eMailAdress, 
			String userNumber,
			String username, 
			String password, 
			Role role) {
	super(username, password, role);
	this.name = name;
	this.surname = surname;
	this.eMailAdress = eMailAdress;
	this.userNumber = userNumber;
	// createdAt = now;
}

@NotNull
private String name;

@NotNull
private String surname;

@NotNull
private String eMailAdress;

@NotNull
private String userNumber;

@Past
@NotNull
private Date createdAt;

private Date deleatedAt;

private Date updatedAt;

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

public String geteMailAdress() {
	return eMailAdress;
}

public void seteMailAdress(String eMailAdress) {
	this.eMailAdress = eMailAdress;
}

public String getUserNumber() {
	return userNumber;
}

public void setUserNumber(String userNumber) {
	this.userNumber = userNumber;
}

public Date getCreatedAt() {
	return createdAt;
}

public Date getDeleatedAt() {
	return deleatedAt;
}

public void setDeleatedAt(Date deleatedAt) {
	this.deleatedAt = deleatedAt;
}

public Date getUpdatedAt() {
	return updatedAt;
}

public void setUpdatedAt(Date updatedAt) {
	this.updatedAt = updatedAt;
}
}
