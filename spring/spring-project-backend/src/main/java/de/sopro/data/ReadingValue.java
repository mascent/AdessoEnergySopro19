package de.sopro.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ReadingValue {

@Id @GeneratedValue(strategy = GenerationType.AUTO)
private String readingValueID;

private int value;

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
