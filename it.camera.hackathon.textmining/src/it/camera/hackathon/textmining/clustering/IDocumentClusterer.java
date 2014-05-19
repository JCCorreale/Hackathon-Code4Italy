package it.camera.hackathon.textmining.clustering;

public interface IDocumentClusterer 
{
	public IClustering cluster(IDocumentCollection collection);
}