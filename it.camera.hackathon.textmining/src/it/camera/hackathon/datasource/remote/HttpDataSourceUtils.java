package it.camera.hackathon.datasource.remote;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class HttpDataSourceUtils {
	public static String getGetParamters(Map<String, String> map) {
		StringBuilder sb = new StringBuilder();
		
		if(map.isEmpty())
			return "";
		
		sb.append("?");
		
		Entry<String, String> entry = null;
		Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
		
		entry = iterator.next();
		try {
			sb.append(entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), "UTF-8"));
			
			while(iterator.hasNext()) {
				entry = iterator.next();
				sb.append("&" + entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), "UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
}
