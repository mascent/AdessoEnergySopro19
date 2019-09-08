package de.sopro.data;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class UserMeterAssociation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long aId;

	@ManyToOne
	@NotNull
	private User user;

	@ManyToOne
	@NotNull
	private Meter meter;

	private LocalDateTime beginOfAssociation;

	private LocalDateTime endOfAssociation;

	public UserMeterAssociation(User user, Meter meter) {
		this.user = user;
		this.meter = meter;
		beginOfAssociation = LocalDateTime.now();
	}

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

}
