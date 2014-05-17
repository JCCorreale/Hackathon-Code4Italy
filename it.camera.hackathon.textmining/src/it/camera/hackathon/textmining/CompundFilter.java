package it.camera.hackathon.textmining;

public class CompundFilter<T> implements IFilter<T> {

	private IFilter<T>[] innerFilters;
	
	public CompundFilter(IFilter<T>... filters)
	{
		if (filters.length == 0)
			throw new IllegalArgumentException("Please give me a filter T_T");
		
		innerFilters = filters;
	}

	@Override
	public boolean accept(T value) {
		
		for(IFilter<T> filter : innerFilters)
		{
			if (!filter.accept(value))
				return false;
		}
		
		return true;
	}
}
