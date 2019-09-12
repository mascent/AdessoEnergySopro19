package de.sopro.dto;

import de.sopro.data.Meter;

public class MeterDTO {

	Long id;
	String meterNumber;
	String type;
	String name;
	Long ownerId;
	ReadingDTO lastReading;

	public MeterDTO(Meter m) {
		this.id = m.getMeterId();
		this.meterNumber = m.getMeternumber();
		this.type = m.getMeterType().toString();
		this.name = "This is a can feature";
		this.ownerId = null; // TODO get current owner somehow

		//this.lastReading = new ReadingDTO(m.getLastReading());

	}
	
	
	//TODO
	public MeterDTO(Meter m, Long uid, String name, ReadingDTO lastReading) {
		this.id = m.getMeterId();
		this.meterNumber = m.getMeternumber();
		this.type = m.getMeterType().toString();
		this.name = name;
		this.ownerId = uid; // TODO get current owner somehow

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
