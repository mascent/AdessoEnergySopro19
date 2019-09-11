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

	LocalDateTime deletedAt;

	public Reading(Meter meter, Long initalReading) {
		this.meter = meter;
		readingValues = new ArrayList<ReadingValue>();
		readingValues.add(new ReadingValue(this, initalReading, null, "Inital Reading"));
	}

	public Reading() {
	}

	public Meter getMeter() {
		return meter;
	}

	public Long getReadingId() {
		return readingId;
	}

	public void delete() {
		if (this.deletedAt == null) {
			this.deletedAt = LocalDateTime.now();
		}
	}

	public List<ReadingValue> getReadingValues() {
		return readingValues;
	}

	public void addReading(Long readinValue, Long changerId, String reason) {
		readingValues.add(new ReadingValue(this, readinValue, changerId, reason));
	}

	public ReadingValue getCurrentReadingValue() {
		LocalDateTime newestTime = getCreatedAt();
		ReadingValue newstValue = null;
		for (ReadingValue rv : readingValues) {
			if (!rv.getCreatedAt().isBefore(newestTime)) {
				newestTime = rv.getCreatedAt();
				newstValue = rv;
			}
		}

		return newstValue;
	}

	public Long getValue() {
		return getCurrentReadingValue().getValue();
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

	public LocalDateTime getUpdatedAt() {
		return getCurrentReadingValue().getCreatedAt();
	}

	public LocalDateTime getDeletedAt() {
		return deletedAt;

	}

}
