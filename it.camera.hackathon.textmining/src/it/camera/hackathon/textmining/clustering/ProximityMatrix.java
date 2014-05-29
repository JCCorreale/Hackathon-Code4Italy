package it.camera.hackathon.textmining.clustering;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import it.camera.hackathon.utils.MapUtils;
import it.camera.hackathon.utils.SymmetricMatrix;
import it.camera.hackathon.utils.SymmetricMatrix.IMatrixAction;

public class ProximityMatrix
{
	// TODO: Possono teoricamente esistere documenti perfettamente "allineati" (distanza coseno = 0),
	// per cui l'implementazione fatta (che fissa il valore a 0) non è utilizzabile. Creare implementazione
	// generale (diagonale principale qualsiasi) e ottimizzata (diagonale principale a default, non memorizzata).
	// Nota: per ora posso comunque evitare di confrontare indici uguali.
	private SymmetricMatrix matrix;
	private Map<ICluster, Integer> clusterIndexes;
	
	public ProximityMatrix()
	{
		this.matrix = new SymmetricMatrix();
		this.clusterIndexes = new HashMap<ICluster, Integer>();
	}
	
	public void addClusters(ICluster... clusters)
	{
		for (ICluster c : clusters)
			this.addCluster(c);
	}
	
	public void addCluster(ICluster cluster)
	{
		this.clusterIndexes.put(cluster, this.matrix.getRowsCount());
		float[] dataRow = new float[this.matrix.getRowsCount()];
		for (int i = 0; i < dataRow.length; i++)
			dataRow[i] = -1; // default value
		this.matrix.add(dataRow);
	}
	
	public void removeClusters(ICluster... clusters)
	{
		int[] indexes = new int[clusters.length];
		for (int i = 0; i < clusters.length; i++)
		{
			indexes[i] = this.clusterIndexes.get(clusters[i]);
			this.clusterIndexes.remove(clusters[i]);
			// decrements greater indexes
			for (Entry<ICluster, Integer> entry : this.clusterIndexes.entrySet())
			{
				if (entry.getValue() > indexes[i])
					this.clusterIndexes.put(entry.getKey(), entry.getValue() - 1);
			}
		}
		this.matrix.remove(indexes);
	}
	
	public void setClustersDistance(ICluster c1, ICluster c2, float distance)
	{
		int r = this.clusterIndexes.get(c1);
		int c = this.clusterIndexes.get(c2);
		this.matrix.set(r, c, distance);
	}
	
	public float getClustersDistance(ICluster c1, ICluster c2)
	{
		int r = this.clusterIndexes.get(c1);
		int c = this.clusterIndexes.get(c2);
		return this.matrix.get(r, c);
	}
	
	/**
	 * 
	 * @return The couple of nearest clusters.
	 */
	public ICluster[] getNearestClusters()
	{
		FindMinMatrixAction action = new FindMinMatrixAction(this.matrix);
		this.matrix.iterate(action);
		ICluster[] clusters = new ICluster[2];
		clusters[0] = (ICluster) MapUtils.getKeyByValue(clusterIndexes, action.minR);
		clusters[1] = (ICluster) MapUtils.getKeyByValue(clusterIndexes, action.minC);
		return clusters;
	}
	
	public int getClusterIndex(ICluster cluster)
	{
		return this.clusterIndexes.get(cluster);
	}
	
	public Iterable<ICluster> getClusters()
	{
		return this.clusterIndexes.keySet();
	}
	
	public int getClustersCount()
	{
		return this.clusterIndexes.size();
	}
	
	@Override
	public String toString()
	{
		return this.matrix.toString();
	}
	
	private class FindMinMatrixAction implements IMatrixAction
	{
		private int minR = -1, minC = -1;
		private float min = -1.0f;
		
		private SymmetricMatrix m;
		
		public FindMinMatrixAction(SymmetricMatrix m)
		{
			this.m = m;
		}
		
		@Override
		public void action(int r, int c) {
			if (r != c) // do not consider the diagonal
			{
				float val = this.m.get(r, c);
				
				if (val < min || min < 0)
				{
					min = val;
					minR = r;
					minC = c;
				}
			}
		}
	}
}