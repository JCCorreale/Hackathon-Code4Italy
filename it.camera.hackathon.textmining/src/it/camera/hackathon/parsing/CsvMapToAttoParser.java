package it.camera.hackathon.parsing;

import java.util.Map;

import it.camera.hackathon.Atto;
import it.camera.hackathon.datasource.sparql.query.Utils;

public class CsvMapToAttoParser implements IParser<Map<String, String>, Atto> {

	@Override
	public Atto parse(Map<String, String> input) {
		return new Atto(Utils.removeQuotesFromIRI(input.get("\"iriAtto\""))); // TODO definire in costruttore
	}

}
