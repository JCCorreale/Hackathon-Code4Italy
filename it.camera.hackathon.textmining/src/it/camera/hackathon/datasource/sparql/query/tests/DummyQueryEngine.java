package it.camera.hackathon.datasource.sparql.query.tests;

import java.util.ArrayList;
import java.util.List;

import it.camera.hackathon.datasource.sparql.IQuery;
import it.camera.hackathon.datasource.sparql.IQueryEngine;
import it.camera.hackathon.datasource.sparql.query.ILimitedQuery;

public class DummyQueryEngine<T> implements IQueryEngine<T> {

	private List<T> buffer = new ArrayList<>();
	
	public void push(T value) {
		buffer.add(value);
	}
	
	public void push(T[] values) {
		for(T value : values)
			push(value);
	}
	
	@Override
	public Iterable<T> run(IQuery query) {
		if(query instanceof ILimitedQuery) {
			ILimitedQuery lQuery = (ILimitedQuery) query;
			
			int min = lQuery.getOffset();
			if(buffer.size() < min)
				min = buffer.size();
			
			int max = lQuery.getOffset() + lQuery.getLimit();
			if(buffer.size() < max)
				max = buffer.size();
			
			return buffer.subList(min, max);
		}
		return buffer;
	}

}
