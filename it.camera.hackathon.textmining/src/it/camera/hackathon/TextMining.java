package it.camera.hackathon;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import it.camera.hackathon.datasource.remote.HttpGetDataSource;
import it.camera.hackathon.datasource.sparql.LawContentProvider;
import it.camera.hackathon.datasource.sparql.VirtuosoRawResultQueryEngine;
import it.camera.hackathon.datasource.sparql.query.LimitedQuery.LimitedQueryConfiguration;
import it.camera.hackathon.parsing.StringReceiver;
import it.camera.hackathon.textmining.HtmlRemover;
import it.camera.hackathon.textmining.IWordCountResult;
import it.camera.hackathon.textmining.TopWordsCountAnalyzer;
import it.camera.hackathon.textmining.clustering.AgglomerativeDocumentClusterer;
import it.camera.hackathon.textmining.clustering.Dendrogram;
import it.camera.hackathon.textmining.clustering.DocumentCollection;
import it.camera.hackathon.textmining.clustering.IClustering;
import it.camera.hackathon.textmining.clustering.IDissimilarityStrategy;
import it.camera.hackathon.textmining.clustering.IDocument;
import it.camera.hackathon.textmining.clustering.IDocumentCollection;
import it.camera.hackathon.textmining.clustering.IProximityStrategy;
import it.camera.hackathon.textmining.clustering.ITerm;
import it.camera.hackathon.textmining.clustering.SingleLinkProximityStrategy;
import it.camera.hackathon.textmining.clustering.TFIDFCosineDissimilarityStrategy;
import it.camera.hackathon.utils.MapUtils;

public class TextMining extends ITextMining
{
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args)
	{
//		String[] filenames = new HTMLDocumentFactory().getFilePaths();
		List<Entry<Atto, IDocument>> documents = new ArrayList<Entry<Atto, IDocument>>();
		
		HttpGetDataSource<String> source = new HttpGetDataSource<String>("http://dati.camera.it/sparql", new StringReceiver());
		VirtuosoRawResultQueryEngine engine = new VirtuosoRawResultQueryEngine(source, "text/csv");
		LawContentProvider provider = new LawContentProvider(engine);
		Set<Entry<Atto, String>> attiContent = provider.getData(new LimitedQueryConfiguration(downloadLimit));
		
		for (Entry<Atto, String> attoContent : attiContent)
		{
//			Calendar cal = Calendar.getInstance();
//			cal.set(2013, Calendar.JANUARY, 1);
//			if (attoContent.getKey().getRevision().before(cal.getTime()))
//				continue;
			
			System.out.println("************************************************");
//			System.out.println("Document " +  filename);
			
			String label = attoContent.getKey().getIRI();
			System.out.println("Atto: " + label);
			
			// gets the input
//			IDataSource<String> source = new TextFileDataSource(filename);
//			String html = source.getData();
			
			String html = attoContent.getValue();
			
			// removes HTML
			System.out.println("Removing HTML tags...");
			String plainText = HtmlRemover.text(html);
			
			// counts the occurence
			System.out.println("Counting word occurences (may take a while!)... [" + plainText.length() + " characters]");
			TextMiningWordCounter counter = buildWordCounter();
			IWordCountResult result = counter.parse(plainText);
			
			// retrieves the top word
			System.out.println("Retrieving the first " + topWordsCount + " top words...");
			TopWordsCountAnalyzer analyzer = new TopWordsCountAnalyzer();
			List<Entry<String, Integer>> topWords = analyzer.getSortedWords(topWordsCount, result);
	
			// prints synonyms for the top words
	//		SynonimScraper synonimScraper = new SynonimScraper();
	//		for (Entry<String, Integer> entry : topWords)
	//		{
	//			System.out.println(entry.getKey() + " " + entry.getValue() + synonimScraper.FindSynonims(entry.getKey()));
	//		}
			
			ITermsDisambiguator disambiguator = getTermsDisambiguator();
			System.out.println("Performing term disambiguation");
			topWords = MapUtils.mapToEntryList(disambiguator.getDisambiguatedTerms(topWords));
			
			System.out.println("Creating document model");
			// creates an IDocument instance from the retrieved data
			IDocument document = buildDocument(topWords, counter.getAcceptedWordCount());
			documents.add(new AbstractMap.SimpleEntry(attoContent.getKey(), document));
			
			System.out.println("Document " + label + " done.\n\n");
			
			// prints some stat about the document
//			System.out.println("\n\nFrequency by term:\n");
//			Utils.printMap(document.getFrequencyByTerm());
//			System.out.println("\n\nWeighted frequency by term:\n");
//			Utils.printMap(document.getWeightedFrequencyByTerm());
		}
		
		AttoDocumentAnalyser analyser = getDocumentsAnalyser();
		
		Map<Atto, List<ITerm>> result = analyser.getData(documents);
		
		// prints top terms for each document
//		for (Entry<Atto, List<ITerm>> entry : result.entrySet())
//		{
//			StringBuilder terms = new StringBuilder();
//			for (ITerm term : entry.getValue())
//			{
//				terms.append("[" + term + "] ");
//			}
//			
//			System.out.println(entry.getKey().toString() + " " + terms.toString());
//		}
		
		JSONAttiTopWordsPersister.saveAsSingleFile(result, "output/json/atti-terms.json");
		
		if (doClustering)
		{
			/* CLUSTERING */
			
			System.out.println("\n\n*******************\n\nCLUSTERING\n\n");
			
			IDocumentCollection docsCollection;
			List<IDocument> docsList = new ArrayList<IDocument>();
			for (Entry<Atto, IDocument> entry : documents)
			{
				docsList.add(entry.getValue());
			}
			docsCollection = new DocumentCollection(docsList.toArray(new IDocument[0]));
			
			IDissimilarityStrategy distanceStrategy = new TFIDFCosineDissimilarityStrategy(docsCollection);
			IProximityStrategy proximityStrategy = new SingleLinkProximityStrategy(distanceStrategy);
			
			AgglomerativeDocumentClusterer clusterer = new AgglomerativeDocumentClusterer(proximityStrategy);
			
			Dendrogram dendrogram = clusterer.getClusteringDendrogram(docsCollection);

			IClustering clustering = dendrogram.getClustering(dendrogram.getHeight() / 2); // TODO Height
			
			ClusteringAnalyser clusterAnalyser = new ClusteringAnalyser();
			Set<ClusterDescriptor> descriptors = clusterAnalyser.getClusterDescriptors(clustering, MapUtils.getValueKeyMap(MapUtils.entryListToMap(documents)));
			
			// Saves clusters descriptors...
			JSONClusterDescriptorsPersister.save(descriptors, "output/json/clusters");

			System.out.println("Clustering completed.");
		}
	}
}