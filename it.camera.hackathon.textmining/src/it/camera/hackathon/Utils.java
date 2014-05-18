package it.camera.hackathon;

import it.camera.hackathon.textmining.clustering.ITerm;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;
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
	
	public static Map<String, Integer> entryListToMap(List<Entry<String, Integer>> list)
	{
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (Entry<String, Integer> entry : list)
		{
			map.put(entry.getKey(), entry.getValue());
		}
		return map;
	}
	
	public static List<Entry<String, Integer>> mapToEntryList(Map<String, Integer> map)
	{
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>();
		for (String key : map.keySet())
		{
			list.add(new AbstractMap.SimpleEntry<String, Integer>(key, map.get(key)));
		}
		return list;
	}
}