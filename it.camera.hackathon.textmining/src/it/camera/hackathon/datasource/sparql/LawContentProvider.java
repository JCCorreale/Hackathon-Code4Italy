package it.camera.hackathon.datasource.sparql;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		
		int i = 0;
		Map<Atto, String> res = new HashMap<>();

		engine.setRequestedFormat("text/html");
		for(Atto atto : atti) {
			System.out.println("Receiving Law(" + i++ + "): " + atto.getIRI());
			
			// Request last edit date
			IQuery q = new ActLastRevisionDateQuery(atto.getIRI());
			String date = HtmlRemover.text(engine.run(q)).split(" ")[1];
			setActDate(atto, date);
			System.out.println("  Final revision: " + new SimpleDateFormat("dd/MM/YYYY").format(atto.getRevision()));
			
			// Request act content location
			q = new ActContentByDateQuery(atto.getIRI(), date);
			String contentUrl = HtmlRemover.text(engine.run(q)).split(" ")[1];
			atto.setContentUrl(contentUrl);
			System.out.println("  Content location: " + atto.getContentUrl());
			
			// Download act content
			System.out.println("  Content: downloading");
			HttpGetDataSource<String> ds = new HttpGetDataSource<String>(contentUrl, new HtmlRemoverFilterReceiver());
			String content = ds.getData(HttpGetRequestConfiguration.getDefault());
			System.out.println("  Content: download ended - received: \"" + content.substring(0, 30) + " [...]\"");
			
			res.put(atto, content);
		}
		engine.setRequestedFormat("text/csv");
		
		return res.entrySet();
	}
	
	private void setActDate(Atto a, String d) {
		try {
			// The correct string is "yyyyMMdd". Do not change it. No, it's not equal to "YYYYMMdd".
			DateFormat df = new SimpleDateFormat("\"yyyyMMdd\"");
			a.setRevision(df.parse(d));			
		} catch (ParseException e) {
			// Ignore error
			System.err.println("Could not save act revision date - Illegal format exception");
			e.printStackTrace();
		}
	}

}
