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
import java.util.TreeMap;
import java.util.TreeSet;

public class ClusteringAnalyser {
	
	private int maxTerms;

	public ClusteringAnalyser(int maxTerms) 
	{
		this.maxTerms = maxTerms;
	}
	
	private Date[] getMinMaxDates(Map<IDocument, Atto> atti)
	{
		if (atti.size() < 1) throw new IllegalArgumentException("atti.size() < 1");
		
		Date minDate = null;
		Date maxDate = null;
		
		for (Entry<IDocument, Atto> entry : atti.entrySet())
		{
			Atto atto = entry.getValue();
			Date attoDate = atto.getRevision();
			if (minDate == null)
				minDate = attoDate;
			if (maxDate == null)
				maxDate = attoDate;
			
			if (attoDate.before(minDate))
				minDate = attoDate;
			if (attoDate.after(maxDate))
				maxDate = attoDate;
		}
	
		return new Date[] { minDate, maxDate };
	}
	
	private List<Date> getDatePeriods(Date minDate, Date maxDate, int monthsStep)
	{
		if (minDate.equals(maxDate))
			throw new IllegalArgumentException("minDate.equals(maxDate)");
		if (minDate.after(maxDate))
			throw new IllegalArgumentException("minDate.after(maxDate)");
		
		List<Date> dates = new ArrayList<Date>();
		Calendar min = Calendar.getInstance();
		min.setTime(minDate);
		min.set(Calendar.HOUR_OF_DAY, 0);
		min.set(Calendar.MINUTE, 0);
		min.set(Calendar.SECOND, 0);
		Calendar tmp = Calendar.getInstance();
		tmp.setTime(maxDate);
		tmp.set(Calendar.HOUR_OF_DAY, 0);
		tmp.set(Calendar.MINUTE, 0);
		tmp.set(Calendar.SECOND, 0);
		// adds 1 day to avoid problem with the time of day
		tmp.add(Calendar.DATE, 1);
		while (tmp.after(min))
		{
			dates.add(tmp.getTime());
			tmp.add(Calendar.MONTH, -monthsStep);
		}
		Collections.reverse(dates);
		return dates;
	}

	@SuppressWarnings("unchecked")
	public Set<ClusterDescriptor> getClusterDescriptors(IClustering clustering, Map<IDocument, Atto> atti, 
			IDocumentCollection documents, Map<Atto, List<ITerm>> topWordsResult)
	{
		Set<ClusterDescriptor> descriptors = new HashSet<ClusterDescriptor>();
		
		int descrId = 0;
		
		// gets global info about acts' dates
		Date[] minMaxDates = this.getMinMaxDates(atti);
		List<Date> dates = this.getDatePeriods(minMaxDates[0], minMaxDates[1], 1);
		
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
			
			/* ATTI BY DATE */
			
			// initializes the set of dates and the descriptor map
			TreeSet<Date> datesSet = new TreeSet<Date>();
			for (Date date : dates)
			{
				datesSet.add(date);
				descr.occurrences.put(date, 0);
			}
			
			// updates the counter for each date entry
			for (Atto atto : descr.atti)
			{
				// finds the nearest greater date
				Date ngDate = datesSet.ceiling(atto.getRevision());
				if (ngDate == null)
					throw new IllegalArgumentException("ngDate == null");
				descr.occurrences.put(ngDate, descr.occurrences.get(ngDate) + 1);
			}
			
//			System.out.println("\n\nChecking revisions for documents in cluster " + c);
//			System.out.println("From " + date.getTime() + " to " + lastDate.getTime());
//			System.out.println("Total acts: " + attiByDate.size());
//			
//			for (Atto atto : attiByDate)
//			{
//				System.out.print("\t" + atto.getIRI());
//				System.out.println(" " + atto.getRevision());
//			}
//			System.out.println("\n\n");
//			
//			// init
//			
//			// counts occurrences for each period
//			while (date.before(lastDate))
//			{
//				System.out.println("Acts between date " + firstDate.getTime() + " and date " + date.getTime());
//				
//				int count = 0;
//				for (Atto atto : attiByDate)
//				{
//					if (atto.getRevision().before(date.getTime()) && atto.getRevision().after(firstDate.getTime()))
//					{
//						System.out.println("Act " + atto + " revision = " + atto.getRevision());
//						count++;
//					}
//				}
//				
//				System.out.println(count + " documents found before date " + date.getTime());
//				System.out.println();
//				
//				// adds occurrences count for this date
//				descr.occurrences.put(date.getTime(), count);
//				
//				firstDate.setTime(date.getTime());
//				date.add(Calendar.MONTH, 2);
//			}
			
			descriptors.add(descr);
			descrId++;
		}
		
		return descriptors;
	}
}