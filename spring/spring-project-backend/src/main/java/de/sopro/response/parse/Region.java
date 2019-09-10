package de.sopro.response.parse;

import java.util.List;

import de.sopro.response.detect.BoundingBox;
import de.sopro.util.exception.UnreadableFotoException;

public class Region {
	private String boundingBox;
	private List<Line> lines;

	public String getBoundingBox() {
		return boundingBox;
	}

	public void setBoundingBox(String boundingBox) {
		this.boundingBox = boundingBox;
	}

	public List<Line> getLines() {
		return lines;
	}

	public void setLines(List<Line> lines) {
		this.lines = lines;
	}

}
