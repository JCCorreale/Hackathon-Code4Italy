package it.camera.hackathon.datasource.sparql.query;

import it.camera.hackathon.datasource.sparql.IQuery;
import it.camera.hackathon.datasource.sparql.IQueryEngine;

import java.util.Iterator;
import java.util.NoSuchElementException;

// TODO change name?
public class IterableQueryResult<T> implements Iterable<T> {

	public static int DEFAULT_LIMIT = 10;
	
	private IQuery query;
	private IQueryEngine<T> queryEngine;
	private int limit; // TODO meaningless?
	private int offset; // TODO meaningless?
	
	public IterableQueryResult(IQuery query, IQueryEngine<T> queryEngine) {
		this(query, queryEngine, DEFAULT_LIMIT, 0);
	}
	
	public IterableQueryResult(IQuery query, IQueryEngine<T> queryEngine, int limit) {
		this(query, queryEngine, limit, 0);
	}
	
	public IterableQueryResult(IQuery query, IQueryEngine<T> queryEngine, int limit, int offset) {
		if(query == null)
			throw new IllegalArgumentException();
		if(queryEngine == null)
			throw new IllegalArgumentException();
		if(limit < 1)
			throw new IllegalArgumentException();
		if(offset < 0)
			throw new IllegalArgumentException();
		
		this.query = query;
		this.queryEngine = queryEngine;
		this.limit = limit;
		this.offset = offset;
	}
	
	@Override
	public Iterator<T> iterator() {
		if(query instanceof ILimitedQuery) {
			ILimitedQuery lq = (ILimitedQuery) query;
			return new IterableQueryResultIterator<T>(query, queryEngine, lq.getLimit(), lq.getOffset());
		}
		
		return new IterableQueryResultIterator<T>(query, queryEngine, limit, offset);
	}

	
	public static class IterableQueryResultIterator<T> implements Iterator<T> {

		int limit;
		int offset;
		
		private IQuery query;
		private IQueryEngine<T> queryEngine;
		
		private Iterator<T> bufferIterator;
		private Iterable<T> buffer;
		
		public IterableQueryResultIterator(IQuery query, IQueryEngine<T> queryEngine) {
			this(query, queryEngine, DEFAULT_LIMIT, 0);
		}

		public IterableQueryResultIterator(IQuery query, IQueryEngine<T> queryEngine, int limit) {
			this(query, queryEngine, limit, 0);
		}

		public IterableQueryResultIterator(IQuery query, IQueryEngine<T> queryEngine, int limit, int offset) {
			if(query == null)
				throw new IllegalArgumentException();
			if(queryEngine == null)
				throw new IllegalArgumentException();
			if(limit < 1)
				throw new IllegalArgumentException();
			if(offset < 0)
				throw new IllegalArgumentException();
			
			this.query = query;
			this.queryEngine = queryEngine;
			this.limit = limit;
			this.offset = offset;
		}

		@Override
		public boolean hasNext() {
			if(bufferIterator != null && bufferIterator.hasNext())
				return true;
			
			pullData();
			
			return bufferIterator.hasNext();
			
		}

		@Override
		public T next() {
			if(hasNext())
				return bufferIterator.next();
			
			throw new NoSuchElementException();
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}
		
		private void pullData() {
			buffer = this.queryEngine.run(new LimitedQuery(query, limit, offset));
			bufferIterator = buffer.iterator();
			offset += limit;
		}
		
	}
}
