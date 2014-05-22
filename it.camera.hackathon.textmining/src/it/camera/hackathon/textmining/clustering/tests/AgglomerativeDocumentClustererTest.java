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

	private IDocumentCollection documents;
	
	private ICluster ba;
	private ICluster fi;
	private ICluster mi;
	private ICluster na;
	private ICluster rm;
	private ICluster to;
	
	@Before
	public void setup()
	{
		IDocumentBuilder builder = new InMemoryDocumentBuilder();
		
		builder.addTerm("Prova", 1);
		
		this.documents = new DocumentCollection(builder.getDocument());
	}
	
	@Test
	public void test() 
	{
		IProximityStrategy proximityStrategy = new DummyProximityStrategy();
		
		AgglomerativeDocumentClusterer cluster = new AgglomerativeDocumentClusterer(proximityStrategy);
		Dendrogram dendrogram = cluster.getClusteringDendrogram(documents);
		
		System.out.println(dendrogram);
	}
	
	/**
	 * Uses the proximity matrix from ProximityMatrixTest as an example.
	 * @author JCC
	 *
	 */
	private class DummyProximityStrategy implements IProximityStrategy
	{
		private ProximityMatrix m;
		
		@Before
		public void setup()
		{
			m = new ProximityMatrix();
			// based on an example of clustering of italian cities
			ba = new DummyCluster("BA");
			fi = new DummyCluster("FI");
			mi = new DummyCluster("MI");
			na = new DummyCluster("NA");
			rm = new DummyCluster("RM");
			to = new DummyCluster("TO");
			
			m.addClusters(ba, fi, mi, na, rm, to);
			
			// BA
			m.setClustersDistance(ba, fi, 662);
			m.setClustersDistance(ba, mi, 877);
			m.setClustersDistance(ba, na, 255);
			m.setClustersDistance(ba, rm, 412);
			m.setClustersDistance(ba, to, 992);
			// FI
			m.setClustersDistance(fi, mi, 295);
			m.setClustersDistance(fi, na, 468);
			m.setClustersDistance(fi, rm, 268);
			m.setClustersDistance(fi, to, 400);
			// MI
			m.setClustersDistance(mi, na, 754);
			m.setClustersDistance(mi, rm, 564);
			m.setClustersDistance(mi, to, 138);
			// NA
			m.setClustersDistance(na, rm, 219);
			m.setClustersDistance(na, to, 869);
			// RM
			m.setClustersDistance(rm, to, 869);
		}

		@Override
		public float getDistance(ICluster c1, ICluster c2) 
		{
			return m.getClustersDistance(c1, c2);
		}
	}
}