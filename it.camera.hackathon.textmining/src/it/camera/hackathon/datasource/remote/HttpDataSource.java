package it.camera.hackathon.datasource.remote;

import it.camera.hackathon.datasource.IDataProvider;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

public abstract class HttpDataSource<T, C> implements IDataProvider<T, C> {
	
	protected abstract HttpURLConnection getConnection(C configuration);
	protected abstract T parseResponse(InputStream is);
	
	@Override
	public T getData(C configuration) {
		try {
			return parseResponse(this.getConnection(configuration).getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
