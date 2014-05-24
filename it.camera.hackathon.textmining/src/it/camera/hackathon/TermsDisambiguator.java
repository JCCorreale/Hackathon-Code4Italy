package it.camera.hackathon;

import it.camera.hackathon.dictionary.SynonimsDictionary;
import it.camera.hackathon.stemming.StemmingUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class TermsDisambiguator implements ITermsDisambiguator 
{
	private SynonimsDictionary dictionary = new SynonimsDictionary();
	
	@Override
	public Map<String, Integer> getDisambiguatedTerms(List<Entry<String, Integer>> terms) 
	{
		Map<String, Integer> result = new HashMap<String, Integer>();
		// whenever a new term is added to the results, it and its synonyms are stored in this
		// sorted set in order to allow a fast synonyms evaluation
		// each entry is related to the corresponding entry in the result map
		TreeMap<String, String> knownWords = new TreeMap<String, String>();
		
		for(Entry<String, Integer> e : terms)
		{
			String mainTerm = null;
			String search = null;
			
			// gets all known words that may have the same root as the current term
			String stem = StemmingUtils.Stem(e.getKey());
			SortedMap<String, String> sameRootCandidates = knownWords.subMap(stem, ((Character)(char)(stem.charAt(0) + 1)).toString());
			
			List<String> synonims = null;
			
			// verifies same roots candidates
			if (!sameRootCandidates.isEmpty())
			{
				for (String candidate : sameRootCandidates.keySet())
				{
					// TODO Assumes all terms with the same root refers to the same result, it would be
					// better to find the more suitable result for the term if more than one are possible
					if (StemmingUtils.SameRoot(e.getKey(), candidate))
						search = knownWords.get(candidate);
				}
			}
			// searches for synonyms only if no common root as been found
			if (search == null)
			{
				// gets synonyms for each given term and searches for them into the knownWords map
				synonims = this.dictionary.getSynonims(e.getKey(), false);
				for (String synonim : synonims)
				{		
					search = knownWords.get(synonim);
					if (search != null)
						break;
				}
			}
			
			// se ha trovato termini con cui raggruppare, somma i risultati
			if(search != null)
			{
				result.put(mainTerm = search, e.getValue() + result.get(search));
			}
			// altrimenti inserisci nuovo record nei risultati
			else
			{
				result.put(mainTerm = e.getKey(), e.getValue());
			}
			
			// to optimize performance, synonyms are not always considered (they are consider only if the roots' comparison fails)
			if (synonims != null)
			{
				// all synonyms are associated with the mainTerm
				for (String synonim : synonims)
				{
					knownWords.put(synonim, mainTerm);
				}
			}
		}
		
//		for(Entry<String, Integer> e : terms)
//		{
//			String search = null;
//			for(String res : result.keySet())
//			{
//				if
//				(	// Raggruppa parole con stessa radice (stemmed words)
//					StemmingUtils.SameRoot(e.getKey(), res)
//				)
//				{
//					search = res;
//				}
//				else if
//				(	// Raggruppa parole sinonime (fa controllo solo se non hanno stessa radice, per diminuire passaggi)
//					dictionary.areSynonims(e.getKey(), res)
//					
//				)
//				{
//					search = res;
//				}
//			}
//			
//			// se ha trovato termini con cui raggruppare, somma i risultati
//			if(search != null)
//			{
//				result.put(search, e.getValue() + result.get(search));
//			}
//			// altrimenti inserisci nuovo record nei risultati
//			else
//			{
//				result.put(e.getKey(), e.getValue());
//			}
//		}
		return result;
	}
}
