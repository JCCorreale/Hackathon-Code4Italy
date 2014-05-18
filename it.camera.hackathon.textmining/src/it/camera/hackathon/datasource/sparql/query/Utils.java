package it.camera.hackathon.datasource.sparql.query;

public class Utils {
	public static String removeQuotesFromIRI(String iri) {
		return iri.substring(1, iri.length() - 1);
	}
}
