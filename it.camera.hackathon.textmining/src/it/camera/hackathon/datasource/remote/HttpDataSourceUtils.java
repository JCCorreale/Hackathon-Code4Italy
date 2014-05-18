package it.camera.hackathon.datasource.remote;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class HttpDataSourceUtils {
	// TODO Escape caratteri sensibili
	public static String getGetParamters(Map<String, String> map) {
		StringBuilder sb = new StringBuilder();
		
		if(map.isEmpty())
			return "";
		
		sb.append("?");
		
		Entry<String, String> entry = null;
		Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
		
		entry = iterator.next();
		sb.append(entry.getKey() + "=" + entry.getValue());
		
		while(iterator.hasNext()) {
			entry = iterator.next();
			sb.append("&" + entry.getKey() + "=" + entry.getValue());
		}
		
		return sb.toString();
	}
}
