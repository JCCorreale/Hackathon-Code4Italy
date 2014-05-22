package it.camera.hackathon.utils;

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
}
