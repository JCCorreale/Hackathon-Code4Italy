package it.camera.hackathon.datasource.remote;

import it.camera.hackathon.parsing.IParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.Map.Entry;

public class HttpPostDataSource<T> extends HttpDataSource<T, HttpPostDataSource.HttpPostDataSourceConfiguration<T>> {

	private IParser<T> parser;
	private HttpURLConnection conn;
	
	public void HttpPostDataSourceConfiguration(HttpURLConnection conn, IParser<T> parser) {
		this.parser = parser;
		this.conn = conn;
	}
	
	@Override
	protected HttpURLConnection getConnection(HttpPostDataSourceConfiguration<T> configuration) {
		applyHeader(conn, configuration);
		
		return this.conn;
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
	
	private void applyHeader(HttpURLConnection conn, HttpPostDataSourceConfiguration<T> conf) {
		for(Entry<String, String> e : conf.getHeaderConfiguration().entrySet()) {
			conn.setRequestProperty(e.getKey(), e.getValue());
		}
	}
	
	public static class HttpPostDataSourceConfiguration<T> {

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
		
		public HttpPostDataSourceConfiguration(Map<String, String> params, Map<String, String> headerConf) {
			if(params == null || headerConf == null)
				throw new IllegalArgumentException();
			
			this.params = params;
			this.headerConf = headerConf;
		}
	}
	
}
