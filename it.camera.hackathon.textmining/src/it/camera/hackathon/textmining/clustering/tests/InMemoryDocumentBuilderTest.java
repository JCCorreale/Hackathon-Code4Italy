package it.camera.hackathon.textmining.clustering.tests;

import static org.junit.Assert.*;
import it.camera.hackathon.textmining.clustering.Concept;
import it.camera.hackathon.textmining.clustering.IDocument;
import it.camera.hackathon.textmining.clustering.IDocumentBuilder;
import it.camera.hackathon.textmining.clustering.InMemoryDocumentBuilder;
import it.camera.hackathon.textmining.clustering.Term;

import org.junit.Test;

public class InMemoryDocumentBuilderTest {

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
		
		// can't add a term with frequency = 0
		try
		{
			docBuilder.addTerm("un termine", 0);
		}
		catch (Exception e)
		{
			thrown = true;
		}
		
		assertTrue(thrown);
		
		// if a term has not been set or has been removed, should have frequency = 0
		
		assertEquals(0, doc.getFrequency("unknown term"));
		
		docBuilder.addTerm("altro termine ancora", 50);
		assertEquals(50, doc.getFrequency("altro termine ancora"));
		docBuilder.removeTerm("altro termine ancora");
		assertEquals(0, doc.getFrequency("altro termine ancora"));
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
	
	@Test
	public void testFrequencies()
	{
		IDocument doc;
		IDocumentBuilder builder = new InMemoryDocumentBuilder();
		doc = builder.getDocument();
		
		builder.addTerm("prova", 2);
		assertEquals(2, doc.getFrequency("prova"));
		builder.addTerm("prova", 2);
		assertEquals(4, doc.getFrequency("prova"));
		
		builder.setFrequency("prova", 7);
		assertEquals(7, doc.getFrequency("prova"));
		
		builder.decFrequency("prova");
		builder.incFrequency("prova");
		builder.decFrequency("prova");
		assertEquals(6, doc.getFrequency("prova"));
	}
	
	@Test
	public void testTotalTermsCount()
	{
		IDocument doc;
		IDocumentBuilder builder = new InMemoryDocumentBuilder();
		doc = builder.getDocument();
		
		builder.addTerm("t1", 2);
		builder.addTerm("t2", 5);
		assertEquals(7, doc.getTotalTermsCount());
		
		builder.addTerm("t2", 2);
		assertEquals(9, doc.getTotalTermsCount());
		
		builder.setFrequency("t2", 2);
		assertEquals(4, doc.getTotalTermsCount());
		
		builder.incFrequency("t1");
		builder.incFrequency("t2");
		assertEquals(6, doc.getTotalTermsCount());
		
		builder.decFrequency("t1");
		builder.decFrequency("t2");
		assertEquals(4, doc.getTotalTermsCount());
	}
}