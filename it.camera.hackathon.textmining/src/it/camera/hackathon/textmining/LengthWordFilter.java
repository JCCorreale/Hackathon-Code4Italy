package it.camera.hackathon.textmining;

public class LengthWordFilter implements IWordFilter {

	private int minLength;
	
	public LengthWordFilter(int minLength) 
	{
		this.minLength = minLength;
	}

	@Override
	public boolean accept(String value) {
		return value.length() >= minLength;
	}
}