package it.camera.hackathon.textmining.scraping.test;

import static org.junit.Assert.*;
import it.camera.hackathon.textmining.HtmlRemover;
import it.camera.hackathon.textmining.scraping.SynonimScraper;

import org.junit.Test;

public class ScraperTest 
{
	public static String mucca = "mucca", vacca ="vacca", stronzo = "stronzo";
	
	@Test
	public void testSearchForSynonims() 
	{
		assertNotNull(SynonimScraper.FindSynonims(mucca));
	}
	
	@Test
	public void testTrueSynonims1() 
	{
		assertTrue(SynonimScraper.areSynonims(mucca, vacca));
	}
	
	@Test
	public void testTrueSynonims2() 
	{
		assertTrue(SynonimScraper.areSynonims(vacca, mucca));
	}
	
	@Test
	public void testFalseSynonims1() 
	{
		assertFalse(SynonimScraper.areSynonims(mucca, stronzo));
	}
	
	@Test
	public void testFalseSynonims2() 
	{
		assertFalse(SynonimScraper.areSynonims(stronzo, vacca));
	}
}
