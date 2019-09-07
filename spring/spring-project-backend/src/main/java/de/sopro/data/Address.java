package de.sopro.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.*;

@Entity
public class Address {

	public Address() {
		meters = new ArrayList<Meter>();
	}

	@NotNull
	@OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
	private List<Meter> meters;

	public List<Meter> getMeters() {
		return meters;
	}

	public void setMeters(List<Meter> meters) {
		this.meters = meters;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long addressID;

	public Long getAddressID() {
		return addressID;
	}

	public void setAddressID(Long addressID) {
		this.addressID = addressID;
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

	@NotNull
	private String street;

	@NotNull
	private String number;

	@Positive
	@Size(min = 5, max = 5) // Für deutsche PLZ
	@NotNull
	private int postalCode;
}
