package it.camera.hackathon.datasource.sparql;

public interface IQueryEngine<T> {
	Iterable<T> run(IQuery query);
}
