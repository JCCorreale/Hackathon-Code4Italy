package it.camera.hackathon.datasource.sparql;

import java.util.HashMap;
import java.util.Map;

import it.camera.hackathon.datasource.IDataSource;
import it.camera.hackathon.datasource.remote.HttpGetDataSource;
import it.camera.hackathon.datasource.remote.HttpGetDataSource.HttpGetRequestConfiguration;
import it.camera.hackathon.datasource.sparql.query.LawsQuery;
import it.camera.hackathon.parsing.LineValueParser;

public class VirtuosoActSource implements IDataSource<String[]> {
	
	// Sorgente su cui effettuare le query
	private HttpGetDataSource<String[]> source;
	
	public VirtuosoActSource(String url) {
		this.source = new HttpGetDataSource<String[]>(url, new LineValueParser());
	}
	
	@Override
	public String[] getData() {
		return this.source.getData(buildConfiguration(new LawsQuery()));
	}
	
	private HttpGetRequestConfiguration<String[]> buildConfiguration(IQuery query) {
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
