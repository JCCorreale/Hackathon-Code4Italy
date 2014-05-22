package it.camera.hackathon.textmining.clustering;

import java.util.Iterator;
import java.util.Set;

public class Cluster implements ICluster
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