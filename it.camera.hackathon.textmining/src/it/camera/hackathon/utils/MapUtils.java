package it.camera.hackathon.utils;

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

public final class MapUtils 
{	
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
	
	public static <TK, TV> Map<TK, TV> entryListToMap(List<Entry<TK, TV>> list)
	{
		Map<TK, TV> map = new HashMap<TK, TV>();
		for (Entry<TK, TV> entry : list)
		{
			map.put(entry.getKey(), entry.getValue());
		}
		return map;
	}
	
//	public static Map<String, Integer> entryListToMap(List<Entry<String, Integer>> list)
//	{
//		Map<String, Integer> map = new HashMap<String, Integer>();
//		for (Entry<String, Integer> entry : list)
//		{
//			map.put(entry.getKey(), entry.getValue());
//		}
//		return map;
//	}
	
	public static <TK, TV> List<Entry<TK, TV>> mapToEntryList(Map<TK, TV> map)
	{
		List<Entry<TK, TV>> list = new ArrayList<Entry<TK, TV>>();
		for (TK key : map.keySet())
		{
			list.add(new AbstractMap.SimpleEntry<TK, TV>(key, map.get(key)));
		}
		return list;
	}
	
//	public static List<Entry<String, Integer>> mapToEntryList(Map<String, Integer> map)
//	{
//		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>();
//		for (String key : map.keySet())
//		{
//			list.add(new AbstractMap.SimpleEntry<String, Integer>(key, map.get(key)));
//		}
//		return list;
//	}
	
	/**
	 * Sorts the map by key.
	 * @param unsortedMap
	 * @param ascending
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map sortMapByKey(Map unsortedMap, final boolean ascending) 
	{	 
		List list = new LinkedList(unsortedMap.entrySet());

		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getKey())
                                       .compareTo(((Map.Entry) (o2)).getKey()) * (ascending? 1 : -1);
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
	
	/**
	 * Sorts the map by value.
	 * @param unsortedMap
	 * @param ascending
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map sortMapByValue(Map unsortedMap, final boolean ascending) 
	{	 
		List list = new LinkedList(unsortedMap.entrySet());

		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue())
                                       .compareTo(((Map.Entry) (o2)).getValue()) * (ascending? 1 : -1);
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
	
	/**
	 * Returns the first occurence of a key related the given value, null if none is found.
	 * @param m
	 * @param value
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Object getKeyByValue(Map<?,?> m, Object value)
	{
		for (Entry e : m.entrySet())
		{
			if (e.getValue().equals(value))
				return e.getKey();
		}
		return null;
	}
	
	public static <TK, TV> Map<TV, TK> getValueKeyMap(Map<TK, TV> map)
	{
		Map<TV, TK> newMap = new HashMap<TV, TK>();
		for (Entry<TK, TV> entry : map.entrySet())
		{
			newMap.put(entry.getValue(), entry.getKey());
		}
		return newMap;
	}
}