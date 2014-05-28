package it.camera.hackathon.textmining.clustering;

import java.util.Collection;
import java.util.List;

public final class ClusteringUtils {

	private ClusteringUtils() {}
	
	public static float getCosine(float[] v1, float[] v2)
	{
		if (v1.length != v2.length)
			throw new IllegalArgumentException("v1.length != v2.length");
		
		float dotProdAcc = 0;
		float d1NormAcc = 0;
		float d2NormAcc = 0;
		
		for (int i = 0; i < v1.length; i++)
		{
			float v1Component = v1[i];
			float v2Component = v2[i];
			
			dotProdAcc += v1Component * v2Component;
			d1NormAcc += (float)Math.pow(v1Component, 2);
			d2NormAcc += (float)Math.pow(v2Component, 2);
		}
		
		d1NormAcc = (float)Math.sqrt(d1NormAcc);
		d2NormAcc = (float)Math.sqrt(d2NormAcc);
		
		return (dotProdAcc / (d1NormAcc * d2NormAcc));
	}
	
	/**
	 * Adds vector b to vector a.
	 * @param a
	 * @param b
	 */
	public static void addVector(float[] a, float[] b)
	{
		if (a.length != b.length)
			throw new IllegalArgumentException("a.length != b.length");
		for (int i = 0; i < a.length; i++)
		{
			a[i] += b[i];
		}
	}
	
	public static void divVector(float[] vector, float divisor)
	{
		for (int i = 0; i < vector.length; i++)
		{
			vector[i] /= divisor;
		}
	}
	
	public static float[] getMeanVector(Collection<float[]> vectors) {
		
		float[] meanVector = null;
		
		if (vectors.size() < 1)
			throw new IllegalArgumentException("vectors.size() < 1");
		
		for (float[] vector : vectors)
		{
			if (meanVector == null)
				meanVector = new float[vector.length];
			
			if (vector.length != meanVector.length)
				throw new IllegalArgumentException("All vectors must have the same length.");
			
			addVector(meanVector, vector);
		}
		
		divVector(meanVector, vectors.size());
		return meanVector;
	}
}