package it.camera.hackathon.parsing;

import it.camera.hackathon.textmining.HtmlRemover;

import java.io.IOException;
import java.io.InputStream;

public class LineValueHtmlParser implements IStreamParser<String[]> {

	@Override
	public String[] parse(InputStream is) throws IOException {
		String s = new StringReceiver().parse(is);
		
		return HtmlRemover.text(s).split(" ");
	}
	
}
