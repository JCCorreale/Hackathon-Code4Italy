package it.camera.hackathon.textmining.clustering;

public interface IClusteringEvaluator {

	public float getCoesion(ICluster c);
	
	public float getSeparation(ICluster c1, ICluster c2);
	
	public float getTotalSeparation(IClustering c);
	
	public float getTotalCoesion(IClustering c);
	
	public float getSilhouette(ICluster c);
}