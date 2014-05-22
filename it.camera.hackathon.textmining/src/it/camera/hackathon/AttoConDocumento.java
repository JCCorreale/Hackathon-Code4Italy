package it.camera.hackathon;

import it.camera.hackathon.textmining.clustering.IDocument;

public class AttoConDocumento extends Atto
{
	private IDocument document;
	
	public AttoConDocumento(Atto atto, IDocument document) 
	{
		super(atto.getIRI());
		this.document = document;
	}
	
	public IDocument getDocument()
	{
		return this.document;
	}		
}