package it.camera.hackathon.textmining.test;

import static org.junit.Assert.*;
import it.camera.hackathon.datasource.remote.HttpGetDataSource;
import it.camera.hackathon.datasource.sparql.VirtuosoSingleResultDataProvider;
import it.camera.hackathon.datasource.sparql.query.ActLastRevisionDateQuery;
import it.camera.hackathon.parsing.StringParser;

import org.junit.Test;

public class VirtuosoActLastRevisionDateTest {

	@Test
	public void test() {
		VirtuosoSingleResultDataProvider dp = new VirtuosoSingleResultDataProvider("http://dati.camera.it/sparql");
		
		assertEquals("20080429", dp.getData(new ActLastRevisionDateQuery("http://dati.camera.it/ocd/attocamera.rdf/ac16_44")));	
	}

}
