package it.camera.hackathon.textmining.clustering;

public interface IDocumentBuilder 
{
	public IDocument getDocument();
	
	/**
	 * Adds a term with frequency 1.
	 * @param term
	 */
	public void addTerm(ITerm term);
	public void addTerm(ITerm term, int frequency);
	public void addTerm(String term);
	public void addTerm(String term, int frequency);
	public void removeTerm(ITerm term);
	public void removeTerm(String term);
	
	public void setFrequency(ITerm term, int frequency);
	public void incFrequency(ITerm term);
	public void decFrequency(ITerm term);
	public void setFrequency(String term, int frequency);
	public void incFrequency(String term);
	public void decFrequency(String term);
	
	public ITerm getTerm(String term);
	
	public ITerm[] getTerms();
}
