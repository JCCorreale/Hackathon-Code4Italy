package it.camera.hackathon.datasource.sparql;

public interface IRawResultQueryEngine<T> {
	T run(IQuery query);
}
