package it.camera.hackathon;

import it.camera.hackathon.textmining.clustering.ITerm;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClusterDescriptor {

	private List<String> terms;
	private String label;
	private Map<Date, Integer> occurrences;
	
	
	public ClusterDescriptor() {
		this.terms = new ArrayList<String>();
		this.occurrences = new HashMap<Date, Integer>();
	}
}