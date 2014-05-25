package it.camera.hackathon.persistence;

import it.camera.hackathon.Atto;
import it.camera.hackathon.ClusterDescriptor;
import it.camera.hackathon.textmining.clustering.ITerm;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONException;

public class JSONClusterDescriptorCollectionSaver extends BaseWriter<Collection<ClusterDescriptor>> {

	public JSONClusterDescriptorCollectionSaver(Writer writer) {
		super(writer);
		// TODO Auto-generated constructor stub
	}
	
	public static JSONArray getJSONArray(Collection<ClusterDescriptor> clusters)
	{
		JSONArray array = new JSONArray();
		for (ClusterDescriptor descr : clusters) {
			array.put(JSONClusterDescriptorSaver.getClusterJSONObject(descr));
		}
		return array;
	}

	@Override
	public void save(Collection<ClusterDescriptor> clusters) {
		try {
			getJSONArray(clusters).write(super.getWriter());
			super.getWriter().flush();
			super.getWriter().close();
		} catch (JSONException e) {
			throw new IllegalStateException("JSON Error");
		} catch (IOException e) {
			throw new IllegalStateException("IO Error");
		}
	}
}