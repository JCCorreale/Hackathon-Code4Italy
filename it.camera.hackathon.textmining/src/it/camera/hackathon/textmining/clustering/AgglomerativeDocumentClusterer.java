package it.camera.hackathon.textmining.clustering;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import it.camera.hackathon.utils.SymmetricMatrix;

/**
 * Clusters the documents using an agglomerative hierarchical clustering algorithm.
 * @author JCC
 * 
 */
public class AgglomerativeDocumentClusterer implements IDocumentClusterer {

	private IProximityStrategy proximityStrategy; 
	
	public AgglomerativeDocumentClusterer(IProximityStrategy proxymityStrategy)
	{
		this.proximityStrategy = proxymityStrategy;
	}
	
	private ProximityMatrix initMatrix(IDocumentCollection documents)
	{
		ProximityMatrix m = new ProximityMatrix();
		Set<ICluster> clusters = new HashSet<ICluster>();
		// adds a cluster for each documents
		for (IDocument d : documents.getDocuments())
		{
			ICluster cluster;
			m.addCluster(cluster = new Cluster(d));
			clusters.add(cluster);
		}
		// calculates the distances
		for (ICluster c1 : clusters)
		{
			for (ICluster c2 : clusters)
			{
				if (!c1.equals(c2))
				{
					m.setClustersDistance(c1, c2, this.proximityStrategy.getDistance(c1, c2));
				}
			}
		}
		
		return m;
	}
	
	private void mergeCluster(ProximityMatrix m, ICluster c1, ICluster c2)
	{
		//(la matrice di prossimità si aggiorna sostituendo i due
		// cluster con un nuovo cluster, le cui distanze da tutti gli altri cluster sono date dalla
		// minima delle distanze relative ai cluster precedenti)
		m.removeClusters(c1, c2);
		
		// TODO
	}
	
	@Override
	public IClustering cluster(IDocumentCollection documents) {

		// 1. inizializza la matrice di prossimità con un cluster per ogni documento
		ProximityMatrix m = this.initMatrix(documents);
		
		for (int i = 0; i < documents.getDocumentsCount(); i++)
		{
			// 2. cerca la coppia di cluster più vicini (single link - distanza punti più vicini)
			ICluster[] nearestClusters = m.getNearestClusters();
			// 3. merge dei cluster trovati
			this.mergeCluster(m, nearestClusters[0], nearestClusters[1]);
			
			// 4. procede dal punto 2 per (n-1) iterazioni (altezza dendogramma pari a n = numero di documenti)
		}
		
		// TODO
		return null;
	}
	
	private class Cluster implements ICluster
	{
		private Set<IDocument> documents;
		
		public Cluster(IDocument...documents)
		{
			if (documents.length < 1)
				throw new IllegalArgumentException("documents.length < 1");
			
			for (IDocument d : documents)
				this.documents.add(d);
		}

		@Override
		public Iterator<IDocument> iterator() {
			return this.documents.iterator();
		}
	}
}