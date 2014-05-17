package it.camera.hackathon.textmining;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class TopWordsCountAnalyzer implements IWordCountAnalyzer
{
	public TopWordsCountAnalyzer() 
	{
		
	}
	
	private void addEntry(List<Entry<String, Integer>> list, String word, int count)
	{
		list.add(new AbstractMap.SimpleEntry<String, Integer>(word, count));
	}
	
	private void insertEnty(List<Entry<String, Integer>> list, String word, int count, int index)
	{	
		list.add(index, new AbstractMap.SimpleEntry<String, Integer>(word, count));
	}
	
	/**
	 * Returns -1 if the list is empty, the index in which insert the entry otherwise.
	 * @param list
	 * @param count
	 * @return
	 */
	private int findInsertIndex(List<Entry<String, Integer>> list, int count)
	{
		int index = -1;
		int i = 0;
		
		for (; i < list.size(); i++)
		{
			if (count >= list.get(i).getValue())
				return i;
		}
		
		if (!list.isEmpty())
			index = i;
		
		return index;
	}
	
	public List<Entry<String, Integer>> getSortedWords(int maxWords, IWordCountResult wordCountResult)
	{
		List<Entry<String, Integer>> sortedWords = new ArrayList<Entry<String, Integer>>();
		int minCount;
		String[] words = wordCountResult.getWords();
		
		addEntry(sortedWords, words[0], minCount = wordCountResult.getCount(words[0]));
		
		for (int i = 1; i < words.length; i++)
		{
			int wordCount = wordCountResult.getCount(words[i]);
			
			// skips words that cannot enter the list
			if (sortedWords.size() == maxWords && wordCount < minCount)
				continue;
			
			int insertIndex = findInsertIndex(sortedWords, wordCount);
			insertEnty(sortedWords, words[i], wordCountResult.getCount(words[i]), insertIndex);
			
			if (wordCount < minCount)
				minCount = wordCount;
			
			// trims the collection
			if (sortedWords.size() > maxWords)
			{
				for (int k = maxWords; k < sortedWords.size(); k++)
				{
					sortedWords.remove(k);
				}
			}
		}
		
		return sortedWords;
	}
}