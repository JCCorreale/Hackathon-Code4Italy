package it.camera.hackathon.textmining;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class StringDataSourceFactory {
	// TODO
	public static HttpDataSource getHttpDataSource(URL url) throws IOException {
		return new HttpDataSource((HttpURLConnection) url.openConnection());
	}
}
