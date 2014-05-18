package it.camera.hackathon.textmining.clustering;

public interface IDocument extends Iterable<ITerm>
{
	public boolean contains(ITerm term);
	
	public boolean contains(String term);
	
	public ITerm getTerm(String term);
	
	public ITerm[] getTerms();
	
	/**
	 * Le term frequency tf(t,d) di un termine t in un documento d è il numero di occorrenze di t in d.
	 * @param term
	 * @return
	 */
	public int getFrequency(ITerm term);
	
	public int getFrequency(String term);
		
	/**
	 * La frequenza con peso logaritmico di un termine t in di è
	 * w(t,d) = 1 + log10[tf(t, d)] se tf(t,d) > 0
	 * w(t,d) = 0 altrimenti
	 * @param term
	 * @return
	 */
	public float getWeightedFrequency(ITerm term);

	public float getWeightedFrequency(String term);
	
	public int getTotalTermsCount();
}