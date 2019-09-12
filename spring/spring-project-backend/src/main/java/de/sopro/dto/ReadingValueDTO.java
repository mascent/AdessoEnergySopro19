package de.sopro.dto;

import de.sopro.data.ReadingValue;

public class ReadingValueDTO {

	private Long id;
	private Long value;
	private Long readingId;;
	private Long changerId;
	private String reason;

	public ReadingValueDTO(ReadingValue readingValue) {
		this.id = readingValue.getReadingValueId();
		this.value = readingValue.getValue();
		this.readingId = readingValue.getReading().getReadingId();
		this.changerId = readingValue.getChangerId();
		this.reason = readingValue.getReason();
	}

	public Long getId() {
		return id;
	}

	public Long getValue() {
		return value;
	}

	public Long getReadingId() {
		return readingId;
	}

	public Long getChangerId() {
		return changerId;
	}

	public String getReason() {
		return reason;
	}

}
