package it.camera.hackathon.parsing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StringReceiver implements IStreamParser<String> {

	@Override
	public String parse(InputStream is) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(is));
		
		String line = read.readLine();
		String html = "";
		
		while(line != null) {
			html += line + "\n";
			line = read.readLine();
		}
		
		return html;
	}

}
