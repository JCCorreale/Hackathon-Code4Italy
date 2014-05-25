package it.camera.hackathon.persistence;

public interface IPersister<T> {

	ISaver<T> getSaver();
	
	ILoader<T> getLoader();
}
