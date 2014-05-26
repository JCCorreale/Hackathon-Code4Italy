package it.camera.hackathon;

import it.camera.hackathon.textmining.clustering.ICluster;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ClusterDescriptor {

	public ICluster cluster;
	public int id;
	public List<String> terms;
	public Map<Date, Integer> occurrences;
	public Set<Atto> atti;
	
	public ClusterDescriptor() {
		this.terms = new ArrayList<String>();
		this.occurrences = new HashMap<Date, Integer>();
		this.atti = new HashSet<Atto>();
	}
	
	public String getLabel()
	{
		StringBuilder sb = new StringBuilder();
		for (String term : this.terms)
		{
			sb.append(term + " ");
		}
		return sb.toString();
	}
	
	@Override
	public String toString()
	{
		return this.getLabel();
	}
}