package it.camera.hackathon.textmining.test;

import static org.junit.Assert.*;
import it.camera.hackathon.textmining.HtmlRemover;

import org.junit.Test;

public class HtmlRemoverTest 
{
	public static String TEST = "<tr id=\"sl_svn255_6\"><td class=\"source\"><span class=\"com\">Licensed under the Apache License, Version 2.0 (the \"License\");<br></span></td></tr>";
	
	@Test
	public void testText()
	{
		assertEquals(HtmlRemover.text(TEST), "Licensed under the Apache License, Version 2.0 (the \"License\");");
	}

}