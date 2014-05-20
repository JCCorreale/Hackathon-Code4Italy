package it.camera.hackathon.textmining.clustering;


public interface IDistanceStrategy 
{
	public float getDistance(IDocument d1, IDocument d2);
}