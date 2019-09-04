package de.sopro.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Meter {
	
public Meter(String meternumber, MeterType meterType) {
	//createdAt = now;
	this.meternumber = meternumber;
	readings = new ArrayList<Reading>();
	//on different types set comma and length
}

private String meternumber;

@Id @GeneratedValue(strategy = GenerationType.AUTO)
private String meterID;

@OneToMany(mappedBy = "meter", cascade = CascadeType.ALL)
private List <Reading> readings;

private Date createdAt;

private Date deletedAt;

private Date updatedAt;

public List<Reading> getReadings() {
	return readings;
}

public void setReadings(List<Reading> readings) {
	this.readings = readings;
}

private int lengthOfReading = 0;

private int commaPosition = 0;

public boolean delete() {
	return false;
}

public String getMeternumber() {
	return meternumber;
}

public void setMeternumber(String meternumber) {
	this.meternumber = meternumber;
}

public String getMeterID() {
	return meterID;
}

public void setMeterID(String meterID) {
	this.meterID = meterID;
}

public Date getCreatedAt() {
	return createdAt;
}

public Date getDeletedAt() {
	return deletedAt;
}

public void setDeletedAt(Date deletedAt) {
	this.deletedAt = deletedAt;
}

public Date getUpdatedAt() {
	return updatedAt;
}

public void setUpdatedAt(Date updatedAt) {
	this.updatedAt = updatedAt;
}

public boolean update(Reading reading) {
	// todo updatelogic -- needed?
	return false;
}

}
