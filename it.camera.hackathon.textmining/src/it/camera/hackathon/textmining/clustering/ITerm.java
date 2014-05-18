package it.camera.hackathon.textmining.clustering;

/**
 * Represent a term that refers to a concept (in a context that is assumed to be fixed, so that there's no ambiguity
 * between a term and the concept it refers to). A term equals to another term if their string representation is equal.
 * The string representation of a term is the term itself.
 * 
 * @author JCC
 *
 */
public interface ITerm extends Comparable<ITerm>
{
	public String getString();
	
	/**
	 * A term refers to a concept. TODO: a term refers to a concept in a certain CONTEXT.
	 * @return
	 */
	public IConcept getConcept();
	
	@Override
	public String toString();
}
