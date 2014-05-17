package it.camera.hackathon.textmining.clustering;

/**
 * Represent a term that refers to a concept (in a context that is assumed to be fixed, so that there's no ambiguity
 * between a term and the concept it refers to). A term equals to another term if their string representation is equal.
 * 
 * @author JCC
 *
 */
public class Term implements ITerm {
	
	private String term;
	private IConcept concept;

	public Term(String term, IConcept concept) {
		this.term = term;
		this.concept = concept;
	}
	
	/**
	 * Automatically creates the concept the term refers to. The concept is named after the given term.
	 * @param term
	 */
	public Term(String term) {
		this(term, new Concept(term));
	}

	@Override
	public String getString() {
		return this.term;
	}

	/**
	 * A term refers to a concept. TODO: a term refers to a concept in a certain CONTEXT.
	 * @return
	 */
	@Override
	public IConcept getConcept() {
		return this.concept;
	}
	
	@Override
	public String toString()
	{
		return this.term;
	}
	
	public boolean equals(Object obj)
	{
		return obj instanceof Term && ((Term)obj).getString().equals(this.term);
	}
	
	public int hashCode()
	{
		return this.term.hashCode();
	}
}