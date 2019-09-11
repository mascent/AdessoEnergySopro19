package de.sopro.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Meter {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long meterId;

	@OneToMany
	private List<Reading> readings;

	private String meternumber;

	private LocalDateTime createdAt;

	private LocalDateTime deletedAt;

	private LocalDateTime updatedAt;

	private int lengthOfReading;

	private int commaPosition;

	private MeterType meterType;

	@ManyToOne
	private Address address;

	public Meter(String meternumber, Long initialValue, MeterType meterType) {
		createdAt = LocalDateTime.now();
		this.meternumber = meternumber;
		this.meterType = meterType;
		this.readings = new ArrayList<>();
		readings.add(new Reading(this, initialValue));

		switch (meterType) {
		case Gas:
			lengthOfReading = 8;
			commaPosition = 1;
			break;

		case Water:
			lengthOfReading = 6;
			commaPosition = 3;
			break;

		case Electricity:
			lengthOfReading = 7;
			commaPosition = 1;
			break;

		default:
			throw new IllegalArgumentException();
		}

	}

	public MeterType getMeterType() {
		return meterType;
	}

	public Address getAdress() {
		return address;
	}

	public void setAdress(Address address) {
		this.address = address;
	}

	public boolean delete() {
		return false;
	}

	public String getMeternumber() {
		return meternumber;
	}

	public void setMeternumber(String meternumber) {
		this.meternumber = meternumber;
	}

	public Long getMeterId() {
		return meterId;
	}

	public void setMeterId(Long meterId) {
		this.meterId = meterId;
	}

	public List<Reading> getReadings() {
		return readings;
	}

	public int getCommaPosition() {
		return commaPosition;
	}

	public Address getAddress() {
		return address;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}

	public void delet() {
		if (deletedAt == null) {
			this.deletedAt = LocalDateTime.now();
		}
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean update(Long reading) {
		readings.add(new Reading(this, reading));
		return false;
	}

	public int getLengthOfReading() {
		return this.lengthOfReading;
	}

	public int getCommaPossition() {
		return this.commaPosition;
	}

	public void addReading(Long value) {

	}

	public Long getLastReadingValue() {
		return getLastReading().getValue();
	}

	public Long getLastReadingValue(LocalDateTime from) {
		return getLastReading(from, LocalDateTime.now()).getValue();
	}

	public Long getLastReadingValue(LocalDateTime from, LocalDateTime to) {
		return getLastReading(from, to).getValue();
	}

	public Reading getLastReading() {
		return getLastReading(getCreatedAt(), LocalDateTime.now());

	}

	public Reading getLastReading(LocalDateTime from) {
		return getLastReading(from, LocalDateTime.now());
	}

	public Reading getLastReading(LocalDateTime from, LocalDateTime to) {
		List<Reading> readingsInTimeFrame = StreamSupport.stream(readings.spliterator(), false)
				.filter(r -> from.isAfter(r.getCreatedAt())).filter(r -> to.isBefore(r.getCreatedAt())).distinct()
				.collect(Collectors.toList());
		LocalDateTime oldestTime = from;
		Reading oldestReading = null;
		for (Reading r : readingsInTimeFrame) {
			if (r.getCreatedAt().isAfter(oldestTime)) {
				oldestTime = r.getCreatedAt();
				oldestReading = r;
			}
		}

		return oldestReading;
	}

}
