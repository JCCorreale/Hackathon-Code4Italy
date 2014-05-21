package it.camera.hackathon.utils.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.IOException;
import it.camera.hackathon.utils.StringUtils;

public class StringUtilsTest 
{
	@Test
	public void readTextFileTest() 
	{
		try 
		{
			assertTrue(StringUtils.readTextFile("stopwords/stopwords_domain").startsWith("comma"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	@Test
	public void readTextFileToStringArrayTest() 
	{
		try 
		{
			assertTrue(StringUtils.textFileToStringArray("stopwords/stopwords_domain")[0].equals("comma"));
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void concatStringArraysTest() 
	{
		String[] array1 = {"a", "b", "c", "d", "e"};
		String[] array2 = {"f", "g", "h", "i", "j"};
		String[] concat = StringUtils.concatArrays(array1, array2);
		assertEquals(concat.length, array1.length + array2.length);
		assertArrayEquals(concat,  new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"});
	}
}
