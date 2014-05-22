package it.camera.hackathon.parsing.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

import it.camera.hackathon.parsing.CsvParser;
import it.camera.hackathon.utils.FileUtils;

import org.junit.Test;

public class CsvParserTest {
	public static final String[] NAMES = {"Thierry", "Jean Claude", "Alessandro"};
	public static final String[] SURNAMES = {"Spetebroot", "Correale", "Colace"};
	
	public static CsvParser parser = new CsvParser();

	@Test
	public void testParse() throws IOException {		
		String input = FileUtils.readFile("src/it/camera/hackathon/parsing/tests/csvParserTest.csv", Charset.forName("UTF-8"));
		
		Iterable<Map<String, String>> res = parser.parse(input);
		
		int i = 0;
		for(Map<String, String> map : res) {
			System.out.println("ROW " + i);
			System.out.println("    name:\t" + map.get("nome"));
			assertEquals(NAMES[i], map.get("nome"));
			System.out.println("    surname:\t" + map.get("cognome"));
			assertEquals(SURNAMES[i], map.get("cognome"));
			i++;
		}
		
		assertEquals(3, i);
	}

}
