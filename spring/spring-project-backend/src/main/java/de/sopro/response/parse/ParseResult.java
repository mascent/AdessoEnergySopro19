package de.sopro.response.parse;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
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

	/**
	 * Serves no functionality since Detected Area and Parsed Areas don't intersect.
	 * @param area
	 * @param bimb
	 * @return
	 * @throws UnreadableFotoException
	 */
	public List<Word> findMatchingBox(BoundingBox area, BufferedImage bimb) throws UnreadableFotoException{
		if(!orientation.equals("Up") ) {
			throw new UnreadableFotoException("Wrong orientation");
		}
		int iwidth = bimb.getWidth();
		int iheight = bimb.getHeight();
		
		Float left = area.getLeft();
		Float top = area.getTop();
		Float width = area.getWidth();
		Float height = area.getHeight();
		
		Float newBoxLeft = left * iwidth;
		Float newBoxTop = top * iheight;
		Float newBoxWidth = width * iwidth;
		Float newBoxHeight = height * iheight;
		
		List<Word> allWords = new ArrayList<>();
		
		// simply ignore all lines'n shit
		for (Region region : regions) {
			 List<Line> lines = region.getLines();
			for (Line line : lines) {
				List<Word> words = line.getWords();
				allWords.addAll(words);
			}
		}		
		List<Word> found = new ArrayList<>();
		
		// search for box
		for (Word word : allWords) {
			String[] regionBox = word.getBoundingBox().split(",");
			
			
			if(allPointsInArea(newBoxLeft,newBoxTop,newBoxHeight,newBoxWidth,regionBox))
			//if(newBoxLeft <= rLeft && newBoxTop <= rTop && newBoxHeight >= rHeight && newBoxWidth >= rWidth) {
				found.add(word);
			}
		
		return found;
	}

	private boolean allPointsInArea(Float newBoxLeft, Float newBoxTop, Float newBoxHeight, Float newBoxWidth,
			String[] regionBox) {
		Integer rLeft = Integer.parseInt(regionBox[0]);
		Integer rTop  = Integer.parseInt(regionBox[1]);
		Integer rWidth = Integer.parseInt(regionBox[2]);
		Integer rHeight = Integer.parseInt(regionBox[3]);
		
		return (newBoxLeft <= rLeft 
				&& newBoxTop <= rTop 
				&& newBoxHeight >= rHeight 
				&& newBoxWidth >= rWidth
				&& newBoxTop + newBoxHeight >= rTop
				&& newBoxLeft+ newBoxWidth >= rLeft);
	}

}
