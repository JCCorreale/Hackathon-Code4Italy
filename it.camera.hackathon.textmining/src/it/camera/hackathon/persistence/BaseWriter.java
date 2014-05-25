package it.camera.hackathon.persistence;

import java.io.Writer;

public abstract class BaseWriter<T> implements ISaver<T> {
	
	private Writer writer;
	
	public BaseWriter(Writer writer) {
		this.writer = writer;
	}
	
	protected Writer getWriter()
	{
		return this.writer;
	}
}