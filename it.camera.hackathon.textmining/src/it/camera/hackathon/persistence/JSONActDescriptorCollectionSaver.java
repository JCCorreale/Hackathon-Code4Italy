package it.camera.hackathon.persistence;

import it.camera.hackathon.Atto;
import it.camera.hackathon.textmining.clustering.ITerm;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;

public class JSONActDescriptorCollectionSaver extends
		BaseWriter<Collection<Entry<Atto, List<ITerm>>>> {

	public JSONActDescriptorCollectionSaver(Writer writer) {
		super(writer);
	}
	
	public static JSONArray getJSONArray(Collection<Entry<Atto, List<ITerm>>> entries)
	{
		JSONArray array = new JSONArray();
		for (Entry<Atto, List<ITerm>> entry : entries) {
			array.put(JSONActDescriptorSaver.getJSONObject(entry));
		}
		return array;
	}

	@Override
	public void save(Collection<Entry<Atto, List<ITerm>>> entries) {
		try {
			getJSONArray(entries).write(super.getWriter());
			super.getWriter().flush();
			super.getWriter().close();
		} catch (JSONException e) {
			throw new IllegalStateException("JSON Error");
		} catch (IOException e) {
			throw new IllegalStateException("IO Error");
		}
	}
}