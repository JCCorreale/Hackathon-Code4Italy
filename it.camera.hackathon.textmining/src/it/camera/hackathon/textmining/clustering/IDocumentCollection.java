package it.camera.hackathon.textmining.clustering;

import java.util.Map;

public interface IDocumentCollection
{
	public IDocument[] getContainingDocuments(ITerm term);
	
	public IDocument[] getContainingDocuments(String term);
	
	public int getContainingDocumentsCount(ITerm term);
	
	public int getContainingDocumentsCount(String term);
	
	public Map<IDocument, Integer> getFrequencyByDocument(ITerm term);
	
	public Map<IDocument, Float> getWeightedFrequencyByDocument(ITerm term);
	
	public Map<ITerm, Float> getIDFByTerm();
	
	public Map<IDocument, Float> getTFIDFByDocument(ITerm term);
	
	public Map<ITerm, Float> getTFIDFByTerm(IDocument doc);
	
	public float getFrequenciesCosine(IDocument d1, IDocument d2);
	
	public float getIDFCosine(IDocument d1, IDocument d2);
	
	public float getTFIDFCosine(IDocument d1, IDocument d2);
	
	/**
	 * Si definisce idf(t) = log10[N/df(t)], dove
	 * N è il numero di documenti nel dataset
	 * df(t) = è il numero di documenti nel dataset che contengono il termine t
	 * @param term
	 * @return
	 */
	public float getIDF(ITerm term);
	
	/**
	 * E' definito come prodotto:
	 * log[1+tf(t,d)] x log[N/df(t)]
	 * @param term
	 * @return
	 */
	public float getTFIDF(ITerm term, IDocument doc);
	
	public IDocument[] getDocuments();
	
	public int getDocumentsCount();
	
	public ITerm[] getAllTerms();
	
	public int getAllTermsCount();
}