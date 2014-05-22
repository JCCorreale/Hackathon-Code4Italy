package it.camera.hackathon.datasource.sparql.query;

import it.camera.hackathon.datasource.sparql.IQuery;
import it.camera.hackathon.datasource.sparql.IQueryEngine;

public class LazyQueryEngine<T> implements IQueryEngine<T> {

	private IQueryEngine<T> queryEngine;
	
	public LazyQueryEngine(IQueryEngine<T> queryEngine) {
		if(queryEngine == null)
			throw new IllegalArgumentException();
		
		this.queryEngine = queryEngine;
	}
	
	@Override
	public Iterable<T> run(IQuery query) {		
		return new IterableQueryResult<>(query, queryEngine);
	}

}
