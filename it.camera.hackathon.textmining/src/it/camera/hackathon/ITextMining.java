package it.camera.hackathon;

import it.camera.hackathon.textmining.clustering.IDocument;
import it.camera.hackathon.textmining.clustering.IDocumentBuilder;
import it.camera.hackathon.textmining.clustering.ITerm;
import it.camera.hackathon.textmining.clustering.InMemoryDocumentBuilder;
import it.camera.hackathon.textmining.clustering.Term;
import it.camera.hackathon.utils.MapUtils;
import it.camera.hackathon.utils.StringUtils;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class ITextMining 
{
	/**
	 * Larger values provide a better clustering but worse top words, smaller values provide better top words but a worse clustering.
	 */
	protected static int topWordsCount = 20;
	protected static int minWordLength = 3;
	protected static String delimiters = " ',;.:/()[]<>";
	protected static String itaStopwordsPath = "stopwords/stopwords_ita";
	protected static String domainStopwordsPath = "stopwords/stopwords_domain";
	protected static float minTfIdf = -1.0f;
	protected static int maxTerms = 5;
	protected static boolean doClustering = true;
	protected static int downloadLimit = 1000;

	public ITextMining() 
	{
		super();
	}
	
	protected static AttoDocumentAnalyser getDocumentsAnalyser() 
	{
		return new AttoDocumentAnalyser(minTfIdf, maxTerms);
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