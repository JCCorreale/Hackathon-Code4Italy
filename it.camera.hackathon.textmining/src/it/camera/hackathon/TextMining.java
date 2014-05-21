package it.camera.hackathon;

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
import it.camera.opendata.model.Atto;

public class TextMining {

	// preprocessing defaults
	private static int topWordsCount = 10;
	private static int minWordLength = 3;
	private static String delimiters = " ',;.:/()[]<>";
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
		return ("a abbia abbiamo abbiano abbiate ad adesso agl agli ai al all alla alle allo allora altre altri altro anche ancora avemmo avendo avere avesse avessero avessi avessimo aveste avesti avete aveva avevamo avevano avevate avevi avevo avrai avranno avrebbe avrebbero avrei avremmo avremo avreste avresti avrete avrà avrò avuta avute avuti avuto ben buono c che chi ci cinque coi col come comprare con consecutivi consecutivo contro cosa cui da dagl dagli dai dal dall dalla dalle dallo degl degli dei del dell della delle dello dentro deve devo di doppio dov dove due e ebbe ebbero ebbi ecco ed era erano eravamo eravate eri ero essendo faccia facciamo facciano facciate faccio facemmo facendo facesse facessero facessi facessimo faceste facesti faceva facevamo facevano facevate facevi facevo fai fanno farai faranno fare farebbe farebbero farei faremmo faremo fareste faresti farete farà farò fece fecero feci fine fino fosse fossero fossi fossimo foste fosti fra fu fui fummo furono gente giu gli ha hai hanno ho i il in indietro invece io l la lavoro le lei li lo loro lui lungo ma me meglio mi mia mie miei mio molta molti molto ne negl negli nei nel nell nella nelle nello no noi nome non nostra nostre nostri nostro nove nuovi nuovo o oltre ora otto peggio per perché pero persone piu più poco primo promesso qua quale quanta quante quanti quanto quarto quasi quattro quella quelle quelli quello questa queste questi questo qui quindi quinto rispetto sara sarai saranno sarebbe sarebbero sarei saremmo saremo sareste saresti sarete sarà sarò se secondo sei sembra sembrava senza sette si sia siamo siano siate siete solo sono sopra soprattutto sotto sta stai stando stanno starai staranno starebbe starebbero starei staremmo staremo stareste staresti starete starà starò stati stato stava stavamo stavano stavate stavi stavo stemmo stesse stessero stessi stessimo stesso steste stesti stette stettero stetti stia stiamo stiano stiate sto su sua subito sue sugl sugli sui sul sull sulla sulle sullo suo suoi tanto te tempo terzo ti tra tre triplo tu tua tue tuo tuoi tutti tutto ultimo un una uno va vai vi voi volte vostra vostre vostri vostro"
				+ " comma commi articolo articoli legge leggi anno anni decreto decreti entro enti ente norma norme codice codici pagina pagine pag pag. decreto-legge decreti-legge disposizioni legislativo legislativa legislatura parte parti ministero ministeri presente presenti senso sensi prevedere prevede prevedono essere sono stato stati milioni milione euro certi certo modificazioni modifiche pari dispari termine termini ministro periodo periodi data date fine fini modalità modalita esigibili convertito caso lettera ministro ministri consigli consiglio repubblica attività attivita attivo deve devono dovere dovuto sostituite alla alle ratifica ratificare limitatamente tabella tabelle nazione nazionale riga righe deliberazione deliberazioni delibera seguente successivo precedente italia italiana italiano italiani intendere intendersi competenza competenze previsione previsioni identico identica identici derivente derivanti testo testi").split(" "); // NB: domain dependent stopwords
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