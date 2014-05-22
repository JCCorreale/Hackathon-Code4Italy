package it.camera.hackathon.datasource.remote;

import it.camera.hackathon.parsing.IStreamParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class HttpGetDataSource<T> extends HttpDataSource<T, HttpGetDataSource.HttpGetRequestConfiguration> {

	IStreamParser<T> parser;
	String baseUrl;
	
	public HttpGetDataSource(String baseUrl, IStreamParser<T> parser) {
		if(baseUrl == null)
			throw new IllegalArgumentException();
		
		this.baseUrl = baseUrl;
		this.parser = parser;
	}
	
	@Override
	protected HttpURLConnection getConnection(HttpGetRequestConfiguration conf) {
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(baseUrl + HttpDataSourceUtils.getGetParamters(conf.getParameters())).openConnection();
			conn.setRequestMethod("GET");
			applyHeader(conn, conf);
			return conn;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected T parseResponse(InputStream is) {
		try {
			return this.parser.parse(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void applyHeader(HttpURLConnection conn, HttpGetRequestConfiguration conf) {
		for(Entry<String, String> e : conf.getHeaderConfiguration().entrySet()) {
			conn.setRequestProperty(e.getKey(), e.getValue());
		}
	}
	
	public static class HttpGetRequestConfiguration {

		public static HttpGetRequestConfiguration getDefault() {
			return new HttpGetRequestConfiguration(new HashMap<String, String>(), new HashMap<String, String>());
		}
		
		private Map<String, String> headerConf;
		private Map<String, String> params;

		public Map<String, String> getHeaderConfiguration() {
			return headerConf;
		}
		
		public Map<String, String> getParameters() {
			return params;
		}
		
//		public Set<Entry<String, String>> getParameters() {
//			return this.params.entrySet();
//		}
//		
//		public Set<Entry<String, String>> getHeaderConfiguration() {
//			return this.headerConf.entrySet();
//		}
		
		public HttpGetRequestConfiguration(Map<String, String> params, Map<String, String> headerConf) {
			if(params == null || headerConf == null)
				throw new IllegalArgumentException();
			
			this.params = params;
			this.headerConf = headerConf;
		}

	}	
}
