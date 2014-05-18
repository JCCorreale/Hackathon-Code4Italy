package it.camera.hackathon.datasource;

public interface IDataProvider<T, A> {
	/**
	 * Returns a data given an argument (options)
	 * @param args
	 * @return
	 */
	T getData(A args) throws IllegalArgumentException;
}
