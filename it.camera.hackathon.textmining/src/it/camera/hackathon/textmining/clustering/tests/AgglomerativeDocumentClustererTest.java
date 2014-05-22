package it.camera.hackathon.textmining.clustering.tests;

import static org.junit.Assert.*;

import javax.xml.parsers.DocumentBuilder;

import it.camera.hackathon.textmining.clustering.AgglomerativeDocumentClusterer;
import it.camera.hackathon.textmining.clustering.Dendrogram;
import it.camera.hackathon.textmining.clustering.DocumentCollection;
import it.camera.hackathon.textmining.clustering.ICluster;
import it.camera.hackathon.textmining.clustering.IDocumentBuilder;
import it.camera.hackathon.textmining.clustering.InMemoryDocumentBuilder;
import it.camera.hackathon.textmining.clustering.ProximityMatrix;
import it.camera.hackathon.textmining.clustering.TFIDFCosineDistanceStrategy;
import it.camera.hackathon.textmining.clustering.IDistanceStrategy;
import it.camera.hackathon.textmining.clustering.IDocumentCollection;
import it.camera.hackathon.textmining.clustering.IProximityStrategy;
import it.camera.hackathon.textmining.clustering.SingleLinkProximityStrategy;

import org.junit.Before;
import org.junit.Test;

public class AgglomerativeDocumentClustererTest {

	@Test
	public void test()
	{
		// TODO
	}
}