package it.camera.hackathon.datasource.sparql.test;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
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
	
	private static final int LIMIT = 15;
	
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
		System.out.println("STARTING TEST");
		
		long startTime = System.currentTimeMillis();
		Set<Entry<Atto, String>> res = lcp.getData(new LimitedQueryConfiguration(LIMIT));
		
		System.out.println();
		
		int i = 0;
		for(Entry<Atto, String> e : res) {
			System.out.println("Received: " + e.getKey() + " - Content: \"" + e.getValue().substring(0, 50) + "[...]\"");
			i++;
		}
		
		System.out.println("Received " + i + " documents");
		assertEquals(LIMIT, i);
		long endTime = System.currentTimeMillis();
		
		long elapsedTime = endTime - startTime; // msec
		
		Date d = new Date(elapsedTime);
		
		System.out.println("Finisced after " + new SimpleDateFormat("m'min' s'sec' SS").format(d) + "msec");
	}

}
