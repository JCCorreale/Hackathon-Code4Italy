package it.camera.hackathon.textmining;

public class CompoundFilter<T> implements IFilter<T> {

	private IFilter<T>[] innerFilters;
	
<<<<<<< HEAD:it.camera.hackathon.textmining/src/it/camera/hackathon/textmining/CompundFilter.java
	@SafeVarargs
	public CompundFilter(IFilter<T>... filters)
=======
	public CompoundFilter(IFilter<T>... filters)
>>>>>>> Added sooo many stuff :D:it.camera.hackathon.textmining/src/it/camera/hackathon/textmining/CompoundFilter.java
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
