package it.camera.hackathon;

import it.camera.hackathon.persistence.JSONClusterDescriptorCollectionSaver;
import it.camera.hackathon.persistence.JSONClusterDescriptorSaver;
import it.camera.hackathon.utils.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONClusterDescriptorsPersister {

	private JSONClusterDescriptorsPersister() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Saves the cluster index as a single file and the series as multiple files.
	 */
	public static void save(Collection<ClusterDescriptor> clusters, String path)
	{
		String indexFileName = path + File.separator + "clusters-index.json";
		final String seriesKey = "series";
		final String xAxisLabelsKey = "xAxisLabels";
		
		FileUtils.clearDirectory(path);
		
		// saves the index
		try 
		{
			JSONClusterDescriptorCollectionSaver saver = new JSONClusterDescriptorCollectionSaver(new FileWriter(indexFileName));
			saver.save(clusters);
		} 
		catch (IOException e) {
			throw new IllegalStateException("IO Error");
		}
		
		try
		{
			for (ClusterDescriptor cluster : clusters)
			{
				List<Date> dates = new ArrayList<Date>(cluster.occurrences.keySet());
				Collections.sort(dates);
				
				JSONObject seriesObject = new JSONObject();
				seriesObject.put(seriesKey, JSONClusterDescriptorSaver.getSeriesJSONObject(cluster));
				seriesObject.put(xAxisLabelsKey, JSONClusterDescriptorSaver.getXAxisLabelsJSONArray(dates));
				FileWriter fw;
				seriesObject.write(fw = new FileWriter(path + File.separator + cluster.id + "-graph.json"));
				fw.flush();
				fw.close();
			}
		}
		catch (JSONException e) {
			throw new IllegalStateException("JSON Error");
		} 
		catch (IOException e) {
			throw new IllegalStateException("IO Error");
		}
	}
}