package it.camera.hackathon.parsing;

import java.io.IOException;
import java.io.InputStream;

public interface IParser<T> {
	T parse(InputStream is) throws IOException;
}
