package it.camera.hackathon.textmining.clustering;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Clustering implements IClustering {

	private Set<ICluster> clusters;
	
	public Clustering(ICluster...clusters)
	{
		if (clusters.length < 1)
			throw new IllegalArgumentException("clusters.length < 1");
	
		this.clusters = new HashSet<ICluster>();
		
		for (ICluster c : clusters)
			this.clusters.add(c);
	}
	
	@Override
	public int getClustersCount()
	{
		return this.clusters.size();
	}

	@Override
	public Iterator<ICluster> iterator() {
		return this.clusters.iterator();
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder("[");
		for (ICluster cluster : this.clusters)
		{
			sb.append(cluster + ", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append("]");
		return sb.toString();
	}
}