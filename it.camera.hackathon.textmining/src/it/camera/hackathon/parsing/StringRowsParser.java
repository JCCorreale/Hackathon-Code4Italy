package it.camera.hackathon.parsing;

import java.util.ArrayList;
import java.util.List;

public class StringRowsParser<T> implements IRowsParser<String, T> {

	private IParser<String, T> rowParser;
	
	public StringRowsParser(IParser<String, T> rowParser) {
		if(rowParser == null)
			throw new IllegalArgumentException();
		
		this.rowParser = rowParser;
	}
	
	@Override
	public Iterable<T> parse(String input) {
		List<T> res = new ArrayList<T>();
		
		for(String row : input.split("\n"))
			res.add(this.rowParser.parse(row));
		
		return res;
	}

	@Override
	public IParser<String, T> getRowParser() {
		return this.rowParser;
	}


}
