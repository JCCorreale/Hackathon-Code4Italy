package it.camera.hackathon.textmining.test;

import it.camera.hackathon.datasource.sparql.VirtuosoLawSource;
import it.camera.hackathon.datasource.sparql.VirtuosoSingleResultDataProvider;
import it.camera.hackathon.datasource.sparql.query.LawsQuery;
import org.junit.Test;

public class VirtuosoSparqlGetTest {

	@Test
	public void test() {
		VirtuosoSingleResultDataProvider dp = new VirtuosoSingleResultDataProvider("http://dati.camera.it/sparql");
		
		System.out.println("Received: '" + dp.getData(new LawsQuery()) + "'");
	}	

	@Test
	public void test2() {
		VirtuosoLawSource dp = new VirtuosoLawSource("http://dati.camera.it/sparql");
		
		System.out.println("Received: '" + dp.getData().length + "'");
	}

}
