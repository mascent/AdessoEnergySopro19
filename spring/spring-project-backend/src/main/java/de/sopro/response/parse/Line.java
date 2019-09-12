package de.sopro.response.parse;

import java.util.List;

public class Line {
	private String boundingBox;
	private List<Word> words;
	public String getBoundingBox() {
		return boundingBox;
	}
	public void setBoundingBox(String boundingBox) {
		this.boundingBox = boundingBox;
	}
	public List<Word> getWords() {
		return words;
	}
	public void setWords(List<Word> words) {
		this.words = words;
	}
	
	
}
