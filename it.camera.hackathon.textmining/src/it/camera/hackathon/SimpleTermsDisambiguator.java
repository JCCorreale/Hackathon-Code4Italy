package it.camera.hackathon;

import it.camera.hackathon.dictionary.SynonimsDictionary;
import it.camera.hackathon.stemming.StemmingUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SimpleTermsDisambiguator implements ITermsDisambiguator {

	private SynonimsDictionary dictionary = new SynonimsDictionary();
	
	@Override
	public Map<String, Integer> getDisambiguatedTerms(List<Entry<String, Integer>> terms) 
	{
		Map<String, Integer> result = new HashMap<String, Integer>();
		
		for(Entry<String, Integer> e : terms)
		{
			String search = null;
			for(String res : result.keySet())
			{
				if
				(	// Raggruppa parole con stessa radice (stemmed words)
					StemmingUtils.SameRoot(e.getKey(), res)
				)
				{
					search = res;
				}
				else if
				(	// Raggruppa parole sinonime (fa controllo solo se non hanno stessa radice, per diminuire passaggi)
					dictionary.areSynonims(e.getKey(), res)
					
				)
				{
					search = res;
				}
			}
			
			// se ha trovato termini con cui raggruppare, somma i risultati
			if(search != null)
			{
				result.put(search, e.getValue() + result.get(search));
			}
			// altrimenti inserisci nuovo record nei risultati
			else
			{
				result.put(e.getKey(), e.getValue());
			}
		}
		
		return result;
	}
}