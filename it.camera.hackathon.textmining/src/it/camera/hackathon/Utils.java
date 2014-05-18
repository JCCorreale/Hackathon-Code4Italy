package it.camera.hackathon;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map.Entry;

public final class Utils {

	private Utils()
	{
		
	}

	public static String readTextFile(String path, Charset encoding) throws IOException 
	{
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
	
	public static void printWordEntries(Iterable<Entry<String, Integer>> wordEntries)
	{
		for (Entry<String, Integer> entry : wordEntries)
		{
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}
}
