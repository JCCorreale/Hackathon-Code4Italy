package it.camera.hackathon.textmining.sparql;

import it.camera.hackathon.datasource.remote.HttpDataSource;

public class VirtuosoSparqlDataProvider<T> implements ISparqlDataProvider<T> {

	private HttpDataSource<T> httpSource;
	
	@Override
	public T getData(IQuery query) {
		return null;
	}
}
