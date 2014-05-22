package it.camera.hackathon.datasource.sparql.query;

import it.camera.hackathon.datasource.sparql.IQuery;

public interface ILimitedQuery extends IQuery {
	public int getOffset();
	public int getLimit();
}
