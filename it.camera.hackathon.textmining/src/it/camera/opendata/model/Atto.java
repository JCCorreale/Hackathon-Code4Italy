package it.camera.opendata.model;

public class Atto {
	private String iri;
	
	public String getIRI() {
		return iri;
	}
	
	public Atto(String iri) {
		if(iri == null)
			throw new IllegalArgumentException();
		
		this.iri = iri;
	}
}
