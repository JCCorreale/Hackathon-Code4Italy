package it.camera.hackathon;

import java.util.List;
import java.util.Map.Entry;

import it.camera.hackathon.textmining.HtmlRemover;
import it.camera.hackathon.textmining.IStringDataSource;
import it.camera.hackathon.textmining.IWordCountResult;
import it.camera.hackathon.textmining.TextFileDataSource;
import it.camera.hackathon.textmining.TopWordsCountAnalyzer;
import it.camera.hackathon.textmining.clustering.IDocument;

public class TextMining {

	private static int topWordsCount = 10;
	private static int minWordLength = 6;
	private static String delimiters = " '";
	
	public static void main(String[] args)
	{
		// gets the input
		String filename = "C:\\Users\\JCC\\workspace\\java\\hackathon\\Textalyser\\PDL 676.htm";
		IStringDataSource source = new TextFileDataSource(filename);
		String html = source.getData();
		
		// removes HTML
		System.out.println("Removing HTML tags...");
		String plainText = HtmlRemover.text(html);
		
		// counts the occurence
		System.out.println("Counting word occurences (may take a while!)...");
		TextMiningWordCounter counter = buildWordCounter();
		IWordCountResult result = counter.parse(plainText);
		
		// retrieves the top word
		System.out.println("Retrieving the first " + topWordsCount + " top words...\n");
		TopWordsCountAnalyzer analyzer = new TopWordsCountAnalyzer();
		List<Entry<String, Integer>> topWords = analyzer.getSortedWords(topWordsCount, result);
		
		// TODO Synoms Detection
		
		// creates an IDocument instance from the retrieved data
		IDocument document = buildDocument(topWords, counter.getAcceptedWordCount());
		
		// prints the top words
		Utils.printWordEntries(topWords);
	}
	
	private static TextMiningWordCounter buildWordCounter()
	{
		TextMiningWordCounter wordCounter = new TextMiningWordCounter();
		wordCounter.setMinLength(minWordLength);
		wordCounter.setDelimiters(delimiters);
		return wordCounter;
	}
	
	private static IDocument buildDocument(List<Entry<String, Integer>> topWords, int totalWords)
	{
		// TODO
		return null;
	}
}