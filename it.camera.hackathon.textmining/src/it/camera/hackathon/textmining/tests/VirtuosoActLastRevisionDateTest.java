package it.camera.hackathon.textmining.tests;

import static org.junit.Assert.*;
import it.camera.hackathon.datasource.sparql.VirtuosoSingleResultDataProvider;
import it.camera.hackathon.datasource.sparql.query.ActLastRevisionDateQuery;
import it.camera.hackathon.datasource.sparql.query.Utils;
import it.camera.hackathon.parsing.LineValueHtmlParser;

import org.junit.Test;

public class VirtuosoActLastRevisionDateTest {

	@Test
	public void test() {
		VirtuosoSingleResultDataProvider dp = new VirtuosoSingleResultDataProvider("http://dati.camera.it/sparql", new LineValueHtmlParser(), "text/html");
		
		assertEquals("20080429", Utils.removeQuotesFromIRI(dp.getData(new ActLastRevisionDateQuery("http://dati.camera.it/ocd/attocamera.rdf/ac16_44"))));	
	}

}
