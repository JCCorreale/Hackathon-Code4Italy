package it.camera.hackathon.textmining;

public class CompoundCharFilter extends CompoundFilter<Character> implements ICharFilter 
{
	@SafeVarargs
	public CompoundCharFilter(IFilter<Character>... filters)
	{
		super(filters);
	}
}