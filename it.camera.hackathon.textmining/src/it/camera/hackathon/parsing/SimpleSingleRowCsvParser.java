package it.camera.hackathon.parsing;

public class SimpleSingleRowCsvParser implements IParser<String, String[]> {

	private boolean trim = true;
	
	public SimpleSingleRowCsvParser(boolean trim) {
		this.trim = trim;
	}
	
	@Override
	public String[] parse(String input) {
		String[] res = input.split(",");
		if(trim)
			for(int i = 0; i < res.length; i++)
				res[i] = res[i].trim();
		
		return res;
	}
	
}
