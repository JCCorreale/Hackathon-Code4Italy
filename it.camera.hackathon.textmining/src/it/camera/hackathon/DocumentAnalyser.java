package it.camera.hackathon;

import it.camera.hackathon.textmining.clustering.DocumentCollection;
import it.camera.hackathon.textmining.clustering.IDocument;
import it.camera.hackathon.textmining.clustering.IDocumentCollection;
import it.camera.hackathon.textmining.clustering.ITerm;
import it.camera.opendata.model.Atto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DocumentAnalyser implements IDocumentAnalyser {

	private static IDocumentCollection buildDocumentCollection(Iterable<IDocument> args)
	{
		List<IDocument> docs = new ArrayList<IDocument>();
		for (IDocument doc : args)
		{
			docs.add(doc);
		}
		return new DocumentCollection(docs.toArray(new IDocument[0]));
	}
	
	public DocumentAnalyser() 
	{
	}

	@Override
	public Map<Atto, List<ITerm>> getData(Iterable<IDocument> args) throws IllegalArgumentException 
	{
		// TODO
		return null;
	}
}