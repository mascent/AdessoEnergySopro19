package de.sopro.response.classify;

import java.util.List;

import de.sopro.data.MeterType;

public class Classifications {
	
	public static final Float MINPROB = 0.75f;
	
	private List<Classification> classifications;

	public List<Classification> getClassifications() {
		return classifications;
	}

	public void setClassifications(List<Classification> classifications) {
		this.classifications = classifications;
	}

	/**
	 * For a given list of classification return the MeterType with the highest probability 
	 * or null if none of the classifications has a high probability.
	 * Minimum probability is defined in Classifications.MINPROB
	 * @return the meter type with the highest probability
	 * 		   null if none met the required minimum
	 */
	public MeterType lookupMeterType() {
		Float maxProb = MINPROB;
		MeterType maxTag = null;
		for (Classification classification : classifications) {
			Float currProb = classification.getProbability();
			if(currProb > maxProb) {
				maxProb = currProb;
				maxTag = classification.getTagMeterType();
			}
		}
		return maxTag;
	}
}
