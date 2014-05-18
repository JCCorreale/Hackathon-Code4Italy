package it.camera.hackathon;

import java.io.File;
import java.io.FileFilter;

public class HTMLDocumentFactory 
{
	public HTMLDocumentFactory() {
		
	}
	
	public String[] getFilePaths()
	{
		String[] paths;
		File htmlDir = new File("text" + File.separator + "html");
		File[] files = htmlDir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File f) {
				return f.isFile();
			}
		});
		
		paths = new String[files.length];
		
		for (int i = 0; i < files.length; i++)
			paths[i] = files[i].getPath();
		
		return paths;
	}
}