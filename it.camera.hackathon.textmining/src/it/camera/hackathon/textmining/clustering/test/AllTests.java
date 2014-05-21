package it.camera.hackathon.textmining.clustering.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DocumentBuilderTest.class, DocumentCollectionTest.class,
		InMemoryDocumentBuilderTest.class, InMemoryDocumentTest.class,
		ProxymityMatrixTest.class })
public class AllTests {

}
