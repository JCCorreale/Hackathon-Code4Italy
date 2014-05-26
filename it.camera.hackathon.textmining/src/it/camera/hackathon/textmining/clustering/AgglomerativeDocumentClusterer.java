package it.camera.hackathon.textmining.clustering;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Clusters the documents using an agglomerative hierarchical clustering algorithm.
 * @author JCC
 * 
 */
public class AgglomerativeDocumentClusterer extends AgglomerativeClusterer implements IDocumentClusterer
{
	private IProximityStrategy proximityStrategy;
	private IDocumentCollection documents;
	private ProximityMatrix matrix;
	
	public AgglomerativeDocumentClusterer(IProximityStrategy proxymityStrategy)
	{
		this.proximityStrategy = proxymityStrategy;
	}
	
	private ProximityMatrix initMatrix(IDocumentCollection documents)
	{
		matrix = new ProximityMatrix();
		Set<ICluster> clusters = new HashSet<ICluster>();
		// adds a cluster for each documents
		for (IDocument d : documents.getDocuments())
		{
			ICluster cluster;
			matrix.addCluster(cluster = new Cluster(d));
			clusters.add(cluster);
		}
		// calculates the distances
		for (ICluster c1 : clusters)
		{
			for (ICluster c2 : clusters)
			{
				if (!c1.equals(c2))
				{
					matrix.setClustersDistance(c1, c2, this.proximityStrategy.getDissimilarity(c1, c2));
				}
			}
		}
		
		return matrix;
	}
	
	private Dendrogram initDendrogram(ProximityMatrix matrix)
	{
		Dendrogram d = new Dendrogram();
		for (ICluster c : matrix.getClusters())
			d.add(c);
		return d;
	}
	
	@Override
	protected ICluster getMergedCluster(ICluster c1, ICluster c2)
	{
		List<IDocument> documents = new ArrayList<IDocument>();
		for (IDocument d : c1)
			documents.add(d);
		for (IDocument d : c2)
			documents.add(d);
		return new Cluster(documents.toArray(new IDocument[0]));
	}
	
//	private ICluster mergeCluster(ProximityMatrix m, ICluster c1, ICluster c2)
//	{
//		// la matrice di prossimità si aggiorna sostituendo i due cluster con un nuovo cluster
//		
//		// stores distances between the given clusters and each other clusters
//		
//		Map<ICluster, Float> c1Distances = new HashMap<ICluster, Float>();
//		Map<ICluster, Float> c2Distances = new HashMap<ICluster, Float>();
//		
//		for (ICluster cluster : m.getClusters())
//		{
//			c1Distances.put(cluster, m.getClustersDistance(c1, cluster));
//			c2Distances.put(cluster, m.getClustersDistance(c2, cluster));
//		}
//		
//		// removes the clusters from the matrix
//		m.removeClusters(c1, c2);
//		
//		// generates the merged cluster and adds it to the matrix
//		ICluster mergedCluster = this.getMergedCluster(c1, c2);
//		m.addCluster(mergedCluster);
//		
//		// le distanze da tutti gli altri cluster sono date dalla minima delle distanze relative ai cluster precedenti
//		
//		// updates new cluster's distances
//		for (ICluster cluster : c1Distances.keySet())
//		{
//			float c1Distance = c1Distances.get(cluster);
//			float c2Distance = c2Distances.get(cluster);
//			float distance = c1Distance < c2Distance ? c1Distance : c2Distance;
//			
//			m.setClustersDistance(mergedCluster, c2, distance);
//		}
//		
//		return mergedCluster;
//	}
	
	public Dendrogram getClusteringDendrogram(IDocumentCollection documents)
	{
		this.documents = documents;
		return this.getClusteringDendrogram();
	}
	
	@Override
	public IClustering getClustering(IDocumentCollection documents) {
		return this.getClusteringDendrogram(documents).getClustering(); // TODO Default clustering...
	}
	
	public IClustering getClustering(IDocumentCollection documents, int dendrogramLevel) {
		return this.getClustering(documents, dendrogramLevel);
	}

	@Override
	protected ProximityMatrix initMatrix() {
		return this.initMatrix(this.documents);
	}

	@Override
	protected Dendrogram initDendrogram() {
		return this.initDendrogram(this.matrix);
	}
}