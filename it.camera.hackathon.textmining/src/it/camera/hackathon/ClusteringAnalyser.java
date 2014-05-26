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
			
			Map<String, Integer> topTerms = new HashMap<String, Integer>();
			
			// for each document in the cluster, gets the corresponding act
			for (IDocument doc : c)
			{
				Atto atto = atti.get(doc);
				// adds the act to the cluster descriptor
				descr.atti.add(atto);
				// for each act, scans the top words and inserts the corresponding term to the topTerms map
				List<ITerm> attoTopWords = topWordsResult.get(atto);
				// adds terms to the topTerms map, with the corresponding score
				for (int i = 0; i < attoTopWords.size(); i++)
				{
					String term = attoTopWords.get(i).toString();
					int score = attoTopWords.size() - i;
					if (!topTerms.containsKey(term))
						topTerms.put(term, score);
					else
						topTerms.put(term, topTerms.get(term) + score);
					
				}
			}
			
			topTerms = MapUtils.sortMapByValue(topTerms, false);
			
			// adds top terms to the cluster descriptor
			int counter = 0;
			for (Entry<String, Integer> entry : topTerms.entrySet())
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
			
			System.out.println("\n\nChecking revisions for documents in cluster " + c);
			System.out.println("From " + date.getTime() + " to " + endDate.getTime() + "\n\n");
			
			// counts occurrences for each period (starting from January 2013)
			while (date.before(endDate))
			{
				System.out.println("Acts before date " + date.getTime());
				
				int count = 0;
				for (Atto atto : attiByDate)
				{
					if (atto.getRevision().before(date.getTime()) && atto.getRevision().after(startDate.getTime()))
					{
						System.out.println("Act " + atto + " revision = " + atto.getRevision());
						count++;
					}
				}
				
				System.out.println(count + " documents found before date " + date.getTime());
				System.out.println();
				
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