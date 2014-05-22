package it.camera.hackathon.datasource.sparql;

import it.camera.hackathon.datasource.remote.HttpGetDataSource;
import it.camera.hackathon.parsing.IParser;

public class VirtuosoQueryEngine<T> implements IQueryEngine<T> {

	private IParser<String, Iterable<T>> resultParser;
	
	private VirtuosoRawResultQueryEngine rawQueryEngine;
	
	public IParser<String, Iterable<T>> getResultParser() {
		return resultParser;
	}
	
	public VirtuosoRawResultQueryEngine getRawQueryEngine() {
		return rawQueryEngine;
	}
	
	public VirtuosoQueryEngine(HttpGetDataSource<String> source, IParser<String, Iterable<T>> resultParser, String requestedFormat) {
		this(new VirtuosoRawResultQueryEngine(source, requestedFormat), resultParser);
	}
	
	public VirtuosoQueryEngine(VirtuosoRawResultQueryEngine engine, IParser<String, Iterable<T>> resultParser) {
		if(engine == null)
			throw new IllegalArgumentException();
		if(resultParser == null)
			throw new IllegalArgumentException();
		
		this.rawQueryEngine = engine;
	}

	@Override
	public Iterable<T> run(IQuery query) {		
		return resultParser.parse(this.rawQueryEngine.run(query));
	}

}
