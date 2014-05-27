package it.camera.hackathon.textmining.clustering.tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import it.camera.hackathon.textmining.clustering.ClusteringUtils;

import org.junit.Test;

public class ClusteringUtilsTest {

	@Test
	public void testCosine() 
	{
		float[] v1 = new float[] { 1, 1, 1, 1, 1 };
		float[] v2 = new float[] { 1, 1, 1, 1, 1 };
		
		assertEquals(1, ClusteringUtils.getCosine(v1, v2), 0.01f);
		
		v2 = new float[] { 2, 2, 2, 2, 2 };
		
		assertEquals(1, ClusteringUtils.getCosine(v1, v2), 0.01f);
		
		v1 = new float[] { 0, 2, 0, 2, 0};
		v2 = new float[] { 2, 0, 2, 0, 2 };
		
		assertEquals(0, ClusteringUtils.getCosine(v1, v2), 0.01f);
	}
	
	public void testGetMeanVector()
	{
		List<float[]> vectors;
		float[] meanVector;
		
		float[] v1 = new float[] { 1, 1, 1, 1, 1 };
		float[] v2 = new float[] { 3, 3, 3, 3, 3 };
		vectors = Arrays.asList(v1, v2);
		
		meanVector = ClusteringUtils.getMeanVector(vectors);
		for (float f : meanVector)
			assertEquals(2, f, 0.01f);
	}

}
