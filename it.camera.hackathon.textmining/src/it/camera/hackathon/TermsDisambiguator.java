package it.camera.hackathon;

import it.camera.hackathon.textmining.stemming.StemmingUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class TermsDisambiguator implements ITermsDisambiguator 
{
	@Override
	public Map<String, Integer> getDisambiguatedTerms(List<Entry<String, Integer>> terms) 
	{
		Map<String, Integer> result = new HashMap<String, Integer>();
		for(Entry<String, Integer> e : terms)
		{
			String search = "";
			for(String res : result.keySet())
			{
				if(StemmingUtils.Stem(e.getKey()).equals(StemmingUtils.Stem(res)))
				{
					search = res;
				}
			}
			if(search!="")
			{
				result.put(search, e.getValue()+result.get(search));
			}
			else
			{
				result.put(e.getKey(), e.getValue());
			}
		}
		return result;
	}
}
