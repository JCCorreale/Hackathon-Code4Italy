package it.camera.hackathon.utils;

import java.io.IOException;
import java.io.OutputStream;

public class MultipleOutputStream extends OutputStream {

	private OutputStream[] streams;
	
	public MultipleOutputStream(OutputStream... streams) {
		this.streams = streams;
	}

	@Override
	public void write(int arg0) throws IOException {
		for (OutputStream stream : streams)
			stream.write(arg0);
	}
}