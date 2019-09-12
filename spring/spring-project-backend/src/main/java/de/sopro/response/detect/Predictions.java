package de.sopro.response.detect;

import java.util.List;

import javax.persistence.Tuple;

import de.sopro.util.Pair;

public class Predictions {

	public static final Float MINPROB = 0.0f; //0.5 should be minimum
	
	List <Prediction> Predictions;

	public List<Prediction> getPredictions() {
		return Predictions;
	}

	public void setPredictions(List<Prediction> predictions) {
		this.Predictions = predictions;
	}
	
	/**
	 * 
	 * @return Pair of the MeternumberArea and the MetervalueArea
	 * 		   with the maximum Probability.
	 */
	public Pair<BoundingBox,BoundingBox> findMaxProbabilityAreas() {
		Float maxProbNumberArea = MINPROB;
		BoundingBox numberArea = null;
		Float maxProbValueArea = MINPROB;
		BoundingBox valueArea = null;
		
		for (Prediction prediction : Predictions) {
			String currTag = prediction.getTag();
			Float currProb = prediction.getProbability();
			BoundingBox currBox = prediction.getBoundingBox();
			switch (currTag) {
			case "Zählernummer":
				if (currProb>maxProbNumberArea) {
					maxProbNumberArea = currProb;
					numberArea = currBox;
				}
				break;
			
			case "Zählerstand":
				if (currProb>maxProbValueArea) {
					maxProbValueArea = currProb;
					valueArea = currBox;
				}
				break;

			default:
				break;
			}
		}
		return new Pair<BoundingBox, BoundingBox>(numberArea, valueArea);
	}
	
}
