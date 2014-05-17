package it.camera.hackathon.textmining;

import java.util.HashMap;
import java.util.Map;

public class WordCounter implements IWordCounter {

	private ICharFilter _charFilter;
	private IWordFilter _wordFilter;
	private IWordComparer _wordComparer;
	
	protected Map<String, Integer> _map = new HashMap<String, Integer>();
	
	public WordCounter(ICharFilter cf, IWordFilter wf, IWordComparer wc) {
		_charFilter = cf;
		_wordFilter = wf;
		_wordComparer = wc;
	}
	
	public ICharFilter getCharFilter() {
		return _charFilter;
	}
	
	public IWordFilter getWordFilter() {
		return _wordFilter;
	}
	
	public IWordComparer getWordComparer() {
		return _wordComparer;
	}
	
	private void checkWord(String currentWord) {
		if("".equals(currentWord))
			return;
		
		if(!_wordFilter.accept(currentWord))
			return;
		
		int currentCount = 0;
		
		for(String ss : _map.keySet())
			if(_wordComparer.areEqualWords(ss, currentWord))
				currentWord = ss;
		
		if(_map.containsKey(currentWord))
			currentCount = _map.get(currentWord);
		
		_map.put(currentWord, currentCount + 1);
	}
	
	@Override
	public IWordCountResult parse(String s) {
		
		StringBuilder sb = new StringBuilder();
		for(char c : s.toCharArray())
			if(_charFilter.accept(c))
				sb.append(c);
			else {
				checkWord(sb.toString());
				sb = new StringBuilder();
			}
		
		checkWord(sb.toString());
		
		return new WordCountResult(_map);
	}

}
