package de.sopro.response.detect;

public class Prediction {

	private String Tag;
	private Float probability;
	private BoundingBox boundingBox;
	
	public String getTag() {
		return Tag;
	}
	public void setTag(String Tag) {
		this.Tag = Tag;
	}
	public Float getProbability() {
		return probability;
	}
	public void setProbability(Float probability) {
		this.probability = probability;
	}
	public BoundingBox getBoundingBox() {
		return boundingBox;
	}
	public void setBoundingBox(BoundingBox boundingBox) {
		this.boundingBox = boundingBox;
	}

}
