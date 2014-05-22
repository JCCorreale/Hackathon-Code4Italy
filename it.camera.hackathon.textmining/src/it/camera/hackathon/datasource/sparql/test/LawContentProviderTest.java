package it.camera.hackathon.datasource.sparql.test;

import static org.junit.Assert.*;

import java.util.Map.Entry;
import java.util.Set;

import it.camera.hackathon.Atto;
import it.camera.hackathon.datasource.remote.HttpGetDataSource;
import it.camera.hackathon.datasource.sparql.LawContentProvider;
import it.camera.hackathon.datasource.sparql.VirtuosoRawResultQueryEngine;
import it.camera.hackathon.datasource.sparql.query.LimitedQuery.LimitedQueryConfiguration;
import it.camera.hackathon.parsing.StringReceiver;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LawContentProviderTest {
	
	private static final int LIMIT = 5;
	
	public static VirtuosoRawResultQueryEngine engine;
	public static LawContentProvider lcp;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		HttpGetDataSource<String> source = new HttpGetDataSource<String>("http://dati.camera.it/sparql", new StringReceiver());
		engine = new VirtuosoRawResultQueryEngine(source, "text/csv");
	}

	@Before
	public void setUp() throws Exception {
		lcp = new LawContentProvider(engine);
	}

	@Test
	public void test() {
		Set<Entry<Atto, String>> res = lcp.getData(new LimitedQueryConfiguration(LIMIT));
		
		int i = 0;
		for(Entry<Atto, String> e : res) {
			System.out.println("Received: " + e.getKey() + " - Content: \"" + e.getValue().substring(0, 50) + "[...]\"");
			i++;
		}
		
		assertEquals(LIMIT, i);
	}

}
