package it.camera.hackathon.textmining.clustering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * This implementation assumes that the documents are completely defined when they're passed to the constructor
 * (no event from IDocument is handled). This class may not work properly if the documents are changed after they're
 * passed to the constructor.
 * @author JCC
 *
 */
public class DocumentCollection implements IDocumentCollection {

	private Set<IDocument> documents;
	private SortedSet<ITerm> allTerms;
	
	public DocumentCollection(IDocument... documents) {
		if (documents.length < 1)
			throw new IllegalArgumentException("documents.length < 1");
		
		this.documents = new HashSet<IDocument>();
		this.allTerms = new TreeSet<ITerm>();
		for (IDocument doc : documents)
		{
			this.documents.add(doc);
			for (ITerm term : doc.getTerms())
				this.allTerms.add(term);
		}
	}
	
	@Override
	public IDocument[] getContainingDocuments(ITerm term)
	{
		List<IDocument> docs = new ArrayList<IDocument>();
		for (IDocument doc : this.documents)
		{
			if (doc.contains(term))
				docs.add(doc);
		}
		return docs.toArray(new IDocument[0]);
	}
	
	@Override
	public IDocument[] getContainingDocuments(String term)
	{
		return getContainingDocuments(new Term(term));
	}
	
	@Override
	public int getContainingDocumentsCount(ITerm term)
	{
		return getContainingDocuments(term).length;
	}
	
	@Override
	public int getContainingDocumentsCount(String term)
	{
		return getContainingDocuments(new Term(term)).length;
	}

	@Override
	public Map<IDocument, Integer> getFrequencyByDocument(ITerm term) {
		HashMap<IDocument, Integer> frequencies = new HashMap<IDocument, Integer>();
		for (IDocument doc : this.documents)
		{
			frequencies.put(doc, doc.getFrequency(term));
		}
		return frequencies;
	}
	
	@Override
	public Map<IDocument, Float> getWeightedFrequencyByDocument(ITerm term) {
		HashMap<IDocument, Float> frequencies = new HashMap<IDocument, Float>();
		for (IDocument doc : this.documents)
		{
			frequencies.put(doc, doc.getWeightedFrequency(term));
		}
		return frequencies;
	}
	
	@Override
	public Map<ITerm, Float> getIDFByTerm() {
		HashMap<ITerm, Float> idfs = new HashMap<ITerm, Float>();
		for (ITerm term : this.allTerms)
		{
			idfs.put(term, this.getIDF(term));
		}
		return idfs;
	}

	@Override
	public Map<IDocument, Float> getTFIDFByDocument(ITerm term) {
		HashMap<IDocument, Float> tfidfs = new HashMap<IDocument, Float>();
		for (IDocument doc : this.documents)
		{
			tfidfs.put(doc, this.getTFIDF(term, doc));
		}
		return tfidfs;
	}
	
	@Override
	public Map<ITerm, Float> getTFIDFByTerm(IDocument doc)
	{
		HashMap<ITerm, Float> tfidfs = new HashMap<ITerm, Float>();
		for (ITerm term : this.allTerms)
		{
			tfidfs.put(term, this.getTFIDF(term, doc));
		}
		return tfidfs;
	}

	@Override
	public float getFrequenciesCosine(IDocument d1, IDocument d2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getIDFCosine(IDocument d1, IDocument d2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getTFIDFCosine(IDocument d1, IDocument d2) {
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
		int n = this.getDocumentsCount();
		int df = this.getContainingDocumentsCount(term);
		return (float)Math.log10(n/df);
	}

	/**
	 * E' definito come prodotto:
	 * log[1+tf(t,d)] x log[N/df(t)]
	 * @param term
	 * @return
	 */
	@Override
	public float getTFIDF(ITerm term, IDocument doc) {
		int n = this.getDocumentsCount();
		float tf = doc.getFrequency(term);
		float df = this.getContainingDocumentsCount(term);
		return (float)(
				Math.log(1 + tf) *
				Math.log(n / df));
	}
	
	@Override
	public IDocument[] getDocuments()
	{
		return this.documents.toArray(new IDocument[0]);
	}
	
	@Override
	public int getDocumentsCount() {
		return this.documents.size();
	}
	
	@Override
	public ITerm[] getAllTerms()
	{
		return this.allTerms.toArray(new ITerm[0]);
	}
	
	@Override
	public int getAllTermsCount()
	{
		return this.allTerms.size();
	}
}