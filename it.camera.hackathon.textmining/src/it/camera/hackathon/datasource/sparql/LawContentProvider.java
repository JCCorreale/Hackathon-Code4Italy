package it.camera.hackathon.datasource.sparql;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import it.camera.hackathon.Atto;
import it.camera.hackathon.datasource.IDataProvider;
import it.camera.hackathon.datasource.remote.HttpGetDataSource;
import it.camera.hackathon.datasource.remote.HttpGetDataSource.HttpGetRequestConfiguration;
import it.camera.hackathon.datasource.sparql.query.ActContentByDateQuery;
import it.camera.hackathon.datasource.sparql.query.ActLastRevisionDateQuery;
import it.camera.hackathon.datasource.sparql.query.LawsQuery;
import it.camera.hackathon.datasource.sparql.query.LimitedQuery;
import it.camera.hackathon.datasource.sparql.query.LimitedQuery.LimitedQueryConfiguration;
import it.camera.hackathon.parsing.CsvMapToAttoParser;
import it.camera.hackathon.parsing.CsvToTypeParser;
import it.camera.hackathon.parsing.IParser;
import it.camera.hackathon.textmining.HtmlRemover;

public class LawContentProvider implements IDataProvider<Set<Entry<Atto, String>>, LimitedQueryConfiguration> {

	private VirtuosoRawResultQueryEngine engine;
	
	public LawContentProvider(VirtuosoRawResultQueryEngine engine) {
		if(engine == null)
			throw new IllegalArgumentException();
		
		this.engine = engine;
	}
	
	@Override
	public Set<Entry<Atto, String>> getData(LimitedQueryConfiguration cfg) throws IllegalArgumentException {
		IParser<String, Iterable<Atto>> attiParser = new CsvToTypeParser<>(new CsvMapToAttoParser());
		Iterable<Atto> atti = attiParser.parse(engine.run(new LimitedQuery(new LawsQuery(), cfg)));
		
		Map<Atto, String> res = new HashMap<>();

		engine.setRequestedFormat("text/html");
		for(Atto atto : atti) {
			// Request last edit date
			IQuery q = new ActLastRevisionDateQuery(atto.getIRI());
			String date = HtmlRemover.text(engine.run(q)).split(" ")[1];
			
			q = new ActContentByDateQuery(atto.getIRI(), date);
			String contentUrl = HtmlRemover.text(engine.run(q)).split(" ")[1];
			
			HttpGetDataSource<String> ds = new HttpGetDataSource<String>(contentUrl, new HtmlRemoverFilterReceiver());
			String content = ds.getData(HttpGetRequestConfiguration.getDefault());
			
			res.put(atto, content);
		}
		engine.setRequestedFormat("text/csv");
		
		return res.entrySet();
	}

}
