package it.camera.hackathon;

import it.camera.hackathon.persistence.JSONClusterDescriptorCollectionSaver;
import it.camera.hackathon.persistence.JSONClusterDescriptorSaver;
import it.camera.hackathon.utils.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
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
			JSONClusterDescriptorCollectionSaver saver = new JSONClusterDescriptorCollectionSaver(WritersFactory.newUTF8FileWriter(indexFileName));
			saver.save(clusters);
		}
		catch (IOException e) {
			throw new IllegalStateException("IO Error");
		}
		
		try {
			for (ClusterDescriptor cluster : clusters)
			{
				List<Date> dates = new ArrayList<Date>(cluster.occurrences.keySet());
				Collections.sort(dates);
				JSONObject seriesObject = new JSONObject();
				JSONObject singleSerieObject = JSONClusterDescriptorSaver.getSeriesJSONObject(cluster);
				JSONArray seriesArray = new JSONArray();
				seriesArray.put(singleSerieObject);
				seriesObject.put(seriesKey, seriesArray);
				seriesObject.put(xAxisLabelsKey, JSONClusterDescriptorSaver.getXAxisLabelsJSONArray(dates));
				Writer fw;
				seriesObject.write(fw = WritersFactory.newUTF8FileWriter(path + File.separator + cluster.id + "-graph.json"));
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