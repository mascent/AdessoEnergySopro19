package de.sopro.data;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Meter {

	public Meter(String meternumber, int initialValue, MeterType meterType) {
		createdAt = LocalDateTime.now();
		this.meternumber = meternumber;
		readings = new ArrayList<Reading>();
		Reading initialReading = new Reading();
		ReadingValue initialReadingValue = new ReadingValue(initialValue, null);
		List<ReadingValue> initialReadingValueList = new ArrayList<>();
		initialReadingValueList.add(initialReadingValue);
		initialReading.setReadingValues(initialReadingValueList);
		readings.add(initialReading);
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

	private String meternumber;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long meterId;

	@OneToMany(mappedBy = "meter", cascade = CascadeType.ALL)
	private List<Reading> readings;

	private LocalDateTime createdAt;

	private LocalDateTime deletedAt;

	private LocalDateTime updatedAt;

	@ManyToOne
	private Address address;

	public Address getAdress() {
		return address;
	}

	public void setAdress(Address address) {
		this.address = address;
	}

	public List<Reading> getReadings() {
		return readings;
	}

	public void setReadings(List<Reading> readings) {
		this.readings = readings;
	}

	private int lengthOfReading = 0;

	private int commaPosition = 0;

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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean update(Reading reading) {
		// todo updatelogic -- needed?
		return false;
	}

}
