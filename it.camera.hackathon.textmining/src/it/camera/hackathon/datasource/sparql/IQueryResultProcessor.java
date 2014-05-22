package it.camera.hackathon.datasource.sparql;

import it.camera.hackathon.parsing.IParser;

public interface IQueryResultProcessor<TI, TO> {
	public TO[] run(IQuery query);
	public IParser<TI, TO> getResultParser();
}
