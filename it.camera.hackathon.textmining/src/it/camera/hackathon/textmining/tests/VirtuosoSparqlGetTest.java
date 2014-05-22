package it.camera.hackathon.textmining.tests;

import it.camera.hackathon.datasource.sparql.VirtuosoLawSource;
import it.camera.hackathon.datasource.sparql.VirtuosoSingleResultDataProvider;
import it.camera.hackathon.datasource.sparql.query.LawsQuery;
import it.camera.hackathon.datasource.sparql.query.LimitedQuery.LimitedQueryConfiguration;

import org.junit.Test;

public class VirtuosoSparqlGetTest {

	@Test
	public void test() {
		System.out.println("TEST VirtuosoSingleResultDataProvider");
		VirtuosoSingleResultDataProvider dp = new VirtuosoSingleResultDataProvider("http://dati.camera.it/sparql");
		
		System.out.println("Received: '" + dp.getData(new LawsQuery()) + "'");
	}	

	@Test
	public void test2() {
		System.out.println("TEST VirtuosoLawSource");
		VirtuosoLawSource dp = new VirtuosoLawSource("http://dati.camera.it/sparql");
		
		System.out.println("Received: '" + dp.getData(new LimitedQueryConfiguration(10)).length + "'");
	}

}
