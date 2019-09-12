package de.sopro.data;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class UserMeterAssociation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long aId;

	@ManyToOne
	@NotNull
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;

	@ManyToOne
	@NotNull
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Meter meter;

	private String meterName;

	public UserMeterAssociation(User user, Meter meter) {
		this.user = user;
		this.meter = meter;
		this.meterName = meter.getMeternumber();
		beginOfAssociation = LocalDateTime.now();
	}
	
	public UserMeterAssociation() {
		
	}
	
	public String getMeterName() {
		return meterName;
	}

	public void setMeterName(String meterName) {
		this.meterName = meterName;
	}

	private LocalDateTime beginOfAssociation;

	private LocalDateTime endOfAssociation;



	public Long getId() {
		return aId;
	}

	public User getUser() {
		return user;
	}

	public Meter getMeter() {
		return meter;
	}

	public LocalDateTime getBeginOfAssociation() {
		return beginOfAssociation;
	}

	public LocalDateTime getEndOfAssociation() {
		return endOfAssociation;
	}

	public void endAssociation() {
		this.endOfAssociation = LocalDateTime.now();
	}

}
