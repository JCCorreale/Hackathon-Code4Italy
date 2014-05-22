package it.camera.hackathon.datasource;

import java.io.IOException;
import java.io.InputStream;

public interface IStreamReceiver<T> {
	T receive(InputStream is) throws IOException;
}
