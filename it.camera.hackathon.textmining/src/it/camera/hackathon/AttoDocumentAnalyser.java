package it.camera.hackathon;

import it.camera.hackathon.textmining.clustering.DocumentCollection;
import it.camera.hackathon.textmining.clustering.IDocument;
import it.camera.hackathon.textmining.clustering.IDocumentCollection;
import it.camera.hackathon.textmining.clustering.ITerm;
import it.camera.opendata.model.Atto;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class AttoDocumentAnalyser implements IAttoDocumentAnalyser {

	private static Map<Atto, List<ITerm>> buildAttoTermsMap(Iterable<Entry<Atto, IDocument>> args)
	{
		Map<Atto, List<ITerm>> attoTerms = new HashMap<Atto, List<ITerm>>();
		for (Entry<Atto, IDocument> entry : args)
		{
			AttoConDocumento attoDoc = new AttoConDocumento(entry.getKey(), entry.getValue());
			attoTerms.put(attoDoc, new ArrayList<ITerm>());
		}
		return attoTerms;
	}
	
	private static IDocumentCollection buildDocumentCollection(Iterable<Entry<Atto, IDocument>> args)
	{
		List<IDocument> docs = new ArrayList<IDocument>();
		for (Entry<Atto, IDocument> entry : args)
		{
			docs.add(entry.getValue());
		}
		return new DocumentCollection(docs.toArray(new IDocument[0]));
	}
	
	private float minTfIdf;
	private int maxTerms;
	
	/**
	 * 
	 * @param minTfIdf
	 * @param maxTerms -1 = no limit
	 */
	public AttoDocumentAnalyser(float minTfIdf, int maxTerms) 
	{
		this.minTfIdf = minTfIdf;
		this.maxTerms = maxTerms;
	}
	
	private List<ITerm> getTFIDFSortedTerms(Map<ITerm, Float> tfIdfs) {
		List<Entry<ITerm, Float>> termsTFIDF = new ArrayList<Entry<ITerm, Float>>();
		termsTFIDF.addAll(tfIdfs.entrySet());
		Collections.sort(termsTFIDF, new Utils.EntryValueComparator(false));

		List<ITerm> terms = new ArrayList<ITerm>();
		for (Entry<ITerm, Float> entry : termsTFIDF)
		{
			if (this.maxTerms >= 0 && terms.size() == this.maxTerms)
				break;
			
			if (entry.getValue() > this.minTfIdf)
				terms.add(entry.getKey());
		}
		return terms;
	}

	@Override
	public Map<Atto, List<ITerm>> getData(Iterable<Entry<Atto, IDocument>> attoDoc) throws IllegalArgumentException 
	{
		IDocumentCollection docs = buildDocumentCollection(attoDoc);
		Map<Atto, List<ITerm>> attoTerms = buildAttoTermsMap(attoDoc);
		// search for the most relevant terms for each document
		for (Atto atto : attoTerms.keySet())
		{
			IDocument doc = ((AttoConDocumento)atto).getDocument();
			// the TF-IDF for each term of the data set, calculated for the document doc
			Map<ITerm, Float> tfIdfs = docs.getTFIDFByTerm(doc);
			List<ITerm> sortedTerms = getTFIDFSortedTerms(tfIdfs);
			for (ITerm term : sortedTerms)
			{
				if (tfIdfs.get(term) > this.minTfIdf)
					attoTerms.get(atto).add(term);
				if (attoTerms.get(atto).size() == this.maxTerms)
					break;
			}
		}
		return attoTerms;
	}
}