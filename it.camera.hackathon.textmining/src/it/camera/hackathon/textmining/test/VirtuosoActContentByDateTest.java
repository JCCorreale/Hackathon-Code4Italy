package it.camera.hackathon.textmining.test;

import static org.junit.Assert.*;
import it.camera.hackathon.datasource.sparql.VirtuosoSingleResultDataProvider;
import it.camera.hackathon.datasource.sparql.query.ActContentByDateQuery;
import it.camera.hackathon.datasource.sparql.query.Utils;
import org.junit.Test;

public class VirtuosoActContentByDateTest {

	@Test
	public void test() {
		VirtuosoSingleResultDataProvider dp = new VirtuosoSingleResultDataProvider("http://dati.camera.it/sparql");
		
		assertEquals("http://documenti.camera.it/apps/commonServices/getDocumento.ashx?sezione=lavori&tipoDoc=testo_pdl&idlegislatura=16&codice=16PDL0002960", Utils.removeQuotesFromIRI(dp.getData(new ActContentByDateQuery("http://dati.camera.it/ocd/attocamera.rdf/ac16_44", "\"20080429\""))));	
	}

}
