package it.camera.hackathon.parsing;

import java.util.Iterator;

import it.camera.hackathon.Atto;

/**
 * Builds an Atto from an Iterable collection of strings.
 * The Strings must be in the correct order: iri, name
 */
public class StringToAttoParser implements IParser<Iterable<String>, Atto> {

	@Override
	public Atto parse(Iterable<String> input) {
		String iri;
		// String name;
		
		Iterator<String> i = input.iterator();
		
		iri = i.next();
		// name = i.next();
		
		return new Atto(iri);
	}

}
