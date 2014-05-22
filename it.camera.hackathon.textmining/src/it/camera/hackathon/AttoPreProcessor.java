package it.camera.hackathon;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map.Entry;

import it.camera.hackathon.datasource.IDataProvider;
import it.camera.hackathon.datasource.remote.HttpGetDataSource;
import it.camera.hackathon.datasource.remote.HttpGetDataSource.HttpGetRequestConfiguration;
import it.camera.hackathon.datasource.sparql.VirtuosoSingleResultDataProvider;
import it.camera.hackathon.datasource.sparql.query.ActContentByDateQuery;
import it.camera.hackathon.datasource.sparql.query.ActLastRevisionDateQuery;
import it.camera.hackathon.datasource.sparql.query.Utils;
import it.camera.hackathon.parsing.LineValueHtmlParser;
import it.camera.hackathon.parsing.StringReceiver;
import it.camera.hackathon.textmining.HtmlRemover;
import it.camera.hackathon.textmining.clustering.IDocument;

public class AttoPreProcessor implements IDataProvider<Entry<Atto, IDocument>, Atto> {

	@Override
	public Entry<Atto, IDocument> getData(Atto atto) throws IllegalArgumentException {
		// ottieni data ultima modifica
		VirtuosoSingleResultDataProvider dp = new VirtuosoSingleResultDataProvider("http://dati.camera.it/sparql", new LineValueHtmlParser(), "text/html");
		String dataLastEdit = dp.getData(new ActLastRevisionDateQuery(atto.getIRI()));
		
		// ottieni url contenuto atto all'ultima modifica
		dp = new VirtuosoSingleResultDataProvider("http://dati.camera.it/sparql");
		String contentUrl = Utils.removeQuotesFromIRI(dp.getData(new ActContentByDateQuery(atto.getIRI(), dataLastEdit)));
		
		// ottieni contentuto individuato da url
		HttpGetRequestConfiguration req = new HttpGetRequestConfiguration(new HashMap<String, String>(), new HashMap<String, String>());
		String content = new HttpGetDataSource<>(contentUrl, new StringReceiver()).getData(req);
		
		// rimozione HTML
		content = HtmlRemover.text(content);
		
		// preprocessing contenuto
		IDocument document = new DataPreprocessor().getData(content);
		
		// definizione coppia atto - document
		return new AbstractMap.SimpleEntry<Atto, IDocument>(atto, document);
	}
	
	
}
