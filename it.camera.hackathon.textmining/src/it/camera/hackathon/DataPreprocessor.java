package it.camera.hackathon;

import it.camera.hackathon.textmining.IWordCountResult;
import it.camera.hackathon.textmining.TopWordsCountAnalyzer;
import it.camera.hackathon.textmining.clustering.IDocument;

public class DataPreprocessor implements IDataPreprocessor {

	// TODO Default, generalizzare
	private static int topWordsCount = 30;
	private static int minWordLength = 3;
	private static String delimiters = " ',;.:/()[]<>";
	
	private static String[] getStopWords()
	{
		return ("a abbia abbiamo abbiano abbiate ad adesso agl agli ai al all alla alle allo allora altre altri altro anche ancora avemmo avendo avere avesse avessero avessi avessimo aveste avesti avete aveva avevamo avevano avevate avevi avevo avrai avranno avrebbe avrebbero avrei avremmo avremo avreste avresti avrete avrà avrò avuta avute avuti avuto ben buono c che chi ci cinque coi col come comprare con consecutivi consecutivo contro cosa cui da dagl dagli dai dal dall dalla dalle dallo degl degli dei del dell della delle dello dentro deve devo di doppio dov dove due e ebbe ebbero ebbi ecco ed era erano eravamo eravate eri ero essendo faccia facciamo facciano facciate faccio facemmo facendo facesse facessero facessi facessimo faceste facesti faceva facevamo facevano facevate facevi facevo fai fanno farai faranno fare farebbe farebbero farei faremmo faremo fareste faresti farete farà farò fece fecero feci fine fino fosse fossero fossi fossimo foste fosti fra fu fui fummo furono gente giu gli ha hai hanno ho i il in indietro invece io l la lavoro le lei li lo loro lui lungo ma me meglio mi mia mie miei mio molta molti molto ne negl negli nei nel nell nella nelle nello no noi nome non nostra nostre nostri nostro nove nuovi nuovo o oltre ora otto peggio per perché pero persone piu più poco primo promesso qua quale quanta quante quanti quanto quarto quasi quattro quella quelle quelli quello questa queste questi questo qui quindi quinto rispetto sara sarai saranno sarebbe sarebbero sarei saremmo saremo sareste saresti sarete sarà sarò se secondo sei sembra sembrava senza sette si sia siamo siano siate siete solo sono sopra soprattutto sotto sta stai stando stanno starai staranno starebbe starebbero starei staremmo staremo stareste staresti starete starà starò stati stato stava stavamo stavano stavate stavi stavo stemmo stesse stessero stessi stessimo stesso steste stesti stette stettero stetti stia stiamo stiano stiate sto su sua subito sue sugl sugli sui sul sull sulla sulle sullo suo suoi tanto te tempo terzo ti tra tre triplo tu tua tue tuo tuoi tutti tutto ultimo un una uno va vai vi voi volte vostra vostre vostri vostro"
				+ " comma commi articolo articoli legge leggi anno anni decreto decreti entro enti ente norma norme codice codici pagina pagine pag pag. decreto-legge decreti-legge disposizioni legislativo legislativa legislatura parte parti ministero ministeri presente presenti senso sensi prevedere prevede prevedono essere sono stato stati milioni milione euro certi certo modificazioni modifiche pari dispari termine termini ministro periodo periodi data date fine fini modalità modalita").split(" ");
	}

	private static TextMiningWordCounter buildWordCounter()
	{
		TextMiningWordCounter wordCounter = new TextMiningWordCounter();
		wordCounter.setMinLength(minWordLength);
		wordCounter.setDelimiters(delimiters);
		wordCounter.setStopWords(getStopWords());
		return wordCounter;
	}
	
	@Override
	public IDocument getData(String plainText) throws IllegalArgumentException {
		// TODO divedere in 2 parti:
		// wordCounting
		System.out.println("Counting word occurences (may take a while!)...");
		TextMiningWordCounter counter = buildWordCounter();
		IWordCountResult result = counter.parse(plainText);
		
		// wordAnalysis
		// retrieves the top word
		System.out.println("Retrieving the first " + topWordsCount + " top words...\n");
		TopWordsCountAnalyzer analyzer = new TopWordsCountAnalyzer();
		ITextDocumentBuilder builder = new DocumentTextBuilder(counter.getAcceptedWordCount());
		
		return builder.getData(analyzer.getSortedWords(topWordsCount, result));
	}

}
