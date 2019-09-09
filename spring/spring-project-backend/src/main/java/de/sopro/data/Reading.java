package de.sopro.data;

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
	private Iterable<ReadingValue> readingValues;

	@ManyToOne
	private Meter meter;

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

}
