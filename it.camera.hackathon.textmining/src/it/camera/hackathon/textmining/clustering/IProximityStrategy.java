package it.camera.hackathon.textmining.clustering;

public interface IProximityStrategy 
{
	public float getDissimilarity(ICluster c1, ICluster c2);
}