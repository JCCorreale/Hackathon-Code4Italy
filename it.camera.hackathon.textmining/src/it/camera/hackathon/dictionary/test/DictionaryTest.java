package it.camera.hackathon.dictionary.test;

import static org.junit.Assert.*;
import it.camera.hackathon.dictionary.SynonimsDictionary;

import org.junit.Test;

public class DictionaryTest 
{
	private SynonimsDictionary dict = new SynonimsDictionary();
	
	@Test
	public void testSynonimsExists() 
	{
		assertNotNull(dict.getSynonims("prova"));
	}
	
	@Test
	public void testSynonimsExistsForPluralNoun() 
	{
		assertNotNull(dict.getSynonims("prove"));
	}

	@Test
	public void testSynonimsNoExists() 
	{
		assertNull(dict.getSynonims("asdgkdsa"));
	}
}
