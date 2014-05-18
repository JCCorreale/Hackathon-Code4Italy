package it.camera.hackathon.textmining;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextFileDataSource implements IStringDataSource {
	
	private File file;
	
	public TextFileDataSource(File file) {
		this.file = file;
	}
	
	public TextFileDataSource(String filename) {
		this(new File(filename));
	}
	
	/**
	 * Returns null if any error occurs.
	 */
	@Override
	public String getData() {
		
		if (!file.exists())
			throw new IllegalStateException("the given file does not exist");
		
		Reader reader;
		
		try
		{
			reader = new BufferedReader(new FileReader(file));
			char[] buffer = new char[1];
			StringBuilder sb = new StringBuilder();
	
			while (reader.read(buffer) > 0)
			{
				sb.append(buffer[0]);
			}
			

			reader.close();

			return sb.toString();
		}
		catch (FileNotFoundException e)
		{
			return null;
		} 
		catch (IOException e) {
			return null;
		}
	}
}