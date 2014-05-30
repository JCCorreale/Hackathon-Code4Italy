package it.camera.hackathon.textmining.clustering;

import it.camera.hackathon.utils.MapUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
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
		StringBuilder sb = new StringBuilder();
		// TODO Use only in DEBUG
		Map<String, Integer> termsCounts = new HashMap<String, Integer>();
		for (IDocument doc : this.documents)
		{
			for (ITerm term : doc.getTerms())
			{
				if (!termsCounts.containsKey(term))
					termsCounts.put(term.toString(), doc.getFrequency(term));
				else
					termsCounts.put(term.toString(), termsCounts.get(term) + doc.getFrequency(term));
			}
		}
		termsCounts = MapUtils.sortMapByValue(termsCounts, false);
		for (Entry<String, Integer> entry : termsCounts.entrySet())
		{
			sb.append(entry.toString() + ", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		return sb.toString();
		
		//return this.documents.toString();
	}
}