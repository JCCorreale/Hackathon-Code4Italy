package it.camera.hackathon.parsing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LineValueParser implements IParser<String[]> {

	@Override
	public String[] parse(InputStream is) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(is));
		List<String> res = new ArrayList<>();
		
		String line = read.readLine();
		while(line != null) {
			System.out.println(line);
			res.add(line);
			line = read.readLine();
		}
		
		return res.toArray(new String[0]);
	}

}
