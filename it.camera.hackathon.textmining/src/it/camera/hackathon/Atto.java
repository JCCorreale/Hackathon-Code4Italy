package it.camera.hackathon;

import java.util.Date;

/**
 * Proposed Act.
 */
public class Atto 
{
	private String iri;
	
	private String 	name;
	private int 	legislature;
	private Date 	revision;
	private String 	contentIrl;
	
	public String getIRI() 
	{
		return iri;
	}	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLegislature() {
		return legislature;
	}

	public void setLegislature(int legislature) {
		this.legislature = legislature;
	}

	public Date getRevision() {
		return revision;
	}

	public void setRevision(Date revision) {
		this.revision = revision;
	}

	public String getContentUrl() {
		return contentIrl;
	}

	public void setContentUrl(String contentUrl) {
		this.contentIrl = contentUrl;
	}
	
	public Atto(String iri) 
	{
		if(iri == null)
			throw new IllegalArgumentException();
		
		this.iri = iri;
	}
	
	public boolean equals(Object obj)
	{
		return obj instanceof Atto && ((Atto)obj).iri.equals(this.iri);
	}
	
	@Override
	public String toString()
	{
		return this.iri;
	}
}