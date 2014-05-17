package it.camera.hackathon.textmining.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map.Entry;

import it.camera.hackathon.textmining.ICharFilter;
import it.camera.hackathon.textmining.IWordComparer;
import it.camera.hackathon.textmining.IWordCountResult;
import it.camera.hackathon.textmining.IWordFilter;
import it.camera.hackathon.textmining.TopWordsCountAnalyzer;
import it.camera.hackathon.textmining.WordCounter;

import org.junit.BeforeClass;
import org.junit.Test;

public class TopWordCountAnalyzerTest {

	public static String text = "one two two three three three four four four four only 1 occurence for this";
	public static WordCounter wc = null;
	

	@BeforeClass
	public static void setup() {
		ICharFilter cf = new ICharFilter() {
			
			@Override
			public boolean accept(Character value) {
				return Character.isAlphabetic(value);
			}
		};
		
		IWordFilter wf = new IWordFilter() {
			
			@Override
			public boolean accept(String value) {
				return true;
			}
		};
		
		IWordComparer comparator = new IWordComparer() {
			
			@Override
			public boolean areEqualWords(String w1, String w2) {
				return w1.toLowerCase().equals(w2.toLowerCase());
			}
		};
		
		wc = new WordCounter(cf, wf, comparator);
	}
	
	static String readFile(String path, Charset encoding) throws IOException 
	{
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
	
	@Test
	public void test() 
	{
		IWordCountResult result = wc.parse(text);
		
		TopWordsCountAnalyzer topWordsAnalyzer = new TopWordsCountAnalyzer();
		
		int maxWords = 5;
		
		List<Entry<String, Integer>> chart = topWordsAnalyzer.getSortedWords(maxWords, result);
//		for (Entry<String, Integer> entry : chart)
//		{
//			System.out.println(entry.getKey() + " " + entry.getValue());
//		}
		
		assertEquals(chart.get(0).getKey(), "four");
		assertEquals(chart.get(0).getValue(), (Integer)4);
		assertEquals(chart.get(1).getKey(), "three");
		assertEquals(chart.get(1).getValue(), (Integer)3);
		assertEquals(chart.get(2).getKey(), "two");
		assertEquals(chart.get(2).getValue(), (Integer)2);
		
		for (int i = 3; i < maxWords; i++)
		{
			assertEquals(chart.get(i).getValue(), (Integer)1);
		}
	}
}
