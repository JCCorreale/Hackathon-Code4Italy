package it.camera.hackathon;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

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
import it.camera.hackathon.textmining.clustering.ICluster;
import it.camera.hackathon.textmining.clustering.IClustering;
import it.camera.hackathon.textmining.clustering.IDissimilarityStrategy;
import it.camera.hackathon.textmining.clustering.IDocument;
import it.camera.hackathon.textmining.clustering.IDocumentCollection;
import it.camera.hackathon.textmining.clustering.IProximityStrategy;
import it.camera.hackathon.textmining.clustering.ITerm;
import it.camera.hackathon.textmining.clustering.SingleLinkProximityStrategy;
import it.camera.hackathon.textmining.clustering.TFIDFCosineDissimilarityStrategy;
import it.camera.hackathon.utils.LogFileAndConsoleOutputStream;
import it.camera.hackathon.utils.MapUtils;

public class TextMining extends ITextMining
{
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) throws FileNotFoundException
	{
		// redirects stdout to file
		System.setOut(new PrintStream(new LogFileAndConsoleOutputStream("output/log.txt")));
		
//		String[] filenames = new HTMLDocumentFactory().getFilePaths();
		List<Entry<Atto, IDocument>> documents = new ArrayList<Entry<Atto, IDocument>>();
		
		HttpGetDataSource<String> source = new HttpGetDataSource<String>("http://dati.camera.it/sparql", new StringReceiver());
		VirtuosoRawResultQueryEngine engine = new VirtuosoRawResultQueryEngine(source, "text/csv");
		LawContentProvider provider = new LawContentProvider(engine);
		Set<Entry<Atto, String>> attiContent = provider.getData(new LimitedQueryConfiguration(downloadLimit));
		
		SortedSet<Atto> sortedActs = new TreeSet<Atto>(new Comparator<Atto>() {
			@Override
			public int compare(Atto arg0, Atto arg1) {
				if (arg0.equals(arg1))
					return 0;
				return arg0.getRevision().before(arg1.getRevision()) ? -1 : 1;
			}
		});
		
		
		for (Entry<Atto, String> attoContent : attiContent)
		{
//			Calendar cal = Calendar.getInstance();WW
//			cal.set(2013, Calendar.JANUARY, 1);
//			if (attoContent.getKey().getRevision().before(cal.getTime()))
//				continue;
			
			// skips acts with date before March 15 2013 (beginning of the XVII Italian legislature)
			Calendar actDate = Calendar.getInstance();
			Calendar minDate = Calendar.getInstance();
			actDate.setTime(attoContent.getKey().getRevision());
			minDate.set(2013, Calendar.MARCH, 15);
			if (actDate.before(minDate))
				continue;
			
			sortedActs.add(attoContent.getKey());
			
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
			
			// counts the occurrences
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
			
			// prints some stats about the document
//			System.out.println("\n\nFrequency by term:\n");
//			Utils.printMap(document.getFrequencyByTerm());
//			System.out.println("\n\nWeighted frequency by term:\n");
//			Utils.printMap(document.getWeightedFrequencyByTerm());
		}
		
		System.out.println("\n\n****************** Atti by date ******************");
		for (Atto atto : sortedActs)
			System.out.println(atto.getIRI() + " " + atto.getRevision());
		
		AttoDocumentAnalyser analyser = getDocumentsAnalyser();
		
		Map<Atto, List<ITerm>> topWordsResult = analyser.getData(documents);
		
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
		
		if (writeTopTerms)
		{
			JSONAttiTopWordsPersister.saveAsSingleFile(topWordsResult, "output/json/atti-terms.json");
		}
		
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

			IClustering clustering = dendrogram.getClustering(dendrogram.getHeight()); // TODO Tune height
			
			ClusteringAnalyser clusterAnalyser = new ClusteringAnalyser(maxClusterTerms);
			Set<ClusterDescriptor> descriptors = clusterAnalyser.getClusterDescriptors(clustering, MapUtils.getValueKeyMap(MapUtils.entryListToMap(documents)), docsCollection, topWordsResult);
			
			// Saves clusters descriptors...
			if (writeClustering)
				JSONClusterDescriptorsPersister.save(descriptors, "output/json/clusters");
			
			// Shows some info about the clustering
			int docsCount = 0;
			for (ICluster cluster : clustering)
				docsCount += cluster.getDocumentsCount();
			
			float docsCheckSum = 0;
			
			for (ClusterDescriptor descr : descriptors)
			{
				int clusterDocsCount = descr.cluster.getDocumentsCount();
				docsCheckSum += clusterDocsCount;
				
				System.out.println("Cluster [" + descr + "]");
				System.out.println("(" + clusterDocsCount + "/" + docsCount + " documents)");
				
				for (Atto atto : descr.atti)
				{
					System.out.println("\tAtto: " + atto.getIRI());
					System.out.println("\t\t" +  topWordsResult.get(atto));
				}
			}
			
			Map<Date, Integer> actsCountByDate = null;
			
			// prints the histogram of acts by period
			for (ClusterDescriptor descr : descriptors)
			{
				if (actsCountByDate == null)
				{
					// initializes the map
					actsCountByDate = new HashMap<Date, Integer>();
					for (Date date : descr.occurrences.keySet())
						actsCountByDate.put(date, 0);
				}
				
				for (Date descrDate : descr.occurrences.keySet())
				{
					// TODO Ottimizzare (per qualche motivo non funzionava usando la get...)
					for (Date mapDate : actsCountByDate.keySet())
					{
						if (descrDate.equals(mapDate))
						{
							// increases the counter
							actsCountByDate.put(mapDate, actsCountByDate.get(mapDate) + descr.occurrences.get(descrDate));
							break;
						}
					}
				}
			}
			
			int actsByDateChecksum = 0;
			for (Entry<Date, Integer> entry : actsCountByDate.entrySet())
			{
				actsByDateChecksum += entry.getValue();
			}
			
			System.out.println("\n\nActs by date checksum: " + actsByDateChecksum + "/" + docsCount);
			if (actsByDateChecksum != docsCount)
				throw new IllegalStateException("actsByDateChecksum != docsCount");
			
			System.out.println("\n\nGlobal Acts Histogram:");
			for (Entry<Date, Integer> entry : actsCountByDate.entrySet())
			{
				System.out.println(entry.getKey() + " " + entry.getValue());
			}
			
			
			System.out.println("\n\n");
			System.out.println("Total documents: " + docsCount);
			System.out.println("Total clusters: " + clustering.getClustersCount());
			System.out.println("Docs checksum: " + docsCheckSum + "/" + docsCount);
			System.out.println("Average documents per cluster: " +  (((float)docsCount) / descriptors.size()));
			
			System.out.println("\n\nClustering completed.");
		}
	}
}