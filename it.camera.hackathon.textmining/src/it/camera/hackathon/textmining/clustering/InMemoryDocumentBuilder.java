package it.camera.hackathon.textmining.clustering;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Builds a Document that is stored in-memory.
 * @author JCC
 *
 */
public class InMemoryDocumentBuilder implements IDocumentBuilder 
{	
	private InMemoryDocument document;
	
	public InMemoryDocumentBuilder() {
		this.document = new InMemoryDocument();
	}

	@Override
	public IDocument getDocument() {
		return this.document;
	}

	@Override
	public void addTerm(ITerm term) {
		this.document.addTerm(term);
	}
	
	@Override
	public void addTerm(String term) {
		this.document.addTerm(new Term(term));
	}

	@Override
	public void addTerm(ITerm term, int frequency) {
		this.document.addTerm(term, frequency);	
	}
	
	@Override
	public void addTerm(String term, int frequency) {
		this.document.addTerm(new Term(term), frequency);	
	}

	@Override
	public void removeTerm(ITerm term) {
		this.document.removeTerm(term);
	}
	
	@Override
	public void removeTerm(String term) {
		this.document.removeTerm(new Term(term));
	}

	@Override
	public void setFrequency(ITerm term, int frequency) {
		this.document.setFrequency(term, frequency);
	}
	
	@Override
	public void setFrequency(String term, int frequency) {
		this.document.setFrequency(new Term(term), frequency);
	}

	@Override
	public void incFrequency(ITerm term) {
		this.document.incFrequency(term);
	}
	
	@Override
	public void incFrequency(String term) {
		this.document.incFrequency(new Term(term));
	}

	@Override
	public void decFrequency(ITerm term) {
		this.document.decFrequency(term);
	}
	
	@Override
	public void decFrequency(String term) {
		this.document.decFrequency(new Term(term));
	}

	@Override
	public ITerm getTerm(String term) {
		return this.document.getTerm(term);
	}

	@Override
	public ITerm[] getTerms() {
		return this.document.getTerms();
	}

	private class InMemoryDocument implements IDocument
	{
		private Map<ITerm, Integer> frequencies;
		private int totalTermsCount;
		
		public InMemoryDocument()
		{
			frequencies = new HashMap<ITerm, Integer>();
			totalTermsCount = 0;
		}

		@Override
		public boolean contains(ITerm term) {
			return frequencies.containsKey(term);
		}

		@Override
		public boolean contains(String term) {
			return contains(new Term(term));
		}

		@Override
		public Iterator<ITerm> iterator() {
			return frequencies.keySet().iterator();
		}

		@Override
		public ITerm getTerm(String term) {
			for (ITerm t : frequencies.keySet())
			{
				if (t.getString() == term)
					return t;
			}
			return null;
		}

		@Override
		public ITerm[] getTerms() {
			return frequencies.keySet().toArray(new ITerm[0]);
		}
		
		@Override
		public Map<ITerm, Integer> getFrequencyByTerm() {
			HashMap<ITerm, Integer> map = new HashMap<ITerm, Integer>();
			for (ITerm term : frequencies.keySet())
			{
				map.put(term, getFrequency(term));
			}
			return map;
		}

		@Override
		public Map<ITerm, Float> getWeightedFrequencyByTerm() {
			HashMap<ITerm, Float> map = new HashMap<ITerm, Float>();
			for (ITerm term : frequencies.keySet())
			{
				map.put(term, getWeightedFrequency(term));
			}
			return map;
		}
		
		@Override
		public int getFrequency(ITerm term) {
			if (!frequencies.containsKey(term))
				return 0;
			
			return frequencies.get(term);
		}

		@Override
		public int getFrequency(String term) {
			return getFrequency(new Term(term));
		}

		/**
		 * La frequenza con peso logaritmico di un termine t in di è
		 * w(t,d) = 1 + log10[tf(t, d)] se tf(t,d) > 0
		 * w(t,d) = 0 altrimenti
		 * @param term
		 * @return
		 */
		@Override
		public float getWeightedFrequency(ITerm term) {
			float tf = getFrequency(term);
			if (tf > 0)
			{
				return (float) (1 + Math.log10(tf));
			}
			else return 0.0f;
		}
		
		@Override
		public float getWeightedFrequency(String term) {
			return getWeightedFrequency(new Term(term));
		}
		
		@Override
		public int getTotalTermsCount()
		{
			return totalTermsCount;
		}
		
		// "PRIVATE" METHODS
		
		public void addTerm(ITerm term) {
			if (!frequencies.containsKey(term))
				frequencies.put(term, 1);
			else
				frequencies.put(term, frequencies.get(term) + 1);
			
			totalTermsCount++;
		}

		public void addTerm(ITerm term, int frequency) {
			if (frequency < 1)
				throw new IllegalArgumentException("frequency < 1");
			
			if (!frequencies.containsKey(term))
				frequencies.put(term, frequency);
			else
				frequencies.put(term, frequencies.get(term) + frequency);
			
			totalTermsCount += frequency;
		}

		public void removeTerm(ITerm term) {
			if (frequencies.containsKey(term))
			{
				totalTermsCount -= frequencies.get(term);
				frequencies.remove(term);
			}
		}

		public void setFrequency(ITerm term, int frequency) {
			if (frequency < 1)
				throw new IllegalArgumentException("frequency < 1");
			if (!frequencies.containsKey(term))
				throw new IllegalArgumentException("term not found");
			
			int prevFrequency = frequencies.get(term);
			frequencies.put(term, frequency);
			totalTermsCount += (frequency - prevFrequency);
		}
		
		public void incFrequency(ITerm term) {
			frequencies.put(term, frequencies.get(term) + 1);
			totalTermsCount ++;
		}

		public void decFrequency(ITerm term) {
			int curFreq = frequencies.get(term);
			if (curFreq > 1)
			{
				frequencies.put(term, curFreq - 1);
				totalTermsCount--;
			}
			else throw new IllegalArgumentException("curFreq <= 1, use removeTerm() to perform this operation");
		}
	}
}