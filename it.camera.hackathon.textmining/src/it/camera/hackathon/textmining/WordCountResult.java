package it.camera.hackathon.textmining;

import java.util.Map;

public class WordCountResult implements IWordCountResult {

	private Map<String, Integer> _map;
	
	public WordCountResult(Map<String, Integer> map) {
		_map = map;
	}
	
	@Override
	public String[] getWords() {
		return (String[])_map.keySet().toArray(new String[0]);
	}

	@Override
	public int getCount(String word) {
		if(_map.containsKey(word))
			return _map.get(word);
		return 0;
	}
}
