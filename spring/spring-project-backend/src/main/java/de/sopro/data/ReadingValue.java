package de.sopro.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ReadingValue {
	
public ReadingValue(int value, Date date, Integer changerID, String reason){
	this.value=value;
	this.date=date;
	this.changerID = changerID;
	this.reason = reason;
}

// smart constructor for first initialization
public ReadingValue(int value, Date date, Integer changerID) {
	this.value=value;
	this.date=date;
	this.changerID = changerID;
	this.reason = "creation";
}

@Id @GeneratedValue(strategy = GenerationType.AUTO)
private String readingValueID;

@Positive
@NotNull
private int value;

@NotNull
@Past
private Date date;

private Integer changerID;

private String reason;

public String getReadingValueID() {
	return readingValueID;
}

public void setReadingValueID(String readingValueID) {
	this.readingValueID = readingValueID;
}

public int getValue() {
	return value;
}

public void setValue(int value) {
	this.value = value;
}

public Date getDate() {
	return date;
}

public void setDate(Date date) {
	this.date = date;
}

public Integer getChangerID() {
	return changerID;
}

public void setChangerID(Integer changerID) {
	this.changerID = changerID;
}

public String getReason() {
	return reason;
}

public void setReason(String reason) {
	this.reason = reason;
}
}
