package it.camera.hackathon.textmining.clustering;

import it.camera.hackathon.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class ClusteringRestructorer {

	public ClusteringRestructorer() {
		// TODO Auto-generated constructor stub
	}
	
	public IClustering restructureClustering(IClustering clustering, float threshold)
	{
		List<ICluster> result = new ArrayList<ICluster>(CollectionUtils.fromIterable(clustering));
		
		for (ICluster documentCluster : result)
		{
			// for each document in each cluster
			for (IDocument document : documentCluster)
			{
				for (ICluster cluster : clustering)
				{
					// TODO gets the cluster centroid
					float[] clusterCentroid;
					// TODO gets the distance between the document and the centroid, checking whether the centroid 
					// is "equal" to the document (in this case the cluster should not be considered
					float[] dist;
					
					// TODO break if you find a suitable cluster (dist < T) (NOT OPTIMAL)
				}
				
				// if dist > threshold for each cluster, delete the document (remove it from its cluster)
				// 	if the document is the only one in the cluster, remove the cluster
				// if dist < threshold for a cluster C, add the point to the cluster
				
				// continue with the new settings (the cluster centroids must be recalculated)
			}
		}
		
		// TODO print some summary info 
		
		return new Clustering(result.toArray(new ICluster[0]));
	}
}