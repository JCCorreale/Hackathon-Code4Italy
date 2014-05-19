package it.camera.hackathon;

import it.camera.hackathon.textmining.clustering.ITerm;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map sortMapAscending(Map unsortMap) 
	{	 
		List list = new LinkedList(unsortMap.entrySet());

		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue())
                                       .compareTo(((Map.Entry) (o2)).getValue());
			}
		});
 
		// ricreo la mappa dalla lista (la LinkedHashMap preserva l'ordine d'inserimento)
		Map sortedMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) 
		{
			Map.Entry entry = (Map.Entry) it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map sortMapDescending(Map unsortMap) 
	{	 
		List list = new LinkedList(unsortMap.entrySet());

		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue())
                                       .compareTo(((Map.Entry) (o2)).getValue());
			}
		});
		
		Collections.reverse(list);
		
		// ricreo la mappa dalla lista (la LinkedHashMap preserva l'ordine d'inserimento)
		Map sortedMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) 
		{
			Map.Entry entry = (Map.Entry) it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
	
	public static class EntryValueComparator implements Comparator<Entry<?, ? extends Comparable>>
	{
		private boolean ascending;
		
		public EntryValueComparator(boolean ascending)
		{
			this.ascending = ascending;
		}
		
		public EntryValueComparator()
		{
			this(true);
		}
		
		@Override
		public int compare(Entry<?, ? extends Comparable> entry1, Entry<?, ? extends Comparable> entry2) {
			return entry1.getValue().compareTo(entry2.getValue()) * (ascending? 1 : -1);
		}
	}
}