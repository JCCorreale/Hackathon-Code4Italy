package it.camera.hackathon.textmining.clustering;


public interface IProximityStrategy 
{
	public float getDistance(ICluster c1, ICluster c2);
}