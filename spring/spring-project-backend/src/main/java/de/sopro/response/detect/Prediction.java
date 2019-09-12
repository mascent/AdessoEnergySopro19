package de.sopro.response.detect;

public class Prediction {

	private String Tag;
	private Float Probability;
	private BoundingBox BoundingBox;
	
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
	public BoundingBox getBoundingBox() {
		return BoundingBox;
	}
	public void setBoundingBox(BoundingBox BoundingBox) {
		this.BoundingBox = BoundingBox;
	}

}
