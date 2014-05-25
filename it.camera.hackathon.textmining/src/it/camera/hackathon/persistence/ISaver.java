package it.camera.hackathon.persistence;

public interface ISaver<T> {
	void save(T object);
}