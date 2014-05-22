package it.camera.hackathon.datasource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StringStreamReceiver implements IStreamReceiver<String>{

	@Override
	public String receive(InputStream is) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader read = new BufferedReader(new InputStreamReader(is));
		
		String line = read.readLine();
		
		while(line != null) {
			sb.append(line + "\n");
			line = read.readLine();
		}
		
		return sb.toString();
	}

}
