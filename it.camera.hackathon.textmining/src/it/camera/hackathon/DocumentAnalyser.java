package it.camera.hackathon;

import it.camera.hackathon.textmining.clustering.IDocument;
import it.camera.hackathon.textmining.clustering.IDocumentCollection;
import it.camera.hackathon.textmining.clustering.ITerm;
import it.camera.opendata.model.Atto;

import java.util.List;
import java.util.Map;

public class DocumentAnalyser implements IDocumentAnalyser {

	private static IDocumentCollection buildDocumentCollection()
	{
		// TODO
		return null;
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