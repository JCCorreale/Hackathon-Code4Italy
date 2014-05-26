package it.camera.hackathon.textmining.clustering.tests;

import it.camera.hackathon.textmining.clustering.AgglomerativeClusterer;
import it.camera.hackathon.textmining.clustering.Dendrogram;
import it.camera.hackathon.textmining.clustering.ICluster;
import it.camera.hackathon.textmining.clustering.IProximityStrategy;
import it.camera.hackathon.textmining.clustering.ProximityMatrix;

import org.junit.Before;
import org.junit.Test;

public class AgglomerativeClustererTest {
	
	private ProximityMatrix m;
	
	private ICluster ba;
	private ICluster fi;
	private ICluster mi;
	private ICluster na;
	private ICluster rm;
	private ICluster to;
	
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
		m.setClustersDistance(rm, to, 669);
	}
	
	@Test
	public void test() 
	{
		DummyClustererImpl clusterer = new DummyClustererImpl();
		Dendrogram clusteringDendrogram = clusterer.getClusteringDendrogram();
		
		// TODO Check dendrogram construction (remember: possible bug introduced in SymmetricMatrix.contains)
		// (maybe one more step needs to be done)
		
		System.out.println(clusteringDendrogram);
	}
	
	/**
	 * This dummy subclass allows to test the abstract AgglomerativeClusterer class.
	 * @author JCC
	 *
	 */
	private class DummyClustererImpl extends AgglomerativeClusterer
	{
		@Override
		protected ProximityMatrix initMatrix() {
			return m;
		}

		@Override
		protected Dendrogram initDendrogram() {
			Dendrogram d = new Dendrogram();
			d.add(ba);
			d.add(fi);
			d.add(mi);
			d.add(na);
			d.add(rm);
			d.add(to);
			return d;
		}

		@Override
		protected ICluster getMergedCluster(ICluster c1, ICluster c2) {
			return new DummyCluster(c1.toString() + "/" + c2.toString());
		}
		
		public Dendrogram getClusteringDendrogram()
		{
			return super.getClusteringDendrogram();
		}
	}
	
	/**
	 * Uses the proximity matrix from ProximityMatrixTest as an example.
	 * @author JCC
	 *
	 */
	private class DummyProximityStrategy implements IProximityStrategy
	{
		@Override
		public float getDissimilarity(ICluster c1, ICluster c2) 
		{
			return m.getClustersDistance(c1, c2);
		}
	}
}