package it.camera.hackathon;

import it.camera.hackathon.textmining.clustering.IDocument;
import it.camera.hackathon.textmining.clustering.IDocumentBuilder;
import it.camera.hackathon.textmining.clustering.ITerm;
import it.camera.hackathon.textmining.clustering.InMemoryDocumentBuilder;
import it.camera.hackathon.textmining.clustering.Term;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class DocumentTextBuilder implements it.camera.hackathon.ITextDocumentBuilder {

	/**
	 * Replaces synonyms with single TERMS (each related to a single CONCEPT).
	 * @param topWords
	 * @return
	 */
	private static List<Entry<ITerm, Integer>> getTerms(List<Entry<String, Integer>> topWords)
	{
		List<Entry<ITerm, Integer>> terms = new ArrayList<Entry<ITerm, Integer>>();
		
		// TODO Handle Synonims
		for (Entry<String, Integer> entry : topWords)
		{
			terms.add(new AbstractMap.SimpleEntry<ITerm, Integer>(new Term(entry.getKey()), entry.getValue()));
		}
		
		return terms;
	}
	
	private static IDocument buildDocument(List<Entry<String, Integer>> topWords, int totalWords)
	{
		List<Entry<ITerm, Integer>> terms = getTerms(topWords);
		IDocumentBuilder builder = new InMemoryDocumentBuilder();
		
		for (Entry<ITerm, Integer> entry : terms)
		{
			builder.addTerm(entry.getKey(), entry.getValue());
		}
		
		return builder.getDocument();
	}
	
	private int wordCount;
	
	public DocumentTextBuilder(int wordCount) {
		this.wordCount = wordCount;
	}
	
	@Override
	public IDocument getData(List<Entry<String, Integer>> args)
			throws IllegalArgumentException {

		return buildDocument(args, wordCount);
	}

}
