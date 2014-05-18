package it.camera.hackathon.datasource.sparql;

import java.util.HashMap;
import java.util.Map;

import it.camera.hackathon.datasource.remote.HttpGetDataSource;
import it.camera.hackathon.datasource.remote.HttpGetDataSource.HttpGetRequestConfiguration;

public class VirtuosoSparqlDataProvider implements ISparqlDataProvider<String> {

	// Sorgente su cui effettuare le query
	private HttpGetDataSource<String> source;
	
	public VirtuosoSparqlDataProvider(HttpGetDataSource<String> source) {
		this.source = source;
	}
	
	@Override
	public String getData(IQuery query) {
		return this.source.getData(buildConfiguration(query));
	}
	
	private HttpGetRequestConfiguration<String> buildConfiguration(IQuery query) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("format", "text/csv");
		param.put("timeout", "0");
		param.put("query", query.toString());
		param.put("default-graph-uri", "");
		param.put("debug", "on");
		
		Map<String, String> header = new HashMap<String, String>();
		
		return new HttpGetRequestConfiguration<>(param, header);
	}
}
