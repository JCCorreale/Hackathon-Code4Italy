package it.camera.hackathon.persistence;

import java.io.Writer;
import java.util.Collection;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

public class JSONCollectionSaver extends BaseWriter<Collection<?>> {

	public JSONCollectionSaver(Writer writer) {
		super(writer);
	}
	
	public static JSONArray getJSONArray(Collection<?> list)
	{
		JSONArray array = new JSONArray();
		for (Object o : list)
		{
			array.put(o);
		}
		return array;
	}

	@Override
	public void save(Collection<?> list) {
		try {
			getJSONArray(list).write(super.getWriter());
		} catch (JSONException e) {
			throw new IllegalStateException("JSON Error");
		}
	}
}