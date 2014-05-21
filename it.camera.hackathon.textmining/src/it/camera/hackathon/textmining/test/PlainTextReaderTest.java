package it.camera.hackathon.textmining.test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;

import it.camera.hackathon.textmining.ICharFilter;
import it.camera.hackathon.textmining.IWordComparer;
import it.camera.hackathon.textmining.IWordCountResult;
import it.camera.hackathon.textmining.IWordFilter;
import it.camera.hackathon.textmining.TopWordsCountAnalyzer;
import it.camera.hackathon.textmining.WordCounter;
import it.camera.hackathon.utils.StringUtils;

import org.junit.BeforeClass;
import org.junit.Test;

public class PlainTextReaderTest 
{
	String filename = "resources/text" + File.separator + "PlainText";
	
	public static WordCounter wc = null;
	
	@BeforeClass
	public static void setup() 
	{
		ICharFilter cf = new ICharFilter() 
		{	
			@Override
			public boolean accept(Character value) 
			{
				return Character.isAlphabetic(value);
			}
		};
		
		IWordFilter wf = new IWordFilter() 
		{	
			@Override
			public boolean accept(String value) 
			{
				return true;
			}
		};
		
		IWordComparer comparator = new IWordComparer() 
		{
			@Override
			public boolean areEqualWords(String w1, String w2) 
			{
				return w1.toLowerCase().equals(w2.toLowerCase());
			}
		};
		
		wc = new WordCounter(cf, wf, comparator);
	}
	
	@Test
	public void testWordCounter() throws IOException 
	{
		String text = StringUtils.readTextFile(filename);
		
		IWordCountResult result = wc.parse(text);
		
		for (String word : result.getWords())
		{
			System.out.println(word + " " + result.getCount(word));
		}
	}
	
	@Test
	public void testTopWordsCount() throws IOException 
	{
		String text = StringUtils.readTextFile(filename);
		
		IWordCountResult result = wc.parse(text);
		
		TopWordsCountAnalyzer topWordsAnalyzer = new TopWordsCountAnalyzer();
		
		int maxWords = 10;
		
		List<Entry<String, Integer>> chart = topWordsAnalyzer.getSortedWords(maxWords, result);
		
		for (Entry<String, Integer> entry : chart)
		{
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}
}
