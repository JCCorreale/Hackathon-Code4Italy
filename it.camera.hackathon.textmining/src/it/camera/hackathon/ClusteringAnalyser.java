package it.camera.hackathon;

import it.camera.hackathon.textmining.clustering.ICluster;
import it.camera.hackathon.textmining.clustering.IClustering;
import it.camera.hackathon.textmining.clustering.IDocument;
import it.camera.hackathon.textmining.clustering.IDocumentCollection;
import it.camera.hackathon.textmining.clustering.ITerm;
import it.camera.hackathon.utils.MapUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ClusteringAnalyser {
	
	private int maxTerms;

	public ClusteringAnalyser(int maxTerms) 
	{
		this.maxTerms = maxTerms;
	}

	@SuppressWarnings("unchecked")
	public Set<ClusterDescriptor> getClusterDescriptors(IClustering clustering, Map<IDocument, Atto> atti, 
			IDocumentCollection documents, Map<Atto, List<ITerm>> topWordsResult)
	{
		Set<ClusterDescriptor> descriptors = new HashSet<ClusterDescriptor>();
		
		int descrId = 0;
		
		// scans all clusters
		for (ICluster c : clustering)
		{
			System.out.println("Cluster " + c);
			
			ClusterDescriptor descr = new ClusterDescriptor();
			// sets a progressive id for the cluster
			descr.id = descrId;
			descr.cluster = c;
			
			Map<String, Float> topTerms = new HashMap<String, Float>();
			// adds unsorted top terms to the map
			for (IDocument doc : c)
			{
				// gets the act related to this document
				Atto atto = atti.get(doc);
				
				for (ITerm term : doc.getTerms())
				{
					if (!topTerms.containsKey(term.toString())) {
						topTerms.put(term.toString(), documents.getTFIDF(term, doc));
					}
					else {
						topTerms.put(term.toString(), topTerms.get(term.toString()) + documents.getTFIDF(term, doc));
					}
				}
			}
			
			// sorts the top terms
			topTerms = MapUtils.sortMapByValue(topTerms, false);
			
			// adds top terms to the cluster descriptor
			int counter = 0;
			for (Entry<String, Float> entry : topTerms.entrySet())
			{
				if (counter >= maxTerms)
					break;
				descr.terms.add(entry.getKey());
				counter++;
			}
			
			// sorts atti by date
			List<Atto> attiByDate = new ArrayList<Atto>(descr.atti);
			Collections.sort(attiByDate, new Comparator<Atto>() {
				@Override
				public int compare(Atto a1, Atto a2) {
					return a1.getRevision().compareTo(a2.getRevision());
				}
			});
			
			Calendar startDate = Calendar.getInstance();
			startDate.set(2013, Calendar.JANUARY, 1);
			Calendar endDate = Calendar.getInstance();
			//endDate.set(2014, Calendar.JANUARY, 1);
			endDate.setTime(new Date());
			
			Calendar date = Calendar.getInstance();
			date.setTime(startDate.getTime());
			
			// counts occurrences for each period (starting from January 2013)
			while (date.before(endDate))
			{
				int count = 0;
				for (Atto atto : attiByDate)
				{
					if (atto.getRevision().before(date.getTime()) && atto.getRevision().after(startDate.getTime()))
						count++;
				}
				
				// adds occurrences count for this date
				descr.occurrences.put(date.getTime(), count);
				
				date.add(Calendar.MONTH, 2);
			}
			
			descriptors.add(descr);
			descrId++;
		}
		
		return descriptors;
	}
}