package it.camera.hackathon.textmining.clustering;


public interface IDissimilarityStrategy 
{
	public float getDissimilarity(IDocument d1, IDocument d2);
}