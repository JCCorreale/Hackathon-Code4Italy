package it.camera.hackathon.textmining.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ HtmlRemoverTest.class, HttpGetRequestTest.class,
		HttpStringDataSourceTest.class, PlainTextReaderTest.class,
		TopWordCountAnalyzerTest.class, VirtuosoActContentByDateTest.class,
		VirtuosoActLastRevisionDateTest.class, VirtuosoSparqlGetTest.class,
		WordCounterTest.class })
public class AllTests {

}
