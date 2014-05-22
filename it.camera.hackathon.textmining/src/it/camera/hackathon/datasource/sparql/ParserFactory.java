package it.camera.hackathon.datasource.sparql;

import it.camera.hackathon.parsing.IParser;

// TODO
public class ParserFactory {
	public static IParser<?, ?> get(String type, String format) {
		switch(format) {
		case "text/csv":
			break;
		default:
			throw new UnsupportedOperationException();
		}
		return null;
	}
}
