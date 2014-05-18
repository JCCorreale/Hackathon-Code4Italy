package it.camera.hackathon;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
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
	
	public static void printMap(Iterable<Entry<?, ?>> entries)
	{
		for (Entry<?, ?> entry : entries)
		{
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}
	
	public static void printMap(Map<?, ?> map)
	{
		for (Object key : map.keySet())
		{
			System.out.println(key + " " + map.get(key));
		}
	}
}