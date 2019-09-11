package de.sopro.response.classify;

import de.sopro.data.MeterType;

public class Classification {
	private Float probability;
	private String tag;

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Float getProbability() {
		return probability;
	}

	public void setProbability(Float probability) {
		this.probability = probability;
	}

	public MeterType getTagMeterType() {
		switch (tag) {
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
