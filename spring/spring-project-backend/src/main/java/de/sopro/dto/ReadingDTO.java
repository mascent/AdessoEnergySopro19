package de.sopro.dto;

import java.time.LocalDateTime;

public class ReadingDTO {
	
	Long id;
	String meterId;
	String ownerId;
	String value;
	Long trend;
	String lastEditorName;
	String lastEditReason;
	LocalDateTime createdAt;
	LocalDateTime updatedAt;
	LocalDateTime deletedAt;


}
