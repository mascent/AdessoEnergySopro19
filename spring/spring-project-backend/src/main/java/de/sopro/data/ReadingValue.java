package de.sopro.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;

@Entity
public class ReadingValue {
	
public ReadingValue(int value, Date date, String changerId, String reason){
	this.value=value;
	this.date=date;
	this.changerId = changerId;
	this.reason = reason;
}

// smart constructor for first initialization
public ReadingValue(int value, Date date, String changerId) {
	this.value=value;
	this.date=date;
	this.changerId = changerId;
	this.reason = "creation";
}

@Id @GeneratedValue(strategy = GenerationType.AUTO)
private String readingValueID;


@ManyToOne
private Reading reading;

@Positive
@NotNull
private int value;

@NotNull
@Past
private Date date;

private String changerId;

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

public String getChangerId() {
	return changerId;
}

public void setChangerID(String changerId) {
	this.changerId = changerId;
}

public String getReason() {
	return reason;
}

public void setReason(String reason) {
	this.reason = reason;
}
}
