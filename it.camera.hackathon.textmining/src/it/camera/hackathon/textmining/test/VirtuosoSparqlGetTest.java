package it.camera.hackathon.textmining.test;

import static org.junit.Assert.*;
import it.camera.hackathon.datasource.remote.HttpGetDataSource;
import it.camera.hackathon.datasource.sparql.VirtuosoActSource;
import it.camera.hackathon.datasource.sparql.VirtuosoSparqlDataProvider;
import it.camera.hackathon.datasource.sparql.query.LawsQuery;
import it.camera.hackathon.parsing.LineValueParser;
import it.camera.hackathon.parsing.StringParser;

import org.junit.Test;

public class VirtuosoSparqlGetTest {

	@Test
	public void test() {		
		HttpGetDataSource<String> ds = new HttpGetDataSource<String>("http://dati.camera.it/sparql", new StringParser());
		VirtuosoSparqlDataProvider dp = new VirtuosoSparqlDataProvider(ds);
		
		System.out.println("Received: '" + dp.getData(new LawsQuery()) + "'");
	}	

	@Test
	public void test2() {
		VirtuosoActSource dp = new VirtuosoActSource("http://dati.camera.it/sparql");
		
		System.out.println("Received: '" + dp.getData().length + "'");
	}

}
