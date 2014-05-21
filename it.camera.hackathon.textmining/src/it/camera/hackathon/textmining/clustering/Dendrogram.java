package it.camera.hackathon.textmining.clustering;

/**
 * A dendogram for an agglomerative algorithm.
 * @author JCC
 *
 */
public class Dendrogram
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