package it.camera.hackathon.textmining;

public class CompoundCharFilter extends CompoundFilter<Character> implements ICharFilter {

	public CompoundCharFilter(IFilter<Character>... filters)
	{
		super(filters);
	}
}