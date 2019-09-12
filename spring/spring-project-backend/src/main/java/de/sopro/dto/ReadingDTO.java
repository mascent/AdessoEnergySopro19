package de.sopro.dto;

import java.time.LocalDateTime;

import de.sopro.data.Reading;
import de.sopro.data.ReadingValue;

public class ReadingDTO {

	Long id;
	Long meterId;
	Long ownerId;
	Long value;
	Long trend;
	Long lastEditorName;
	String lastEditReason;
	LocalDateTime createdAt;
	LocalDateTime updatedAt;
	LocalDateTime deletedAt;

	public ReadingDTO(Reading reading) {
		this.id = reading.getReadingId();
		this.meterId = reading.getMeter().getMeterId();
		this.createdAt = reading.getCreatedAt();
		this.deletedAt = reading.getDeletedAt();
		this.ownerId = null; // TODO get ownerID

//		ReadingValue rv = reading.getCurrentReadingValue();
//		this.value = rv.getValue();
//		this.lastEditorName = rv.getChangerId(); // TODO get Name
//		this.lastEditReason = rv.getReason();
//		this.updatedAt = rv.getCreatedAt();
	}

	public Long getId() {
		return id;
	}

	public Long getMeterId() {
		return meterId;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public Long getValue() {
		return value;
	}

	public Long getTrend() {
		return trend;
	}

	public Long getLastEditorName() {
		return lastEditorName;
	}

	public String getLastEditReason() {
		return lastEditReason;
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

}
