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
	
	/**
	 * Calculated over all terms in the data set.
	 * @param doc
	 * @return
	 */
	public Map<ITerm, Float> getTFIDFByTerm(IDocument doc);
	
	public float getFrequenciesCosine(IDocument d1, IDocument d2);
	
	public float getIDFCosine(IDocument d1, IDocument d2);
	
	public float getTFIDFCosine(IDocument d1, IDocument d2);
	
	/**
	 * Returns a vector representing the TFIDF value of the centroid of a set of documents.
	 * @param documents
	 * @return
	 */
	public float[] getDocumentsTFIDFCentroid(Iterable<IDocument> documents);
	
	/**
	 * Returns a vector representing the TFIDF values of a document in this collection.
	 * @param doc
	 * @return
	 */
	public float[] getDocumentTFIDFVector(IDocument doc);
	
	/**
	 * Returns the position of the given term in the vector returned by this collection.
	 * @param term
	 * @return
	 */
	public int getVectorPosition(ITerm term);
	
	/**
	 * Returns the term at the given position in the vector returned by this collection.
	 * @param position
	 * @return
	 */
	public ITerm getVectorTerm(int position);
	
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