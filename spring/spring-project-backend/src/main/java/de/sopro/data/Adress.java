package de.sopro.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Adress {

@OneToMany(mappedBy = "adress", cascade=CascadeType.ALL)
private List<Meter> meters = new ArrayList<Meter>();

private String adressID;

@Id @GeneratedValue(strategy = GenerationType.AUTO)
public String getAdressID() {
	return adressID;
}

public void setAdressID(String adressID) {
	this.adressID = adressID;
}

public String getStreet() {
	return street;
}

public void setStreet(String street) {
	this.street = street;
}

public String getNumber() {
	return number;
}

public void setNumber(String number) {
	this.number = number;
}

public int getPostalCode() {
	return postalCode;
}

public void setPostalCode(int postalCode) {
	this.postalCode = postalCode;
}

private String street;

private String number;

private int postalCode;
}
