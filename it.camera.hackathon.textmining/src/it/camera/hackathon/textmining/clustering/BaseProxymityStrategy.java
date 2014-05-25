package it.camera.hackathon.textmining.clustering;

public abstract class BaseProxymityStrategy implements IProximityStrategy
{
	private IDissimilarityStrategy distanceStrategy;
	
	public BaseProxymityStrategy(IDissimilarityStrategy distanceStrategy) {
		this.distanceStrategy = distanceStrategy;
	}
	
	public IDissimilarityStrategy getDistanceStrategy()
	{
		return this.distanceStrategy;
	}
}