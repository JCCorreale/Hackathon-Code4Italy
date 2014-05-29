package it.camera.hackathon;

import it.camera.hackathon.persistence.JSONActDescriptorCollectionSaver;
import it.camera.hackathon.persistence.JSONActDescriptorSaver;
import it.camera.hackathon.textmining.clustering.ITerm;
import it.camera.hackathon.utils.FileUtils;
import it.camera.hackathon.utils.MapUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class JSONAttiTopWordsPersister {
	
	public static final String attoTermsPrefix = "atto-terms-";
	public static final String jsonExtension = ".json";

	private JSONAttiTopWordsPersister() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Accepts only existing folders. Clears the directory every time is called.
	 * @param atti
	 * @param path
	 */
	public static void saveAsMultipleFiles(Map<Atto, List<ITerm>> atti, String path)
	{
		File dir = new File(path);
		if (!dir.exists() || !dir.isDirectory())
			throw new IllegalArgumentException("!dir.exists() || !dir.isDirectory()");
		
		FileUtils.clearDirectory(dir);
		
		try
		{
			int counter = 0;
			for (Entry<Atto, List<ITerm>> entry : atti.entrySet())
			{
	
				String filename = path + File.separator + attoTermsPrefix + counter + jsonExtension;
				JSONActDescriptorSaver saver = new JSONActDescriptorSaver(WritersFactory.newUTF8FileWriter(filename));
				saver.save(entry);
				
				counter++;
			}
		}
		catch (IOException e)
		{
			System.err.println(e);
		}
	}

	public static void saveAsSingleFile(Map<Atto, List<ITerm>> atti, String filename)
	{
		try
		{
			JSONActDescriptorCollectionSaver saver = new JSONActDescriptorCollectionSaver(WritersFactory.newUTF8FileWriter(filename));
			saver.save(MapUtils.mapToEntryList(atti));
		}
		catch (IOException e)
		{
			System.err.println(e);
		}
	}
}