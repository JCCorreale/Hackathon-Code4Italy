package it.camera.hackathon.parsing;

import java.util.HashMap;
import java.util.Map;

public class SingleRowCsvParser implements IParser<String, Map<String, String>> {

	private String[] header;
	private SimpleSingleRowCsvParser rowParser;
	
	public SingleRowCsvParser(String[] header) {
		this(header, true);
	}
	
	public SingleRowCsvParser(String[] header, boolean trim) {
		if(header == null || header.length == 0)
			throw new IllegalArgumentException();
		
		this.header = header;
		this.rowParser = new SimpleSingleRowCsvParser(trim);
	}

	@Override
	public Map<String, String> parse(String input) {
		String[] values = rowParser.parse(input);
		Map<String, String> res = new HashMap<String, String>();
		
		if(values.length != header.length)
			throw new IllegalStateException();
		
		for(int i = 0; i < header.length; i++) {
			res.put(header[i], values[i]);
		}
		
		return res;
	}

}
