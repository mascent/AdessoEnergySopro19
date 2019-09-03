package de.sopro.data;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class User extends Person{

private String name;

private String surname;

private String eMailAdress;

private String userNumber;

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

public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
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
