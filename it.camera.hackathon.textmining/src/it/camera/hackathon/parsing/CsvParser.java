package it.camera.hackathon.parsing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CsvParser implements IRowsWithHeaderParser {

	private SimpleSingleRowCsvParser headerParser;
	private SingleRowCsvParser rowParser;
	private boolean trim;
	
	public CsvParser() {
		this(true);
	}
	
	public CsvParser(boolean b) {
		this.trim = b;
		this.headerParser = new SimpleSingleRowCsvParser(this.trim);
	}

	@Override
	public IParser<String, Map<String, String>> getRowParser() {
		return this.rowParser;
	}

	@Override
	public Iterable<Map<String, String>> parse(String input) {
		StringRowsParser<String> splitParser = new StringRowsParser<>(new IdentityParser<String>());
		Iterable<String> rows = splitParser.parse(input);
		
		Iterator<String> i = rows.iterator();
		
		String[] header = headerParser.parse(i.next());
		rowParser = new SingleRowCsvParser(header, trim);
		
		List<Map<String, String>> res = new ArrayList<>();
		
		String row;
		while(i.hasNext()) {
			row = i.next();
			if(!row.equals(""))
				res.add(rowParser.parse(row));
		}
		return res;
	}
}
