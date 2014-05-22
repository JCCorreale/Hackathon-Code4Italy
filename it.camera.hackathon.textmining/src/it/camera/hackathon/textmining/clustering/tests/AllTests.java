package it.camera.hackathon.textmining.clustering.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AgglomerativeDocumentClustererTest.class,
		DendrogramTest.class, DocumentBuilderTest.class,
		DocumentCollectionTest.class, InMemoryDocumentBuilderTest.class,
		ProximityMatrixTest.class })
public class AllTests {

}
