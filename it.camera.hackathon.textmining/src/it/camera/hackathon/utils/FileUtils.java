package it.camera.hackathon.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {
	public static String readFile(String path, Charset encoding) throws IOException {
		System.out.println("Opening file: " + Paths.get(path).toAbsolutePath());
		byte[] encoded = Files.readAllBytes(Paths.get(path));
	    return new String(encoded, encoding);
	}
	
	public static void clearDirectory(File dir)
	{
		if (!dir.exists() || !dir.isDirectory())
			throw new IllegalArgumentException("!dir.exists() || !dir.isDirectory()");
		
		// clears the directory
		for (File f : dir.listFiles(new FileFilter() {

			@Override
			public boolean accept(File arg0) {
				return arg0.isFile();
			}
			
		}))
		{
			f.delete();
		}
	}
	
	public static void clearDirectory(String path)
	{
		clearDirectory(new File(path));
	}
}