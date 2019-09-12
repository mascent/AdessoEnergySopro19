package de.sopro.data;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "readingValue")
public class ReadingValue {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long readingValueId;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Reading reading;

	@Positive
	@NotNull
	private Long value;

	@NotNull
	@Past
	private LocalDateTime lastChange;

	private Long changerId;

	private String reason;

	public ReadingValue(Reading reading, Long value, Long changerId, String reason) {
		this.value = value;
		this.changerId = changerId;
		this.reason = reason;
		this.reading = reading;
		lastChange = LocalDateTime.now();
	}

	public ReadingValue() {
		
	}
	
	public Long getReadingValueId() {
		return readingValueId;
	}

	public Long getValue() {
		return value;
	}

	public LocalDateTime getCreatedAt() {
		return lastChange;
	}

	public Long getChangerId() {
		return changerId;
	}

	public String getReason() {
		return reason;
	}

	public Reading getReading() {
		return reading;
	}

}
