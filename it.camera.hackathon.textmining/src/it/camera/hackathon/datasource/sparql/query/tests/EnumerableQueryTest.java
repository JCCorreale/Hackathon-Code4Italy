package it.camera.hackathon.datasource.sparql.query.tests;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import it.camera.hackathon.datasource.sparql.query.IterableQueryResult;
import it.camera.hackathon.datasource.sparql.query.LimitedQuery;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EnumerableQueryTest {

	public static int VALUES_NUM = 35;
	public static String[] VALUES;
	
	public static DummyQueryEngine<String> queryEngine;
	public static IterableQueryResult<String> query;
	
	@BeforeClass
	public static void staticSetUp() {
		VALUES = new String[VALUES_NUM];
		
		for(int i = 0; i < VALUES_NUM; i++)
			VALUES[i] = i + "";
	}
	
	@Before
	public void setUp() throws Exception {
		queryEngine = new DummyQueryEngine<>();
		queryEngine.push(VALUES);
	}

	@Test
	public void test() {
		System.out.println("\nDUMMY QUERY TEST");
		query = new IterableQueryResult<>(new DummyQuery(), queryEngine);
		
		int i = 0;
		
		Iterator<String> iterator = query.iterator();
		while(iterator.hasNext()) {
			System.out.println("Received: " + iterator.next());
			i++;
		}
		
		assertEquals(VALUES_NUM, i);
		
		assertEquals(false, iterator.hasNext());
		
		try {
			iterator.next();
			fail();
		} catch(NoSuchElementException e) {
			
		}
	}

	public static int LIMIT = 5;
	public static int OFFSET = 10;
	
	@Test
	public void testLimitedQuery() {
		System.out.println("\nLIMITED QUERY TEST");
		query = new IterableQueryResult<>(new LimitedQuery(new DummyQuery(), LIMIT, OFFSET), queryEngine);
		
		int i = 0;
		
		Iterator<String> iterator = query.iterator();
		while(iterator.hasNext()) {
			System.out.println("Received: " + iterator.next());
			i++;
		}
		
		assertEquals(VALUES_NUM - OFFSET, i);
		
		assertEquals(false, iterator.hasNext());
		
		try {
			iterator.next();
			fail();
		} catch(NoSuchElementException e) {
			
		}
	}

}
