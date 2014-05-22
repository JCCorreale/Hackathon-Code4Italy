package it.camera.hackathon.textmining.tests;

import static org.junit.Assert.*;
import it.camera.hackathon.textmining.ICharFilter;
import it.camera.hackathon.textmining.IWordComparer;
import it.camera.hackathon.textmining.IWordCountResult;
import it.camera.hackathon.textmining.IWordFilter;
import it.camera.hackathon.textmining.WordCounter;

import org.junit.BeforeClass;
import org.junit.Test;

public class WordCounterTest {

	public static String TEST = "testo Testo ciao cau bau";
	public static WordCounter wc = null;
	
	@BeforeClass
	public static void setup() {
		ICharFilter cf = new ICharFilter() {
			
			@Override
			public boolean accept(Character value) {
				return value != ' ';
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
	
	@Test
	public void test() {
		IWordCountResult wcr = wc.parse(TEST);

		assertEquals(wcr.getCount("testo"), 2);
		assertEquals(wcr.getCount("ciao"), 1);
		assertEquals(wcr.getCount("cau"), 1);
		assertEquals(wcr.getCount("bau"), 1);
		assertEquals(wcr.getCount(" "), 0);
		assertEquals(wcr.getCount("jeeean"), 0);
	}
}
