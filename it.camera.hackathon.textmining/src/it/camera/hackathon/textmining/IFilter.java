package it.camera.hackathon.textmining;

public interface IFilter<T> {
	boolean accept(T value);
}
