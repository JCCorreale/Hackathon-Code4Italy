package it.camera.hackathon.textmining.clustering;

public class Concept implements IConcept
{
	private String name, description;
	
	public Concept(String name, String description) 
	{
		this.name = name;
		this.description = description;
	}
	
	public Concept(String name) 
	{
		this(name, "");
	}
	
	public Concept() 
	{
		this(null, "");
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getDescription() {
		return this.description;
	}
}
