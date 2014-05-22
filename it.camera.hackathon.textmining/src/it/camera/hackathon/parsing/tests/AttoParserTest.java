package it.camera.hackathon.parsing.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.Charset;
import it.camera.hackathon.Atto;
import it.camera.hackathon.parsing.CsvMapToAttoParser;
import it.camera.hackathon.parsing.CsvToTypeParser;
import it.camera.hackathon.utils.FileUtils;

import org.junit.Test;

public class AttoParserTest {
	public static final String[] HEADER = { "iriAtto" };
	public static final String[] VALUES = { "atto1", "atto2", "atto3" };
	
	public static CsvToTypeParser<Atto> parser = new CsvToTypeParser<>(new CsvMapToAttoParser());

	@Test
	public void testParse() throws IOException {		
		String input = FileUtils.readFile("src/it/camera/hackathon/parsing/tests/atti.csv", Charset.forName("UTF-8"));
		
		Iterable<Atto> res = parser.parse(input);
		
		int i = 0;
		for(Atto a : res) {
			System.out.println("iri: " + a.getIRI());
			assertEquals(VALUES[i], a.getIRI());
			i++;
		}
		
		assertEquals(3, i);
	}

}
