package it.camera.hackathon;

import it.camera.hackathon.textmining.clustering.DocumentCollection;
import it.camera.hackathon.textmining.clustering.IDocument;
import it.camera.hackathon.textmining.clustering.IDocumentCollection;
import it.camera.hackathon.textmining.clustering.ITerm;
import it.camera.opendata.model.Atto;

import java.util.ArrayList;
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
	
	public AttoDocumentAnalyser(float minTfIdf, int maxTerms) 
	{
		this.minTfIdf = minTfIdf;
		this.maxTerms = maxTerms;
	}
	
	private List<ITerm> getTFIDFSortedTerms(Map<ITerm, Float> tfIdfs) {
		List<Entry<ITerm, Float>> termsTFIDF = new ArrayList<Entry<ITerm, Float>>();
		for (Entry<ITerm, Float> entry : tfIdfs.entrySet())
		{
			if (termsTFIDF.size() == 0) termsTFIDF.add(entry);
			else
			{
				int i = 0;
				
				for (; i < termsTFIDF.size(); i++)
				{
					if (termsTFIDF.get(i).getValue() > entry.getValue())
					{
						termsTFIDF.add(i, entry);
					}
				}
				
				if (i == termsTFIDF.size())
					 termsTFIDF.add(entry);
			}
		}

		List<ITerm> terms = new ArrayList<ITerm>();
		for (Entry<ITerm, Float> entry : termsTFIDF)
		{
			terms.add(entry.getKey());
		}
		return terms;
	}

	@Override
	public Map<Atto, List<ITerm>> getData(Iterable<Entry<Atto, IDocument>> args) throws IllegalArgumentException 
	{
		IDocumentCollection docs = buildDocumentCollection(args);
		Map<Atto, List<ITerm>> attoTerms = buildAttoTermsMap(args);
		// search for the most relevant terms for each document
		for (Atto atto : attoTerms.keySet())
		{
			IDocument doc = ((AttoConDocumento)attoTerms.get(atto)).getDocument();
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