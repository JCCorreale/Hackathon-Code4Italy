package it.camera.hackathon.textmining.clustering;

public interface IDocumentClusterer 
{
	public IClustering getClustering(IDocumentCollection collection);
}