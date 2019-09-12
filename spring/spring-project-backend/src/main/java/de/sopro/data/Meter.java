package de.sopro.data;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "meter")
public class Meter {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long meterId;

	private String meternumber;

	private LocalDateTime createdAt;

	private LocalDateTime deletedAt;

	private LocalDateTime updatedAt;

	private int lengthOfReading;

	private int commaPosition;

	private MeterType meterType;

//	@ManyToOne
//	private Address address;

	public Meter(String meternumber, MeterType meterType) {
		createdAt = LocalDateTime.now();
		this.meternumber = meternumber;
		this.meterType = meterType;

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

	public Meter() {

	}

	public MeterType getMeterType() {
		return meterType;
	}


//
//	public void setAdress(Address address) {
//		this.address = address;
//	}

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

	public int getCommaPosition() {
		return commaPosition;
	}

//	public Address getAddress() {
//		return address;
//	}

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

	public int getLengthOfReading() {
		return this.lengthOfReading;
	}

	public int getCommaPossition() {
		return this.commaPosition;
	}

	public void addReading(Long value) {

	}


}
