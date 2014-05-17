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
		
		public InMemoryDocument()
		{
			frequencies = new HashMap<ITerm, Integer>();
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
		public int getFrequency(ITerm term) {
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
		
		public float getWeightedFrequency(String term) {
			return getWeightedFrequency(new Term(term));
		}
		
		// "PRIVATE" METHODS
		
		public void addTerm(ITerm term) {
			frequencies.put(term, 1);
		}

		public void addTerm(ITerm term, int frequency) {
			if (frequency < 1)
				throw new IllegalArgumentException("frequency < 1");
			
			frequencies.put(term, frequency);
		}

		public void removeTerm(ITerm term) {
			frequencies.remove(term);
		}

		public void setFrequency(ITerm term, int frequency) {
			frequencies.put(term, frequency);
		}
		
		public void incFrequency(ITerm term) {
			frequencies.put(term, frequencies.get(term) + 1);
		}

		public void decFrequency(ITerm term) {
			int curFreq = frequencies.get(term);
			if (curFreq > 1)
				frequencies.put(term, curFreq - 1);
			else
				throw new IllegalArgumentException("curFreq <= 1, use removeTerm() to perform this operation");
		}	
	}
}