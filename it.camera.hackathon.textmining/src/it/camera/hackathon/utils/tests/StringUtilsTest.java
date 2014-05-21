package it.camera.hackathon.utils.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.Charset;

import it.camera.hackathon.utils.StringUtils;

import org.junit.Test;

public class StringUtilsTest 
{
	@Test
	public void correctReadTest() 
	{
		try 
		{
			assertTrue(StringUtils.readTextFile("resources/text/PlainText", Charset.defaultCharset()).trim().startsWith("XVII LEGISLATURA"));
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
