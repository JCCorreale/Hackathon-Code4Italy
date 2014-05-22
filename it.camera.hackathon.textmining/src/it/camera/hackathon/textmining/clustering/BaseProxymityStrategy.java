package it.camera.hackathon.textmining.clustering;

public abstract class BaseProxymityStrategy implements IProximityStrategy
{
	private IDistanceStrategy distanceStrategy;
	
	public BaseProxymityStrategy(IDistanceStrategy distanceStrategy) {
		this.distanceStrategy = distanceStrategy;
	}
	
	public IDistanceStrategy getDistanceStrategy()
	{
		return this.distanceStrategy;
	}
}