package it.camera.hackathon;

import it.camera.hackathon.textmining.CaseInsensitiveWordComparer;
import it.camera.hackathon.textmining.CompoundWordFilter;
import it.camera.hackathon.textmining.ICharFilter;
import it.camera.hackathon.textmining.IWordComparer;
import it.camera.hackathon.textmining.IWordFilter;
import it.camera.hackathon.textmining.LengthWordFilter;
import it.camera.hackathon.textmining.SingleDelimiterCharFilter;
import it.camera.hackathon.textmining.StopWordFilter;
import it.camera.hackathon.textmining.WordCounter;

public class TextMiningWordCounter extends WordCounter {
	
	// STATIC FIELDS
	
	private static IWordComparer defaultWordComparer = new CaseInsensitiveWordComparer();
	private static String defaultDelimiters = " ";
	private static String[] defaultStopWords = new String[0];
	private static int defaultMinLength = 1;
	
	private static ICharFilter getCharFilter(String delimiters) {
		return new SingleDelimiterCharFilter(delimiters);
	}
	
	private static IWordFilter getWordFilter(String[] stopWords, int minLength) {
		return new WordFilterWithCounter(
				new CompoundWordFilter(
					new StopWordFilter(stopWords, defaultWordComparer),
					new LengthWordFilter(minLength),
					new AlphabeticStringFilter()));
	}
	
	// INSTANCE FIELDS
	
	private String delimiters = defaultDelimiters;
	private String[] stopWords = defaultStopWords;
	private int minLength = defaultMinLength;

	public TextMiningWordCounter() {
		super(getCharFilter(defaultDelimiters), getWordFilter(defaultStopWords, defaultMinLength), defaultWordComparer);
	}
	
	public String getDelimiters()
	{
		return delimiters;
	}
	
	public void setDelimiters(String delimiters)
	{
		this.delimiters = delimiters;
		super._charFilter = getCharFilter(delimiters);
	}
	
	public String[] getStopWords()
	{
		return this.stopWords.clone();
	}
	
	public void setStopWords(String[] stopWords)
	{
		this.stopWords = stopWords;
		super._wordFilter = getWordFilter(stopWords, minLength);
	}
	
	public int getMinLength()
	{
		return this.minLength;
	}
	
	public void setMinLength(int minLength)
	{
		this.minLength = minLength;
		super._wordFilter = getWordFilter(stopWords, minLength);
	}
	
	/**
	 * Returns -1 if the parse() method has never been called on this WordCounter instance. After the parse() method has been called,
	 * the word count value is kept until:
	 * - the parse() method is called again
	 * - the word filter is changed (i.e. the minLength or stopWords attributes are changed)
	 * @return
	 */
	public int getAcceptedWordCount()
	{
		return ((WordFilterWithCounter)this._wordFilter).count;
	}
	
	private static class WordFilterWithCounter implements IWordFilter
	{
		private IWordFilter innerFilter;
		private int count;
		
		public WordFilterWithCounter(IWordFilter innerFilter)
		{
			this.innerFilter = innerFilter;
			this.count = -1;
		}
		
		@Override
		public boolean accept(String value) {
			boolean accepted = innerFilter.accept(value);
			if (accepted)
				count++;
			return accepted;
		}
	}
	
	private static class AlphabeticStringFilter implements IWordFilter
	{
		@Override
		public boolean accept(String value) {
			for (char c : value.toCharArray())
				if (!Character.isAlphabetic(c))
					return false;
			
			return true;
		}
	}
}