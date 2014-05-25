package it.camera.hackathon;

import it.camera.hackathon.textmining.clustering.ICluster;
import it.camera.hackathon.textmining.clustering.IClustering;
import it.camera.hackathon.textmining.clustering.IDocument;
import it.camera.hackathon.textmining.clustering.ITerm;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ClusteringAnalyser {

	public ClusteringAnalyser() 
	{
		
	}

	public Set<ClusterDescriptor> getClusterDescriptors(IClustering clustering, Map<IDocument, Atto> atti)
	{
		Set<ClusterDescriptor> descriptors = new HashSet<ClusterDescriptor>();

		int descrId = 0;
		
		// scans all clusters
		for (ICluster c : clustering)
		{
			ClusterDescriptor descr = new ClusterDescriptor();
			// sets a progressive id for the cluster
			descr.id = descrId;
			
			String topTerm = null;
			int maxFreq = 0;
			
			// adds all related acts to this cluster descriptor
			// and stores the top term
			for (IDocument doc : c)
			{
				descr.atti.add(atti.get(doc));
				for (ITerm term : doc.getTerms())
				{
					int freq;
					
					if ((freq = doc.getFrequency(term)) > maxFreq)
					{
						topTerm = term.toString();
						maxFreq = freq;
					}
				}
			}
			
			// TODO stores only the top term for now...
			descr.terms.add(topTerm);
			
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
			
			// counts occurences for each period (starting from january 2013)
			while (date.before(endDate))
			{
				int count = 0;
				for (Atto atto : attiByDate)
				{
					if (atto.getRevision().before(date.getTime()) && atto.getRevision().after(startDate.getTime()))
						count++;
				}
				
				// adds occurences count for this date
				descr.occurrences.put(date.getTime(), count);
				
				date.add(Calendar.MONTH, 2);
			}
			
			// TODO Adds top terms to the act
			
			
			// TODO
			
			descriptors.add(descr);
			descrId++;
		}
		
		return descriptors;
	}
}
