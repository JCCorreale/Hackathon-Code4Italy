package it.camera.hackathon;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import it.camera.hackathon.datasource.IDataSource;
import it.camera.hackathon.textmining.HtmlRemover;
import it.camera.hackathon.textmining.IWordCountResult;
import it.camera.hackathon.textmining.TextFileDataSource;
import it.camera.hackathon.textmining.TopWordsCountAnalyzer;
import it.camera.hackathon.textmining.clustering.IDocument;
import it.camera.hackathon.textmining.clustering.IDocumentBuilder;
import it.camera.hackathon.textmining.clustering.ITerm;
import it.camera.hackathon.textmining.clustering.InMemoryDocumentBuilder;
import it.camera.hackathon.textmining.clustering.Term;
import it.camera.hackathon.utils.MapUtils;
import it.camera.hackathon.utils.StringUtils;
import it.camera.opendata.model.Atto;

public class TextMining 
{

	// preprocessing defaults
	private static int topWordsCount = 10;
	private static int minWordLength = 3;
	private static String delimiters = " ',;.:/()[]<>";
	private static String itaStopwordsPath = "stopwords/stopwords_ita";
	private static String domainStopwordsPath = "stopwords/stopwords_domain";
	
	// analysis defaults
	private static float minTfIdf = 0.0f;
	private static int maxTerms = 5; // -1 = no limit
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args)
	{
		String[] filenames = new HTMLDocumentFactory().getFilePaths();
		List<Entry<Atto, IDocument>> documents = new ArrayList<Entry<Atto, IDocument>>();
		
		for (String filename : filenames)
		{
			System.out.println("************************************************");
			System.out.println("Document " +  filename);
			
			// gets the input
			IDataSource<String> source = new TextFileDataSource(filename);
			String html = source.getData();
			
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
			documents.add(new AbstractMap.SimpleEntry(new Atto(filename), document));
			
			System.out.println("Document " + filename + " done.\n\n");
			
			// prints some stat about the document
//			System.out.println("\n\nFrequency by term:\n");
//			Utils.printMap(document.getFrequencyByTerm());
//			System.out.println("\n\nWeighted frequency by term:\n");
//			Utils.printMap(document.getWeightedFrequencyByTerm());
		}
		
		AttoDocumentAnalyser analyser = getDocumentsAnalyser();
		
		Map<Atto, List<ITerm>> result = analyser.getData(documents);
		
		// TODO Aggregate Documents
		
		//Map<Atto, List<ITerm>> attoTerms = analyser.getData(null); // TODO
		
		//ValueComparator comparator =  new ValueComparator(attoTerms);
		// prints the top words
		//Utils.printMap(topWords);
		
		// prints top terms for each document
		for (Entry<Atto, List<ITerm>> entry : result.entrySet())
		{
			StringBuilder terms = new StringBuilder();
			for (ITerm term : entry.getValue())
			{
				terms.append("[" + term + "] ");
			}
			
			System.out.println(entry.getKey().toString() + " " + terms.toString());
		}
	}
	
	private static AttoDocumentAnalyser getDocumentsAnalyser()
	{
		return new AttoDocumentAnalyser(minTfIdf, maxTerms);
	}
	
	private static ITermsDisambiguator getTermsDisambiguator()
	{	
		return new TermsDisambiguator();
	}
	
	private static String[] getStopWords()
	{
		String[] result = null;
		try 
		{
			// concateno i due elenchi di stopwords leggendoli dai 2 file (cartella stopwords)
			result = StringUtils.concatArrays(StringUtils.textFileToStringArray(itaStopwordsPath), StringUtils.textFileToStringArray(domainStopwordsPath));
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return result;
	}
	
	private static TextMiningWordCounter buildWordCounter()
	{
		TextMiningWordCounter wordCounter = new TextMiningWordCounter();
		wordCounter.setMinLength(minWordLength);
		wordCounter.setDelimiters(delimiters);
		wordCounter.setStopWords(getStopWords());
		return wordCounter;
	}
	
	/**
	 * Replaces synonyms with single TERMS (each related to a single CONCEPT).
	 * @param topWords
	 * @return
	 */
	private static List<Entry<ITerm, Integer>> getTerms(List<Entry<String, Integer>> topWords)
	{
		List<Entry<ITerm, Integer>> terms = new ArrayList<Entry<ITerm, Integer>>();
		
		// TODO Handle Synonims
		for (Entry<String, Integer> entry : topWords)
		{
			terms.add(new AbstractMap.SimpleEntry<ITerm, Integer>(new Term(entry.getKey()), entry.getValue()));
		}
		
		return terms;
	}
	
	private static IDocument buildDocument(List<Entry<String, Integer>> topWords, int totalWords)
	{
		List<Entry<ITerm, Integer>> terms = getTerms(topWords);
		IDocumentBuilder builder = new InMemoryDocumentBuilder();
		
		for (Entry<ITerm, Integer> entry : terms)
		{
			builder.addTerm(entry.getKey(), entry.getValue());
		}
		
		return builder.getDocument();
	}
}