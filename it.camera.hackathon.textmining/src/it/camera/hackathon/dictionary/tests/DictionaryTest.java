package it.camera.hackathon.dictionary.tests;

import static org.junit.Assert.*;
import it.camera.hackathon.dictionary.SynonimsDictionary;

import org.junit.Test;

public class DictionaryTest 
{
	private SynonimsDictionary dict = new SynonimsDictionary();
	
	@Test
	public void testSynonimsExists() 
	{
		assertTrue(dict.getSynonims("prova").size() > 0);
	}
	
	@Test
	public void testSynonimsExistsForPluralNoun() 
	{
		assertTrue(dict.getSynonims("prove").size() > 0);
	}
	
	@Test
	public void testSynonimsExistsForConiugatedVerb() 
	{
		assertTrue(dict.getSynonims("provammo").size() > 0);
		assertTrue(dict.getSynonims("provarono").size() > 0);
	}
	
	@Test
	public void testSynonimsExistsForTruncatedTerms() 
	{
		assertTrue(dict.getSynonims("prov").size() > 0);
	}

	@Test
	public void testSynonimsNoExists() 
	{
		assertTrue(dict.getSynonims("asdgkdsa").size() == 0);
	}
	
	@Test
	public void testAreSynonims()
	{
		assertTrue(dict.areSynonims("vacca", "mucca"));
	}
	
	@Test
	public void testArentSynonims()
	{
		assertFalse(dict.areSynonims("mucca", "toro"));
	}
}
