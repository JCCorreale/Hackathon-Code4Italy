package it.camera.hackathon.textmining.clustering.tests;

import static org.junit.Assert.*;

import it.camera.hackathon.textmining.clustering.ICluster;
import it.camera.hackathon.textmining.clustering.ProximityMatrix;

import org.junit.Before;
import org.junit.Test;

public class ProxymityMatrixTest 
{
	
	ProximityMatrix m;
	ICluster ba;
	ICluster fi;
	ICluster mi;
	ICluster na;
	ICluster rm;
	ICluster to;
	
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
		
		//System.out.println("\n***********************\n\nInitialized matrix:\n\n" + m);
		
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
		
		//System.out.println("\n***********************\n\nFilled matrix:\n\n" + m);
	}

	@Test
	public void setClustersDistanceTest() 
	{	
		assertEquals(662, m.getClustersDistance(ba, fi), 0.01);
		assertEquals(877, m.getClustersDistance(ba, mi), 0.01);
		assertEquals(255, m.getClustersDistance(ba, na), 0.01);
		assertEquals(412, m.getClustersDistance(ba, rm), 0.01);
		assertEquals(992, m.getClustersDistance(ba, to), 0.01);
		// FI
		assertEquals(295, m.getClustersDistance(fi, mi), 0.01);
		assertEquals(468, m.getClustersDistance(fi, na), 0.01);
		assertEquals(268, m.getClustersDistance(fi, rm), 0.01);
		assertEquals(400, m.getClustersDistance(fi, to), 0.01);
		// MI
		assertEquals(754, m.getClustersDistance(mi, na), 0.01);
		assertEquals(564, m.getClustersDistance(mi, rm), 0.01);
		assertEquals(138, m.getClustersDistance(mi, to), 0.01);
		// NA
		assertEquals(219, m.getClustersDistance(na, rm), 0.01);
		assertEquals(869, m.getClustersDistance(na, to), 0.01);
		// RM
		assertEquals(869, m.getClustersDistance(rm, to), 0.01);
	}
	
	@Test
	public void getClustersDistanceTest() 
	{	
		assertEquals(662, m.getClustersDistance(fi, ba), 0.01);
		assertEquals(877, m.getClustersDistance(mi, ba), 0.01);
		assertEquals(255, m.getClustersDistance(na, ba), 0.01);
		assertEquals(412, m.getClustersDistance(rm, ba), 0.01);
		assertEquals(992, m.getClustersDistance(to, ba), 0.01);
		// FI
		assertEquals(295, m.getClustersDistance(mi, fi), 0.01);
		assertEquals(468, m.getClustersDistance(na, fi), 0.01);
		assertEquals(268, m.getClustersDistance(rm, fi), 0.01);
		assertEquals(400, m.getClustersDistance(to, fi), 0.01);
		// MI
		assertEquals(754, m.getClustersDistance(na, mi), 0.01);
		assertEquals(564, m.getClustersDistance(rm, mi), 0.01);
		assertEquals(138, m.getClustersDistance(to, mi), 0.01);
		// NA
		assertEquals(219, m.getClustersDistance(rm, na), 0.01);
		assertEquals(869, m.getClustersDistance(to, na), 0.01);
		// RM
		assertEquals(869, m.getClustersDistance(to, rm), 0.01);
	}
	
	@Test
	public void getNearestClustersTest()
	{
		ICluster[] nearest = m.getNearestClusters();
		
		assertNotEquals(nearest[0], nearest[1]);
		assertTrue(nearest[0].equals(mi) || nearest[1].equals(mi));
		assertTrue(nearest[0].equals(to) || nearest[1].equals(to));
		
		System.out.println("\n***********************\n\nNearest cities distance:\n\n" + m.getClustersDistance(nearest[0], nearest[1]));
	}
}