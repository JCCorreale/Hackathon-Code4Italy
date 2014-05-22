package it.camera.hackathon.textmining.clustering.tests;

import static org.junit.Assert.*;
import it.camera.hackathon.textmining.clustering.Dendrogram;
import it.camera.hackathon.textmining.clustering.ICluster;
import it.camera.hackathon.textmining.clustering.IClustering;

import org.junit.Before;
import org.junit.Test;

public class DendrogramTest {

	private Dendrogram d;
	private ICluster a, b, c, ab, abc;
	
	@Before
	public void setup()
	{
		d = new Dendrogram();
		
		a = new DummyCluster("A"); 
		b = new DummyCluster("B");
		c = new DummyCluster("C");
		
		ab = new DummyCluster("AB");
		abc = new DummyCluster("ABC");
		
		d.add(a);
		d.add(b);
		d.add(c);
		
		d.add(ab, a, b);
		d.add(abc, ab, c);
		
		System.out.println("\n\n****************************\nDendrogram:\n\n\t" + d);
	}
	
	@Test
	public void testGetHeight()
	{
		assertEquals(3, d.getHeight());
		
		System.out.println("\nHeight = " + d.getHeight());
	}
	
	@Test
	public void testGetClustering()
	{
		IClustering c = d.getClustering(3);
		System.out.println("Clustering at level 3 -> " + c.getClustersCount() + " clusters (expected 1).");
		System.out.println(c);
		assertEquals(1, c.getClustersCount());
		
		c = d.getClustering(2);
		System.out.println("Clustering at level 2 -> " + c.getClustersCount() + " clusters (expected 2).");
		System.out.println(c);
		assertEquals(2, c.getClustersCount());
		
		c = d.getClustering(1);
		System.out.println("Clustering at level 1 -> " + c.getClustersCount() + " clusters (expected 3).");
		System.out.println(c);
		assertEquals(3, c.getClustersCount());
		
		c = d.getClustering();
		System.out.println("Clustering at default level (3) -> " + c.getClustersCount() + " clusters (expected 1).");
		System.out.println(c);
		assertEquals(1, c.getClustersCount());
		
		try { d.getClustering(0); assertTrue(false); }
		catch (IllegalArgumentException e) { assertTrue(true); }
		
		try { d.getClustering(4); assertTrue(false); }
		catch (IllegalArgumentException e) { assertTrue(true); }
	}
}
