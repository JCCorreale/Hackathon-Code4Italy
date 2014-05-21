package it.camera.hackathon.examples;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import it.camera.hackathon.AttoDocumentAnalyser;
import it.camera.hackathon.AttoPreProcessor;
import it.camera.hackathon.textmining.clustering.IDocument;
import it.camera.hackathon.textmining.clustering.ITerm;
import it.camera.opendata.model.Atto;

public class AttoMain 
{	
	public static final String IRI = "http://dati.camera.it/ocd/attocamera.rdf/ac16_44";
	
	public static void main(String[] args) 
	{
		Atto atto = new Atto(IRI);
		AttoPreProcessor attoProcessor = new AttoPreProcessor();
		
		Entry<Atto, IDocument> entry = attoProcessor.getData(atto);
		
		HashMap<Atto, IDocument> map = new HashMap<>();
		map.put(entry.getKey(), entry.getValue());
		
		AttoDocumentAnalyser analyser = new AttoDocumentAnalyser(2.5F, 5);
		Map<Atto, List<ITerm>> resMap = analyser.getData(map.entrySet());
		
		for(Entry<Atto, List<ITerm>> e : resMap.entrySet())
			System.out.println(e.getKey().getIRI() + " - " + e.getValue());
	}

}
