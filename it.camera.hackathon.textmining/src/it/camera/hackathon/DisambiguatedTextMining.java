package it.camera.hackathon;

import it.camera.hackathon.datasource.IDataSource;
import it.camera.hackathon.textmining.HtmlRemover;
import it.camera.hackathon.textmining.IWordCountResult;
import it.camera.hackathon.textmining.TextFileDataSource;
import it.camera.hackathon.textmining.TopWordsCountAnalyzer;
import it.camera.hackathon.textmining.clustering.IDocument;
import it.camera.hackathon.utils.MapUtils;

import java.util.List;
import java.util.Map.Entry;


public class DisambiguatedTextMining extends ITextMining 
{
	public static void main(String[] args) 
	{
			// gets the input
			String filename = "PDL 676.htm";
			IDataSource<String> source = new TextFileDataSource(filename);
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
	
			// prints synonyms for the top words
	//		SynonimScraper synonimScraper = new SynonimScraper();
	//		for (Entry<String, Integer> entry : topWords)
	//		{
	//			System.out.println(entry.getKey() + " " + entry.getValue() + synonimScraper.FindSynonims(entry.getKey()));
	//		}
			
			ITermsDisambiguator disambiguator = getTermsDisambiguator();
			topWords = MapUtils.mapToEntryList(disambiguator.getDisambiguatedTerms(topWords));
			
			// creates an IDocument instance from the retrieved data
			IDocument document = buildDocument(topWords, counter.getAcceptedWordCount());
			
			// prints some stat about the document
			System.out.println("\n\nFrequency by term:\n");
			MapUtils.printMap(document.getFrequencyByTerm());
			System.out.println("\n\nWeighted frequency by term:\n");
			MapUtils.printMap(document.getWeightedFrequencyByTerm());
			
			getDocumentsAnalyser();
			
			// TODO Aggregate Documents
			
			//Map<Atto, List<ITerm>> attoTerms = analyser.getData(null); // TODO
			
			// prints the top words
			//Utils.printMap(topWords);
		}
}