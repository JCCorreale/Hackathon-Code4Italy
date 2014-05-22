package it.camera.hackathon.textmining.clustering;

import java.util.Iterator;
import java.util.Set;

public class Clustering implements IClustering {

	private Set<ICluster> clusters;
	
	public Clustering(ICluster...clusters)
	{
		if (clusters.length < 1)
			throw new IllegalArgumentException("clusters.length < 1");
		
		for (ICluster c : clusters)
			this.clusters.add(c);
	}

	@Override
	public Iterator<ICluster> iterator() {
		return this.clusters.iterator();
	}
}