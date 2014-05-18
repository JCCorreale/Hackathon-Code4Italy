package it.camera.hackathon;

import it.camera.hackathon.textmining.clustering.ITerm;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public interface ITermsDisambiguator 
{
	public Map<String, Integer> getDisambiguatedTerms(List<Entry<String, Integer>> terms);
}