package de.sopro.dto;

import java.time.LocalDateTime;

import de.sopro.data.Meter;

public class MeterDTO {

	Long id;
	String meterNumber;
	String type;
	String name;
	Long ownerId;
	Long initialValue;
	LocalDateTime createdAt;
	LocalDateTime updatedAt;
	LocalDateTime deletedAt;
	
	public Long getInitialValue() {
		return initialValue;
	}

	ReadingDTO lastReading;

	public MeterDTO() {
		
	}
	
	public MeterDTO(Meter m) {
		this.id = m.getMeterId();
		this.meterNumber = m.getMeternumber();
		this.type = m.getMeterType().toString();
		this.name = "This is a can feature";
		this.ownerId = null; // TODO get current owner somehow
		this.createdAt = m.getCreatedAt();
		this.updatedAt= m.getUpdatedAt();
		this.deletedAt = m.getDeletedAt();

		//this.lastReading = new ReadingDTO(m.getLastReading());

	}
	
	
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}

	//TODO
	public MeterDTO(Meter m, Long uid, String name, ReadingDTO lastReading) {
		this.id = m.getMeterId();
		this.meterNumber = m.getMeternumber();
		this.type = m.getMeterType().toString();
		this.name = name;
		this.ownerId = uid; // TODO get current owner somehow
		this.createdAt = m.getCreatedAt();
		this.updatedAt= m.getUpdatedAt();
		this.deletedAt = m.getDeletedAt();
		this.lastReading = lastReading;

	}

	public Long getId() {
		return id;
	}

	public String getMeterNumber() {
		return meterNumber;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public ReadingDTO getLastReading() {
		return lastReading;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof MeterDTO)) {
			return false;
		}else {
			return id.equals(((MeterDTO) obj).getId());
		}
		//return super.equals(obj);
	}
}
