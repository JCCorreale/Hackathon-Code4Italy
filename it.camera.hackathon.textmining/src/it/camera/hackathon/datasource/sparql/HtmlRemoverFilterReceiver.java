package it.camera.hackathon.datasource.sparql;

import java.io.IOException;
import java.io.InputStream;

import it.camera.hackathon.parsing.IStreamParser;
import it.camera.hackathon.parsing.StringReceiver;
import it.camera.hackathon.textmining.HtmlRemover;

public class HtmlRemoverFilterReceiver implements IStreamParser<String> {

	@Override
	public String parse(InputStream is) throws IOException {
		return HtmlRemover.text(new StringReceiver().parse(is));
	}

}
