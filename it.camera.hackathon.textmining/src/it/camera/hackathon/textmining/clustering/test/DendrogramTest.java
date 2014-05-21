package it.camera.hackathon.textmining.clustering.test;

import static org.junit.Assert.*;
import it.camera.hackathon.textmining.clustering.Dendrogram;
import it.camera.hackathon.textmining.clustering.ICluster;

import org.junit.Test;

public class DendrogramTest {

	@Test
	public void test() {
		
		Dendrogram d = new Dendrogram();
		
		ICluster a = new DummyCluster("A"), b = new DummyCluster("B"), c = new DummyCluster("C");
		ICluster ab = new DummyCluster("AB"), abc = new DummyCluster("ABC");
		
		d.add(a);
		d.add(b);
		d.add(c);
		
		d.add(ab, a, b);
		d.add(abc, ab, c);
		
		assertEquals(3, d.getHeight());
		
		System.out.println("\n\n****************************Dendrogram:\n\n" + d);
	}

}
