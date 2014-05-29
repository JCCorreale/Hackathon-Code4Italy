package it.camera.hackathon;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

public class WritersFactory {

	private WritersFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public static Writer newUTF8FileWriter(String filename) throws FileNotFoundException
	{
		return new OutputStreamWriter(new FileOutputStream(filename), Charset.forName("UTF-8"));
	}
}
