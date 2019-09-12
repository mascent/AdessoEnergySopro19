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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getMid() {
		return mid;
	}

	public void setMid(Long mid) {
		this.mid = mid;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
