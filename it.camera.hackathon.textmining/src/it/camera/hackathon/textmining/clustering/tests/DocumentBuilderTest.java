package it.camera.hackathon.textmining.clustering.tests;

import static org.junit.Assert.*;
import it.camera.hackathon.textmining.clustering.Concept;
import it.camera.hackathon.textmining.clustering.IDocument;
import it.camera.hackathon.textmining.clustering.IDocumentBuilder;
import it.camera.hackathon.textmining.clustering.InMemoryDocumentBuilder;
import it.camera.hackathon.textmining.clustering.Term;

import org.junit.Test;

public class DocumentBuilderTest {

	@Test
	public void testBuildDoc() 
	{
		IDocumentBuilder docBuilder = new InMemoryDocumentBuilder();
		IDocument doc;
		
		docBuilder.addTerm("termini", 32);
		docBuilder.addTerm("a");
		docBuilder.addTerm("caso", 128);
		docBuilder.addTerm("solo", 1);
		docBuilder.addTerm("per");
		docBuilder.addTerm("provare", 42);
		
		doc = docBuilder.getDocument();
		
		assertEquals(32, doc.getFrequency("termini"));
		assertEquals(1, doc.getFrequency("a"));
		assertEquals(128, doc.getFrequency("caso"));
		assertEquals(1, doc.getFrequency("solo"));
		assertEquals(1, doc.getFrequency("per"));
		assertEquals(42, doc.getFrequency("provare"));
		
		boolean thrown = false;
		
		try
		{
			docBuilder.addTerm("un termine", 0);
		}
		catch (Exception e)
		{
			thrown = true;
		}
		
		assertTrue(thrown);
	}
	
	@Test
	public void testIncDec()
	{
		IDocument doc;
		Term tmpTerm;
		IDocumentBuilder builder = new InMemoryDocumentBuilder();
		builder.addTerm(tmpTerm = new Term("termine",  new Concept("concetto")));
		
		// just for fun, try some different ways to reference a term
		builder.incFrequency("termine");
		builder.incFrequency(tmpTerm);
		builder.incFrequency(new Term("termine"));
		
		doc = builder.getDocument();
		
		// there should be 4 occurences of "termine"
		assertEquals(4, doc.getFrequency("termine"));
	}

}
