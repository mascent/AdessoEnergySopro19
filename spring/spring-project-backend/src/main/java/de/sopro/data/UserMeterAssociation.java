package de.sopro.data;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UserMeterAssociation {

public UserMeterAssociation(User user, Meter meter) {
	this.user = user;
	this.meter=meter;
	// beginOfAssociation = now; 
}
	

@ManyToOne
@NotNull
private User user;

@ManyToOne
@NotNull
private Meter meter;

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}

public Meter getMeter() {
	return meter;
}

public void setMeter(Meter meter) {
	this.meter = meter;
}

@Id @GeneratedValue(strategy = GenerationType.AUTO)
private String aID;

private Date beginOfAssociation;

private Date endOfAssociation;

public String getaID() {
	return aID;
}

public void setaID(String aID) {
	this.aID = aID;
}

public Date getBeginOfAssociation() {
	return beginOfAssociation;
}

public void setBeginOfAssociation(Date beginOfAssociation) {
	this.beginOfAssociation = beginOfAssociation;
}

public Date getEndOfAssociation() {
	return endOfAssociation;
}

public void setEndOfAssociation(Date endOfAssociation) {
	this.endOfAssociation = endOfAssociation;
}

}
