package it.camera.hackathon.textmining;

public class SimpleStopWordsFilter implements IWordFilter {

	protected String[] _stopWords;
	
	public String[] getStopWords() {
		return _stopWords;
	}
	
	public SimpleStopWordsFilter(String[] stopWords) {
		_stopWords = stopWords;
	}
	
	@Override
	public boolean accept(String value) {
		for(String stopWord : _stopWords)
			if(stopWord.equals(value))
				return false;
		
		return true;
	}

}
