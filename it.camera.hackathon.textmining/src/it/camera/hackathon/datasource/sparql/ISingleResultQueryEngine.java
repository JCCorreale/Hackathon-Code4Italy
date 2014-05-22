package it.camera.hackathon.datasource.sparql;

public interface ISingleResultQueryEngine<T> {
	T run(ISingleResultQuery query);
}
