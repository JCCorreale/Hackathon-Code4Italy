package it.camera.hackathon.textmining;

public class StopWordFilter implements IStopWordFilter {

	private IWordComparer _comparer;
	protected String[] _stopWords;
	
	public StopWordFilter(String[] array, IWordComparer comparer) {
		_stopWords = array;
		_comparer = comparer;
	}

	@Override
	public String[] getStopWords() {
		return _stopWords;
	}
	
	@Override
	public boolean accept(String value) {
		for(String stopWord : _stopWords)
			if(_comparer.areEqualWords(stopWord, value))
				return false;
		return true;
	}

}
