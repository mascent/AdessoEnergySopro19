package de.sopro.response.parse;

import java.util.List;

import de.sopro.response.detect.BoundingBox;
import de.sopro.util.exception.UnreadableFotoException;

public class ParseResult {
	private String language;
	private Float textAngle;
	private String orientation;
	private List<Region> regions;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Float getTextAngle() {
		return textAngle;
	}

	public void setTextAngle(Float textAngle) {
		this.textAngle = textAngle;
	}

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	public String findMatchingBox(BoundingBox meterNumberArea) throws UnreadableFotoException{
		if(!orientation.equals("up")) {
			throw new UnreadableFotoException("Wrong orientation");
		}
		
		return null;
	}

}
