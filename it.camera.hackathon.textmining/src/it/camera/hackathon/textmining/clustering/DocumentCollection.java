package it.camera.hackathon.textmining.clustering;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DocumentCollection implements IDocumentCollection {

	private Set<IDocument> documents;
	
	public DocumentCollection() {
		this.documents = new HashSet<IDocument>();
	}

	@Override
	public Map<IDocument, Float> getFrequencyByDocument(ITerm term) {
		HashMap<IDocument, Float> frequencies = new HashMap<IDocument, Float>();
		// TODO
		return null;
	}
	

	@Override
	public Map<IDocument, Float> getIDFByDocument(ITerm term) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<IDocument, Float> getTFIDFByDocument(ITerm term) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float GetFrequenciesCosine(IDocument d1, IDocument d2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float GetIDFCosine(IDocument d1, IDocument d2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float GetTFIDFCosine(IDocument d1, IDocument d2) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Si definisce idf(t) = log10[N/df(t)], dove
	 * N è il numero di documenti nel dataset
	 * df(t) = è il numero di documenti nel dataset che contengono il termine t
	 * @param term
	 * @return
	 */
	@Override
	public float getIDF(ITerm term) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * E' definito come prodotto:
	 * log[1+tf(t,d)] x log[N/df(t)]
	 * @param term
	 * @return
	 */
	@Override
	public float getTFIDF(ITerm term) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDocumentsCount() {
		return this.documents.size();
	}
}
