package it.camera.hackathon.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Redirects only standard output.
 * @author JCC
 *
 */
public class LogFileAndConsoleOutputStream extends OutputStream {

	private MultipleOutputStream stream;
	
	public LogFileAndConsoleOutputStream(String logFilePath) {
		try {
			this.stream = new MultipleOutputStream(new FileOutputStream(logFilePath), System.out);
		} 
		catch (FileNotFoundException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	@Override
	public void write(int b) throws IOException {
		this.stream.write(b);
	}
}