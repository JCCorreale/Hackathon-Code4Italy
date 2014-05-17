package it.camera.hackathon.textmining;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class HttpDataSource implements IStringDataSource {
	protected HttpURLConnection connection = null;
	
	public HttpDataSource(HttpURLConnection target) throws IOException {
		connection = target;
	}
	
	protected void setupConnection() {
		
	}
	
	protected void performRequest() {
		
	}

	@Override
	public String getData() {
		try {
			setupConnection();
			performRequest();
			
			BufferedReader read = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			String line = read.readLine();
			String html = "";
			
			while(line != null) {
				html += line;
				line = read.readLine();
			}
			
			return html;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
