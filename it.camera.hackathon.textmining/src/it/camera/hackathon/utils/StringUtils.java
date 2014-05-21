package it.camera.hackathon.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StringUtils 
{
	public static String readTextFile(String path) throws IOException 
	{
		return new String(Files.readAllBytes(Paths.get(path)), Charset.defaultCharset());
	}
	
	public static String[] textFileToStringArray(String path) throws IOException
	{
		return Files.readAllLines(Paths.get(path), Charset.defaultCharset()).toArray(new String[]{});
	}
	
	public static String[] concatArrays(String[] A, String[] B) 
	{
	   int aLen = A.length;
	   int bLen = B.length;
	   // creo nuovo array con dimensione somma delle dimensioni dei due array da concatenare
	   String[] result = new String[aLen + bLen];
	   System.arraycopy(A, 0, result, 0, aLen);
	   System.arraycopy(B, 0, result, aLen, bLen);
	   return result;
	}
}
