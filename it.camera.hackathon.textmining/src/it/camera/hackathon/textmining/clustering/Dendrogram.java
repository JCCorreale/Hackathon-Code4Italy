package it.camera.hackathon.textmining.clustering;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * A dendogram for an agglomerative algorithm.
 * @author JCC
 *
 */
public class Dendrogram
{
	private int height;
	private LinkedList<Node> topNodes;
	
	public Dendrogram()
	{
		this.topNodes = new LinkedList<Node>();
		this.height = 0;
	}
	
	/**
	 * Adds the parent cluster linking it to the nodes corresponding to the left and right child nodes.
	 * @param parent
	 * @param left
	 * @param right
	 */
	public void add(ICluster parent, ICluster left, ICluster right)
	{
		if (parent == null)
			throw new IllegalArgumentException("parent == null");
		if (left == null ^ right == null)
			throw new IllegalArgumentException("child nodes must be both defined or both undefined");
		
		Node parentNode;
		
		if (left == null)
		{
			this.topNodes.add(parentNode = new Node(parent));
		}
		else
		{
			Node leftNode = null, rightNode = null;
			
			for (Node n : this.topNodes)
			{
				if (n.cluster.equals(left))
					leftNode = n;
				if (n.cluster.equals(right))
					rightNode = n;
			}
			
			if (leftNode == null || rightNode == null)
				throw new IllegalStateException("Given clusters not in this dendrogram");

			this.topNodes.remove(leftNode);
			this.topNodes.remove(rightNode);
			
			parentNode = new Node(parent, leftNode, rightNode);
			this.topNodes.add(parentNode);
		}
		
		if (parentNode.depth > this.height)
			this.height = parentNode.depth;
	}
	
	/**
	 * Used to add the leaves of the dendrogram, can be used only when the height is at most equal to 1.
	 * @param cluster
	 */
	public void add(ICluster cluster)
	{
		if (height > 1)
			throw new IllegalStateException("height > 1. Can't add new leaves.");
		
		add(cluster, null, null);
	}
	
	private static IClustering buildClustering(List<Node> topNodes)
	{
		List<ICluster> clusters = new ArrayList<ICluster>();
		for (Node n : topNodes)
		{
			clusters.add(n.cluster);
		}
		return new Clustering(clusters.toArray(new ICluster[0]));
	}
	
	private static boolean checkTopNodes(int level, List<Node> currentTopNodes, List<Node> higherNodes)
	{
		for (Node n : currentTopNodes)
		{
			if (n.depth > level)
				higherNodes.add(n);
		}
		return higherNodes.size() > 0;
	}
	
	private static IClustering getClustering(int level, int height, List<Node> topNodes)
	{
		LinkedList<Node> currentTopNodes = new LinkedList<Node>(topNodes);
		LinkedList<Node> higherNodes = new LinkedList<Node>();
		
		// nodes with depth > level are appended to the list (the list is not cleared)
		while(checkTopNodes(level, currentTopNodes, higherNodes))
		{
			// removes the higher nodes from the topNodes list and inserts its child nodes
			for (Node n : higherNodes)
			{
				currentTopNodes.remove(n);
				currentTopNodes.add(n.left);
				currentTopNodes.add(n.right);
			}
			
			higherNodes.clear();
		}
		
		return buildClustering(currentTopNodes);
	}
	
	/**
	 * Must be 0 < level <= height.
	 * @param level
	 * @return
	 */
	public IClustering getClustering(int level)
	{
		if (level < 1 || level > height)
			throw new IllegalArgumentException("Must be 0 < level <= height.");
		
		return getClustering(level, height, this.topNodes);
	}
	
	public IClustering getClustering()
	{
		return this.getClustering(this.height);
	}
	
	public int getHeight()
	{
		return this.height;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for (Node n : this.topNodes)
			sb.append(n.toString() + "\n");
		return sb.toString();
	}
	
	private static class Node
	{
		public ICluster cluster;
		public Node left;
		public Node right;
		public int depth;
		
		public Node(ICluster cluster, Node left, Node right) {
			this.cluster = cluster;
			this.left = left;
			this.right = right;
			
			if (left == null)
				this.depth = 1;
			else
			{
				int maxChildDepth = left.depth > right.depth ? left.depth : right.depth;
				this.depth = maxChildDepth + 1;
			}
		}

		public Node(ICluster cluster) {
			this(cluster, null, null);
		}
		
		public String toString()
		{
			return this.cluster.toString() + (this.left == null ? "" : ("(" + this.left.toString() + ", " + this.right.toString() + ")"));
		}
	}
}