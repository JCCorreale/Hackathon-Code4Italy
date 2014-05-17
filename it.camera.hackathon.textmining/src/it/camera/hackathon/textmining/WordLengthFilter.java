package it.camera.hackathon.textmining;

public class WordLengthFilter implements IWordFilter {

	private int minLength;
	
	public WordLengthFilter(int minLength) 
	{
		this.minLength = minLength;
	}

	@Override
	public boolean accept(String value) {
		return value.length() >= minLength;
	}
}