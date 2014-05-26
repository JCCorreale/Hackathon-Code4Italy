package it.camera.hackathon.textmining.clustering;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Cluster implements ICluster
{
	private Set<IDocument> documents;
	
	public Cluster(IDocument...documents)
	{
		if (documents.length < 1)
			throw new IllegalArgumentException("documents.length < 1");
		
		this.documents = new HashSet<IDocument>();
		
		for (IDocument d : documents)
			this.documents.add(d);
	}

	public int getDocumentsCount() {
		return this.documents.size();
	}

	@Override
	public Iterator<IDocument> iterator() {
		return this.documents.iterator();
	}
	
	@Override
	public String toString()
	{
		return this.documents.toString();
	}
}