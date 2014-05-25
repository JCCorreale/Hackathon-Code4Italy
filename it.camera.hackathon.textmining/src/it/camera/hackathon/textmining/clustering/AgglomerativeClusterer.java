package it.camera.hackathon.textmining.clustering;

import java.util.HashMap;
import java.util.Map;

public abstract class AgglomerativeClusterer {
	
	protected abstract ProximityMatrix initMatrix();
	
	protected abstract Dendrogram initDendrogram();

	protected abstract ICluster getMergedCluster(ICluster c1, ICluster c2);

	private ICluster mergeCluster(ProximityMatrix m, ICluster c1, ICluster c2)
	{
		// la matrice di prossimità si aggiorna sostituendo i due cluster con un nuovo cluster
		
		// stores distances between the given clusters and each other clusters
		
		Map<ICluster, Float> c1Distances = new HashMap<ICluster, Float>();
		Map<ICluster, Float> c2Distances = new HashMap<ICluster, Float>();
		
		for (ICluster cluster : m.getClusters())
		{
			// does not consider removed clusters
			if (!cluster.equals(c1) && !cluster.equals(c2))
			{
				c1Distances.put(cluster, m.getClustersDistance(c1, cluster));
				c2Distances.put(cluster, m.getClustersDistance(c2, cluster));
			}
		}
		
		// removes the clusters from the matrix
		m.removeClusters(c1, c2);
		
		// generates the merged cluster and adds it to the matrix
		ICluster mergedCluster = this.getMergedCluster(c1, c2);
		m.addCluster(mergedCluster);
		
		// le distanze da tutti gli altri cluster sono date dalla minima delle distanze relative ai cluster precedenti
		
		// updates new cluster's distances
		for (ICluster cluster : c1Distances.keySet())
		{
			float c1Distance = c1Distances.get(cluster);
			float c2Distance = c2Distances.get(cluster);
			float distance = c1Distance < c2Distance ? c1Distance : c2Distance;
			
			m.setClustersDistance(mergedCluster, cluster, distance);
		}
		
		return mergedCluster;
	}
	
	/**
	 * Implements the core algorithm.
	 * @return
	 */
	protected Dendrogram getClusteringDendrogram()
	{
		// 1. inizializza la matrice di prossimità con un cluster per ogni documento
		ProximityMatrix m = this.initMatrix();
		Dendrogram dendrogram = this.initDendrogram();
		
		for (int i = 0; i < m.getClustersCount() - 1; i++)
		{
			// 2. cerca la coppia di cluster più vicini (single link - distanza punti più vicini)
			ICluster[] nearestClusters = m.getNearestClusters();
			// TODO DEBUG
			float dist = m.getClustersDistance(nearestClusters[0], nearestClusters[1]);
			// 3. merge dei cluster trovati
			ICluster mergedCluster = this.mergeCluster(m, nearestClusters[0], nearestClusters[1]);
			
			System.out.println("Merged clusters (distance = " + dist + "):\n" + nearestClusters[0] + "\n" + nearestClusters[1]/* + "\nto:\n" + mergedCluster*/ + "\n\n");
			
			dendrogram.add(mergedCluster, nearestClusters[0], nearestClusters[1]);
			
			// 4. procede dal punto 2 per (n-1) iterazioni (altezza dendogramma pari a n = numero di documenti)
		}
		
		return dendrogram;
	}
}
