package it.camera.hackathon.textmining.clustering;

import it.camera.hackathon.utils.SymmetricMatrix;

/**
 * Clusters the documents using an agglomerative hierarchical clustering algorithm.
 * @author JCC
 * 
 */
public class AgglomerativeDocumentClusterer implements IDocumentClusterer {

	public AgglomerativeDocumentClusterer()
	{
		
	}
	
	@Override
	public IClustering cluster(IDocumentCollection collection) {

		// 1. inizializza la matrice di prossimità con un cluster per ogni documento
		// 2. cerca la coppia di cluster più vicini (single link - distanza punti più vicini)
		// 3. merge dei cluster trovati (la matrice di prossimità si aggiorna sostituendo i due
		// cluster con un nuovo cluster, le cui distanze da tutti gli altri cluster sono date dalla
		// minima delle distanze relative ai cluster precedenti)
		// 4. procede dal punto 2 per (n-1) iterazioni (altezza dendogramma pari a n = numero di documenti)
		
		// TODO
		return null;
	}
	
	private static class ProxymityMatrix
	{
		// TODO: Possono teoricamente esistere documenti perfettamente "allineati" (distanza coseno = 0),
		// per cui l'implementazione fatta (che fissa il valore a 0) non è utilizzabile. Creare implementazione
		// generale (diagonale principale qualsiasi) e ottimizzata (diagonale principale a default, non memorizzata).
		private SymmetricMatrix matrix;
		
		public ProxymityMatrix()
		{
			this.matrix = new SymmetricMatrix(0);
		}
		
		public void addCluster(ICluster cluster)
		{
			// TODO
		}
		
		public float getDistance(ICluster c1, ICluster c2)
		{
			// TODO
			return 0.0f;
		}
		
		public ICluster mergeClusters(ICluster c1, ICluster c2)
		{
			// TODO
			return null;
		}
	}
	
	/**
	 * A dendogram for an agglomerative algorithm.
	 * @author JCC
	 *
	 */
	private static class Dendogram
	{
		public int height;
		
		/**
		 * Adds the parent cluster linking it to the nodes corresponding to the left and right child nodes.
		 * @param parent
		 * @param left
		 * @param right
		 */
		public void add(ICluster parent, ICluster left, ICluster right)
		{
			// TODO
		}
		
		public void add(ICluster cluster)
		{
			add(cluster, null, null);
		}
		
		public IClustering getClustering(int level)
		{
			// TODO
			return null;
		}
		
		public IClustering getClustering()
		{
			return this.getClustering(this.height);
		}
		
		public static class Node
		{
			public ICluster cluster;
			public Node left;
			public Node right;
		}
	}
}