package de.sopro.data;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;

@Entity
public class ReadingValue {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long readingValueId;

	@ManyToOne
	private Reading reading;


	@Positive
	@NotNull
	private Long value;

	@NotNull
	@Past
	private LocalDateTime lastChange;

	private Long changerId;

	private String reason;

	public ReadingValue(Long value, Long changerId, String reason) {
		this.value = value;
		this.changerId = changerId;
		this.reason = reason;
		lastChange = LocalDateTime.now();
	}

// smart constructor for first initialization
	public ReadingValue(Long value, Long changerId) {
		this.value = value;
		this.changerId = changerId;
		this.reason = "creation";
		lastChange = LocalDateTime.now();
	}

	public Long getReadingValueId() {
		return readingValueId;
	}

	public void setReadingValueId(Long readingValueId) {
		this.readingValueId = readingValueId;
	}

	public Long getValue() {
		return value;
	}

	public LocalDateTime getCreatedAt() {
		return lastChange;
	}

	public void setDate(LocalDateTime date) {
		this.lastChange = date;
	}

	public Long getChangerId() {
		return changerId;
	}

	public void setChangerId(Long changerId) {
		this.changerId = changerId;
	}

	public String getReason() {
		return reason;
	}

	public Reading getReading() {
		return reading;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
