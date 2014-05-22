package it.camera.hackathon.datasource.sparql;

import it.camera.hackathon.parsing.IParser;

// TODO probably useless
public interface IVirtuosoQueryResultProcessor<T> extends IQueryResultProcessor<String, T> {
	IParser<String, T> getResultParser();
}
