package it.camera.hackathon.persistence.test;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import it.camera.hackathon.Atto;
import it.camera.hackathon.persistence.JSONActDescriptorSaver;
import it.camera.hackathon.textmining.clustering.ITerm;
import it.camera.hackathon.textmining.clustering.Term;

import org.junit.Test;

public class JSONActDescriptorSaverTest {

	private Entry<Atto, List<ITerm>> buildSampleEntry(String actIRI, String... terms)
	{
		List<ITerm> termsList = new ArrayList<ITerm>();
		for (String term : terms)
			termsList.add(new Term(term));
		
		return new AbstractMap.SimpleEntry(new Atto(actIRI), termsList);
	}
	
	@Test
	public void testWithStringWriter() {
		StringWriter sw = new StringWriter();
		JSONActDescriptorSaver saver = new JSONActDescriptorSaver(sw);
		
		Entry<Atto, List<ITerm>> sampleEntry = buildSampleEntry("anyIRI", "term1", "term2", "term3");
		sampleEntry.getKey().setLegislature("17");
		sampleEntry.getKey().setLabel("anyAtto");
		
		saver.save(sampleEntry);
		
		
		System.out.println(sw.toString());
	}

	@Test
	public void testWithFileStream() throws IOException {
		JSONActDescriptorSaver saver = new JSONActDescriptorSaver(new FileWriter("output/tests/json/actDescriptorSaverTest.json"));
		
		Entry<Atto, List<ITerm>> sampleEntry = buildSampleEntry("anyIRI", "term1", "term2", "term3");
		sampleEntry.getKey().setLegislature("17");
		sampleEntry.getKey().setLabel("anyAtto");
		
		saver.save(sampleEntry);
		
		
	}
}
