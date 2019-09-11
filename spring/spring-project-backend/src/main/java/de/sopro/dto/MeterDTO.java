package de.sopro.dto;

import de.sopro.data.Meter;

public class MeterDTO {

	Long id;
	String meterNumber;
	String type;
	String name;
	String ownerId;
	ReadingDTO lastReading;
	
	public MeterDTO(Meter m) {
		this.id = m.getMeterId();
		this.meterNumber = m.getMeternumber();
		this.type = m.getMeterType().toString();
		this.name = "This is a can feature";
		this.ownerId = null; //TODO get current owner somehow
		this.lastReading = new ReadingDTO();
		
		
		
	}
}
