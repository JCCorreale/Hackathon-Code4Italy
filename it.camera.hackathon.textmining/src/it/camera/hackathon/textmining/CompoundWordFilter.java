package it.camera.hackathon.textmining;

public class CompoundWordFilter extends CompoundFilter<String> implements IWordFilter {

	public CompoundWordFilter(IFilter<String>... filters)
	{
		super(filters);
	}
}