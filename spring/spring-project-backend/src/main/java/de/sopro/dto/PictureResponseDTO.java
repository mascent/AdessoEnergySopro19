package de.sopro.dto;

import de.sopro.data.MeterType;

public class PictureResponseDTO {
	String type;
	Long mid;
	String value;

	public PictureResponseDTO() {

	}

	public PictureResponseDTO(MeterType type, Long mid, String value) {
		this.type = type.toString();
		this.mid = mid;
		this.value = value;
	}

}
