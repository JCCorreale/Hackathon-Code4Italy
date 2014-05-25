package it.camera.hackathon.persistence;

import java.io.Writer;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

public class JSONListSaver extends BaseWriter<List<?>> {

	public JSONListSaver(Writer writer) {
		super(writer);
	}
	
	public static JSONArray getJSONArray(List<?> list)
	{
		JSONArray array = new JSONArray();
		for (Object o : list)
		{
			array.put(o);
		}
		return array;
	}

	@Override
	public void save(List<?> list) {
		try {
			this.getJSONArray(list).write(super.getWriter());
		} catch (JSONException e) {
			throw new IllegalStateException("JSON Error");
		}
	}
}