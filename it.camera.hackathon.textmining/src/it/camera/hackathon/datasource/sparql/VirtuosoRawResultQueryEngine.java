package it.camera.hackathon.datasource.sparql;

import it.camera.hackathon.datasource.remote.HttpGetDataSource;
import it.camera.hackathon.datasource.remote.HttpGetDataSource.HttpGetRequestConfiguration;
import java.util.HashMap;
import java.util.Map;

public class VirtuosoRawResultQueryEngine implements IRawResultQueryEngine<String> {
	private String requestedFormat;
	private HttpGetDataSource<String> source;

	public String getRequestedFormat() {
		return requestedFormat;
	}
	
	public void setRequestedFormat(String s) {
		this.requestedFormat = s;
	}

	public HttpGetDataSource<String> getSource() {
		return source;
	}
	
	public VirtuosoRawResultQueryEngine(HttpGetDataSource<String> source, String requestedFormat) {
		if(source == null)
			throw new IllegalArgumentException();
		if(requestedFormat == null)
			throw new IllegalArgumentException();
		
		this.requestedFormat = requestedFormat;
		this.source = source;
	}
	
	@Override
	public String run(IQuery query) {
		return this.source.getData(buildConfiguration(query));
	}
	
	private HttpGetRequestConfiguration buildConfiguration(IQuery query) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("format", this.requestedFormat);
		param.put("timeout", "0");
		param.put("query", query.toString());
		param.put("default-graph-uri", "");
		param.put("debug", "on");
		
		Map<String, String> header = new HashMap<String, String>();
		
		return new HttpGetRequestConfiguration(param, header);
	}

}
