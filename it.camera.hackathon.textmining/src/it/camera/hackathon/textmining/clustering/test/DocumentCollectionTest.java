package it.camera.hackathon.textmining.clustering.test;

import static org.junit.Assert.*;

import java.util.Arrays;

import it.camera.hackathon.textmining.clustering.DocumentCollection;
import it.camera.hackathon.textmining.clustering.IDocumentBuilder;
import it.camera.hackathon.textmining.clustering.IDocumentCollection;
import it.camera.hackathon.textmining.clustering.InMemoryDocumentBuilder;
import it.camera.hackathon.textmining.clustering.Term;

import org.junit.Test;

public class DocumentCollectionTest 
{
	@Test
	public void test() 
	{
		IDocumentBuilder b1 = new InMemoryDocumentBuilder();
		IDocumentBuilder b2 = new InMemoryDocumentBuilder();
		
		b1.addTerm("primo", 3);
		b1.addTerm("secondo");
		b2.addTerm("terzo", 9);
		b2.addTerm("quarto");

		// The Document collection does NOT update if the document are changed
		IDocumentCollection dc = new DocumentCollection(b1.getDocument(), b2.getDocument());
		
		assertEquals(4, dc.getAllTermsCount());
		assertTrue(Arrays.binarySearch(dc.getAllTerms(), Term.fromString("primo")) >= 0);
		assertTrue(Arrays.binarySearch(dc.getAllTerms(), Term.fromString("secondo")) >= 0);
		assertTrue(Arrays.binarySearch(dc.getAllTerms(), Term.fromString("terzo")) >= 0);
		assertTrue(Arrays.binarySearch(dc.getAllTerms(), Term.fromString("quarto")) >= 0);
	}
}