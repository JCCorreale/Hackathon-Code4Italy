package it.camera.hackathon.datasource.sparql;

import java.util.Iterator;

import it.camera.hackathon.datasource.remote.HttpGetDataSource;
import it.camera.hackathon.parsing.IParser;

public class VirtuosoSingleResultQueryEngine<T> implements
		ISingleResultQueryEngine<T> {

	private VirtuosoQueryEngine<T> queryEngine;
	
	public VirtuosoSingleResultQueryEngine(VirtuosoQueryEngine<T> queryEngine) {
		if(queryEngine == null)
			throw new IllegalArgumentException();
		
		this.queryEngine = queryEngine;
	}
	
	public VirtuosoSingleResultQueryEngine(HttpGetDataSource<String> source, IParser<String, Iterable<T>> resultParser, String requestedFormat) {
		this.queryEngine = new VirtuosoQueryEngine<>(source, resultParser, requestedFormat);
	}
	
	@Override
	public T run(ISingleResultQuery query) {
		Iterator<T> resIterator = this.queryEngine.run(query).iterator();
		
		return resIterator.hasNext() ? resIterator.next() : null;
	}

}
