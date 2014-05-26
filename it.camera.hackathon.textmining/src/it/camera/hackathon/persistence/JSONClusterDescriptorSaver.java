package it.camera.hackathon.persistence;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
	
	public static JSONArray getXAxisLabelsJSONArray(List<Date> dates)
	{
		JSONArray array = new JSONArray();
		// TODO Localize
		for (Date date : dates)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			array.put(new SimpleDateFormat("MM yyyy").format(date));
		}
		return array;
	}

	@Override
	public void save(ClusterDescriptor descr) {
		throw new UnsupportedOperationException();
	}
}