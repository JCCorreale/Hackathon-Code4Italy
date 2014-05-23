package it.camera.hackathon;

import java.util.Date;

/**
 * Proposed Act.
 */
public class Atto 
{
	private String iri;
	
	private String 	label;			// rdfs:label
	private String	identifier; 	// dc:identifier
	private String 	legislature;	// dc:title (of odc:rif_leg ?legislatura)
	private Date 	revision;		// dc:date (of ocd:rif_versioneTestoAtto ?versionAtto)
	private String 	contentUrl;		// <http://purl.org/dc/terms/isReferencedBy> (of ocd:rif_versioneTestoAtto ?versionAtto)
	
	public String getIRI() 
	{
		return iri;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLegislature() {
		return legislature;
	}

	public void setLegislature(String legislature) {
		this.legislature = legislature;
	}

	public Date getRevision() {
		return revision;
	}

	public void setRevision(Date revision) {
		this.revision = revision;
	}

	public String getContentUrl() {
		return contentUrl;
	}

	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
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