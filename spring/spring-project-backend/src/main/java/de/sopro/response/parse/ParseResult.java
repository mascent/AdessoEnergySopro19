package de.sopro.response.parse;

import java.awt.image.BufferedImage;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import de.sopro.response.detect.BoundingBox;
import de.sopro.util.exception.UnreadableFotoException;
import net.bytebuddy.asm.Advice.OffsetMapping.ForAllArguments;

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

	public Region findMatchingBox(BoundingBox meterNumberArea, BufferedImage bimb) throws UnreadableFotoException{
		if(!orientation.equals("up")) {
			throw new UnreadableFotoException("Wrong orientation");
		}
		int iwidth = bimb.getWidth();
		int iheight = bimb.getHeight();
		
		Float left = meterNumberArea.getLeft();
		Float top = meterNumberArea.getTop();
		Float width = meterNumberArea.getWidth();
		Float height = meterNumberArea.getHeight();
		
		Float newBoxLeft = left * iwidth;
		Float newBoxTop = top * iheight;
		Float newBoxWidth = width * iwidth;
		Float newBoxHeight = height * iheight;
		
		for (Region region : regions) {
			String[] regionBox = region.getBoundingBox().split(",");
			Integer rLeft = Integer.parseInt(regionBox[0]);
			Integer rTop  = Integer.parseInt(regionBox[1]);
			Integer rWidth = Integer.parseInt(regionBox[2]);
			Integer rHeight = Integer.parseInt(regionBox[3]);
			
			if(isAlike(newBoxLeft,rLeft)
					&& isAlike(newBoxTop,rTop) 
					&& isAlike(newBoxWidth,rWidth) 
					&& isAlike(newBoxHeight,rHeight)){
				return region;
			}
		}
		
		throw new UnreadableFotoException("No matching Box");
	}

	private boolean isAlike(Float newBoxValue, Integer rValue) {
		return (newBoxValue > rValue - 4) && (newBoxValue < rValue + 4);
	}
	
	

}
