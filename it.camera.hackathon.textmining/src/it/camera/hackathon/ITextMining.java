package it.camera.hackathon;

import it.camera.hackathon.textmining.clustering.IDocument;
import it.camera.hackathon.textmining.clustering.IDocumentBuilder;
import it.camera.hackathon.textmining.clustering.ITerm;
import it.camera.hackathon.textmining.clustering.InMemoryDocumentBuilder;
import it.camera.hackathon.textmining.clustering.Term;
import it.camera.hackathon.utils.StringUtils;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public abstract class ITextMining 
{
	/**
	 * Larger values provide a better clustering but worse top words, smaller values provide better top words but a worse clustering.
	 */
	protected static int topWordsCount = 2000;
	protected static int minWordLength = 3;
	protected static String delimiters = " ',;.:/()[]<>";
	protected static String itaStopwordsPath = "stopwords/stopwords_ita";
	protected static String domainStopwordsPath = "stopwords/stopwords_domain";
	protected static float minTfIdf = -1.0f;
	/**
	 * Also affects the number of terms considered by the clustering analyzer (input).
	 */
	protected static int maxActTerms = 5;
	/**
	 * Number of terms in the output of the clustering analyzer.
	 */
	protected static int maxClusterTerms = 5;
	protected static boolean doClustering = true;
	protected static int downloadLimit = 10;
	protected static boolean writeTopTerms = false;
	protected static boolean writeClustering = false;
	
	static
	{
//		setTopWordsConfig();
		setClusteringConfig();
//		setTopWordsTestConfig();
//		setClusteringTestConfig();
	}
	
	private static void setTopWordsConfig()
	{
		topWordsCount = 20;
		minWordLength = 3;
		delimiters = " ',;.:/()[]<>";
		itaStopwordsPath = "stopwords/stopwords_ita";
		domainStopwordsPath = "stopwords/stopwords_domain";
		minTfIdf = -1.0f;
		maxActTerms = 5;
		maxClusterTerms = 5;
		doClustering = true;
		downloadLimit = 1000;
		writeTopTerms = true;
		writeClustering = false;
	}
	
	private static void setClusteringConfig()
	{
		topWordsCount = 2000;
		minWordLength = 3;
		delimiters = " ',;.:/()[]<>";
		itaStopwordsPath = "stopwords/stopwords_ita";
		domainStopwordsPath = "stopwords/stopwords_domain";
		minTfIdf = -1.0f;
		maxActTerms = 50;
		maxClusterTerms = 5;
		doClustering = true;
		downloadLimit = 1000;
		writeTopTerms = false;
		writeClustering = true;
	}
	
	private static void setTopWordsTestConfig()
	{
		topWordsCount = 20;
		minWordLength = 3;
		delimiters = " ',;.:/()[]<>";
		itaStopwordsPath = "stopwords/stopwords_ita";
		domainStopwordsPath = "stopwords/stopwords_domain";
		minTfIdf = -1.0f;
		maxActTerms = 5;
		maxClusterTerms = 5;
		doClustering = true;
		downloadLimit = 10;
		writeTopTerms = false;
		writeClustering = false;
	}
	
	private static void setClusteringTestConfig()
	{
		topWordsCount = 2000;
		minWordLength = 3;
		delimiters = " ',;.:/()[]<>";
		itaStopwordsPath = "stopwords/stopwords_ita";
		domainStopwordsPath = "stopwords/stopwords_domain";
		minTfIdf = -1.0f;
		maxActTerms = 50;
		maxClusterTerms = 5;
		doClustering = true;
		downloadLimit = 10;
		writeTopTerms = false;
		writeClustering = false;
	}
	

	public ITextMining() 
	{
		super();
	}
	
	protected static AttoDocumentAnalyser getDocumentsAnalyser() 
	{
		return new AttoDocumentAnalyser(minTfIdf, maxActTerms);
	}

	protected static ITermsDisambiguator getTermsDisambiguator() 
	{
		//return TermsDisambiguatorFactory.getDummyDisambiguator();
		//return TermsDisambiguatorFactory.getSimpleDisambiguator();
		return TermsDisambiguatorFactory.getOptimizedDisambiguator();
	}

	protected static String[] getStopWords()
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

	protected static TextMiningWordCounter buildWordCounter() {
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
	private static List<Entry<ITerm, Integer>> getTerms(List<Entry<String, Integer>> topWords) {
		List<Entry<ITerm, Integer>> terms = new ArrayList<Entry<ITerm, Integer>>();
		
		// TODO Handle Synonims
		for (Entry<String, Integer> entry : topWords)
		{
			terms.add(new AbstractMap.SimpleEntry<ITerm, Integer>(new Term(entry.getKey()), entry.getValue()));
		}
		
		return terms;
	}

	protected static IDocument buildDocument(List<Entry<String, Integer>> topWords, int totalWords) {
		List<Entry<ITerm, Integer>> terms = getTerms(topWords);
		IDocumentBuilder builder = new InMemoryDocumentBuilder();
		
		for (Entry<ITerm, Integer> entry : terms)
		{
			builder.addTerm(entry.getKey(), entry.getValue());
		}
		
		return builder.getDocument();
	}
}