package it.camera.hackathon.parsing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CsvToTypeParser<T> implements IParser<String, Iterable<T>> {

	private CsvParser csvParser = new CsvParser();
	private IParser<Map<String, String>, T> csvMapParser;
	
	public CsvToTypeParser(IParser<Map<String, String>, T> csvMapParser) {
		if(csvMapParser == null)
			throw new IllegalArgumentException();
		
		this.csvMapParser = csvMapParser;
	}
	
	@Override
	public Iterable<T> parse(String input) {
		List<T> res = new ArrayList<>();
		
		Iterable<Map<String, String>> csvMap = csvParser.parse(input);
		
		for(Map<String, String> e : csvMap)
			res.add(csvMapParser.parse(e));
		
		return res;
	}

}
