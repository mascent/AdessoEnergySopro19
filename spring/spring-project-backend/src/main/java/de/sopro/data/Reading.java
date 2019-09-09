package de.sopro.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Reading {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long readingId;

	@OneToMany(mappedBy = "reading", cascade = CascadeType.ALL)
	@NotNull
	private List<ReadingValue> readingValues;

	
	@ManyToOne
	private Meter meter;

	
	public Reading(Meter meter, Long initalReading) {
		this.meter = meter;
		readingValues = new ArrayList<ReadingValue>();
		readingValues.add(new ReadingValue(initalReading, null, "Inital Reading"));
	}
	
	public Reading() {
	}

	public Meter getMeter() {
		return meter;
	}

	public void setMeter(Meter meter) {
		this.meter = meter;
	}

	public Long getReadingId() {
		return readingId;
	}

	public void setReadingId(Long readingId) {
		this.readingId = readingId;
	}

	public List<ReadingValue> getReadingValues() {
		return readingValues;
	}

	public void setReadingValues(List<ReadingValue> readingValues) {
		this.readingValues = readingValues;
	}

	public LocalDateTime getCreatedAt() {
		LocalDateTime oldestTime = LocalDateTime.now();
		for (ReadingValue rv : readingValues) {
			if (rv.getCreatedAt().isBefore(oldestTime)) {
				oldestTime = rv.getCreatedAt();
			}
		}
		return oldestTime;
	}
}
