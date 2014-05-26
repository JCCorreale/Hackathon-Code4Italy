package it.camera.hackathon.persistence;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import it.camera.hackathon.ClusterDescriptor;
import it.camera.hackathon.utils.MapUtils;

public class JSONClusterDescriptorSaver extends BaseWriter<ClusterDescriptor> {
	
	public JSONClusterDescriptorSaver(Writer writer) {
		super(writer);
	}

	public static final String idKey = "id";
	public static final String attoKey = "atti";
	public static final String termsKey = "terms";
	
	public static final String keyKey = "key";
	public static final String valuesKey = "values";
	
	public static JSONObject getClusterJSONObject(ClusterDescriptor descr)
	{
		JSONObject obj = new JSONObject();
		try 
		{
			obj.put(idKey, descr.id);
			obj.put(attoKey, JSONCollectionSaver.getJSONArray(descr.atti));
			obj.put(termsKey, JSONCollectionSaver.getJSONArray(descr.terms));
		} 
		catch (JSONException e) {
			throw new IllegalStateException("JSON Error");
		}
		
		return obj;
	}
	
	private static JSONArray getValuePairJSONArray(int counter, int occurences)
	{
		JSONArray array = new JSONArray();
		array.put(counter);
		array.put(occurences);
		return array;
	}
	
	private static JSONArray getValuesJSONArray(Map<Date, Integer> occurrences)
	{
		JSONArray array = new JSONArray();
		
		// sorts the occurences by date
		Map<Date, Integer> sortedOccurences = MapUtils.sortMapByKey(occurrences, true);
		
		int counter = 0;
		
		for (Entry<Date, Integer> entry : sortedOccurences.entrySet())
		{
			array.put(getValuePairJSONArray(counter, sortedOccurences.get(entry.getKey())));
			counter++;
		}
		
		return array;
	}
	
	public static JSONObject getSeriesJSONObject(ClusterDescriptor descr)
	{
		JSONObject obj = new JSONObject();
		try 
		{
			obj.put(keyKey, descr.getLabel());
			obj.put(valuesKey, getValuesJSONArray(descr.occurrences));
		} 
		catch (JSONException e) {
			throw new IllegalStateException("JSON Error");
		}
		
		return obj;
	}
	
	public static JSONArray getXAxisLabelsJSONArray()
	{
		JSONArray array = new JSONArray();
		array.put("Gen, Feb '13");
		array.put("Mar, Apr '13");
		array.put("May, Jun '13");
		array.put("Jul, Aug '13");
		array.put("Sep, Oct '13");
		array.put("Nov, Dec '13");
		return array;
	}

	@Override
	public void save(ClusterDescriptor descr) {
		throw new UnsupportedOperationException();
	}
}