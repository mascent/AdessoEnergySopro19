package de.sopro.response.classify;

import de.sopro.data.MeterType;

public class Classification {
	private Float Probability;
	private String Tag;

	public String getTag() {
		return Tag;
	}

	public void setTag(String Tag) {
		this.Tag = Tag;
	}

	public Float getProbability() {
		return Probability;
	}

	public void setProbability(Float Probability) {
		this.Probability = Probability;
	}

	public MeterType getTagMeterType() {
		switch (Tag) {
		case "gas":
			return MeterType.Gas;
		case "strom":
			return MeterType.Electricity;
		case "wasser":
			return MeterType.Water;
		default:
			return null;
		}
	}

}
