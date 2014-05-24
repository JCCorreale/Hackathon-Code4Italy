package it.camera.hackathon.tests;

import static org.junit.Assert.*;
import it.camera.hackathon.OptimizedTermsDisambiguator;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.BeforeClass;
import org.junit.Test;

public class TermsDisambiguatorTest 
{
	private static List<Entry<String, Integer>> termsToTests = new ArrayList<Map.Entry<String,Integer>>();
	private OptimizedTermsDisambiguator td = new OptimizedTermsDisambiguator();
	
	@BeforeClass
	public static void setup() 
	{
		termsToTests.add(new AbstractMap.SimpleEntry<String, Integer>("prova", 22));
		termsToTests.add(new AbstractMap.SimpleEntry<String, Integer>("prove", 44));
		termsToTests.add(new AbstractMap.SimpleEntry<String, Integer>("ciccione", 11));
		termsToTests.add(new AbstractMap.SimpleEntry<String, Integer>("provi", 2));
		termsToTests.add(new AbstractMap.SimpleEntry<String, Integer>("cicciona", 1));
	}
	
	@Test
	public void testDisambiguatorCount() 
	{
		Map<String, Integer> map = td.getDisambiguatedTerms(termsToTests);
		assertEquals(map.get("ciccione").intValue(), 11+1);
		assertEquals(map.get("prova").intValue(), 22+44+2);
	}
}
