package it.camera.hackathon.textmining.clustering;

import java.util.Map;

public interface IDocumentCollection 
{
	public Map<IDocument, Float> getFrequencyByDocument(ITerm term);
	
	public Map<IDocument, Float> getIDFByDocument(ITerm term);
	
	public Map<IDocument, Float> getTFIDFByDocument(ITerm term);
	
	public float GetFrequenciesCosine(IDocument d1, IDocument d2);
	
	public float GetIDFCosine(IDocument d1, IDocument d2);
	
	public float GetTFIDFCosine(IDocument d1, IDocument d2);
	
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
	public float getTFIDF(ITerm term);
	
	public int getDocumentsCount();
}