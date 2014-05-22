package it.camera.hackathon.datasource.sparql.query;

import it.camera.hackathon.datasource.sparql.IQuery;

public class LimitedQuery implements ILimitedQuery {
	private int offset = 0;
	private int limit;
	private IQuery query;
	
	public int getOffset() {
		return offset;
	}
	public int getLimit() {
		return limit;
	}
	
	public LimitedQuery(IQuery query, int limit) {
		if(limit < 1) {
			throw new IllegalArgumentException();
		}
		
		this.query = query;
		this.limit = limit;
	}
	
	public LimitedQuery(IQuery query, int limit, int offset) {
		this(query, limit);
		this.offset = offset;
	}
	
	public LimitedQuery(IQuery query, LimitedQueryConfiguration cfg) {
		this(query, cfg.getLimit(), cfg.getOffset());
	}
	
	public String toString() {
		return 	query.toString() + 
				(offset == 0 ? "" : ("OFFSET " + offset + " " )) +
				"LIMIT " + limit;
	}

	public static class LimitedQueryConfiguration {
		private int limit;
		private int offset = 0;
		
		public int getLimit() {
			return limit;
		}
		
		public int getOffset() {
			return offset;
		}
		
		public LimitedQueryConfiguration(int limit) {
			if(limit < 1)
				throw new IllegalArgumentException();
			
			this.limit = limit;
		}
		
		public LimitedQueryConfiguration(int limit, int offset) {
			this(limit);
			this.offset = offset;
		}
	}
}
