package it.camera.hackathon.textmining.clustering;

/**
 * Considers the distance between nearest elements in the clusters.
 * @author JCC
 *
 */
public class SingleLinkProximityStrategy extends BaseProxymityStrategy {

	public SingleLinkProximityStrategy(IDissimilarityStrategy distanceStrategy) {
		super(distanceStrategy);
	}

	@Override
	public float getDissimilarity(ICluster c1, ICluster c2) 
	{
		float minDistance = -1;
		
		for (IDocument doc1 : c1)
		{
			for (IDocument doc2 : c2)
			{
				float docsDistance = this.getDistanceStrategy().getDissimilarity(doc1, doc2);
				
				if (docsDistance < minDistance || minDistance < 0)
					minDistance = docsDistance;
			}
		}
		
		return minDistance;
	}
}