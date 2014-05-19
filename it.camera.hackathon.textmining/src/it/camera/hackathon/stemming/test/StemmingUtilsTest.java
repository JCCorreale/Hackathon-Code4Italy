package it.camera.hackathon.stemming.test;

import static org.junit.Assert.*;
import it.camera.hackathon.stemming.StemmingUtils;

import org.junit.Test;

public class StemmingUtilsTest 
{
	public static String liquido = "liquido", liquida ="liquida", liquidita = "liquidità";
	public static String spesso = "spesso", speso ="speso";
	
	@Test
	public void testSameCategory1() 
	{
		assertTrue(StemmingUtils.SameRoot(liquido, liquida));
	}

	@Test
	public void testSameCategory2() 
	{
		assertTrue(StemmingUtils.SameRoot(liquido, liquidita));
	}
	
	@Test
	public void testSameCategory3() 
	{
		assertTrue(StemmingUtils.SameRoot(liquida, liquidita));
	}
	
	@Test
	public void testNotSameCategory1() 
	{
		assertFalse(StemmingUtils.SameRoot(liquido, spesso));
	}
	
	@Test
	public void testNotSameCategory2() 
	{
		assertFalse(StemmingUtils.SameRoot(spesso, speso));
	}
}
