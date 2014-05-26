package it.camera.hackathon;

import it.camera.hackathon.textmining.clustering.ICluster;
import it.camera.hackathon.textmining.clustering.IClustering;
import it.camera.hackathon.textmining.clustering.IDocument;
import it.camera.hackathon.textmining.clustering.ITerm;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

public class ClusteringAnalyser {
	
	private int topTermsCount;

	public ClusteringAnalyser(int topTermsCount) 
	{
		this.topTermsCount = topTermsCount;
	}

	public Set<ClusterDescriptor> getClusterDescriptors(IClustering clustering, Map<IDocument, Atto> atti)
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
			
			// stores top term sorted by frequency (descending)
			TreeSet<Entry<String, Integer>> topTermsSet = new TreeSet<Entry<String, Integer>>(new Comparator<Entry<String, Integer>>() {
				@Override
				public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
					return -e1.getValue().compareTo(e2.getValue());
				}
			});
			
			// adds all related acts to this cluster descriptor and stores the sorted top terms
			for (IDocument doc : c)
			{
				descr.atti.add(atti.get(doc));
				for (ITerm term : doc.getTerms())
				{
					// TODO Usare TF-IDF (serve DocumentCollection)
					Entry<String, Integer> entry;
					topTermsSet.add(entry = new AbstractMap.SimpleEntry(term.toString(), doc.getFrequency(term)));
				}
			}
			
			// Adds top terms to the act
			int counter = 0;
			for (Entry<String, Integer> entry : topTermsSet)
			{
				if (counter > topTermsCount)
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