package it.camera.hackathon.textmining.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import it.camera.hackathon.datasource.remote.HttpGetDataSource;
import it.camera.hackathon.datasource.remote.HttpGetDataSource.HttpGetRequestConfiguration;
import it.camera.hackathon.parsing.StringParser;
import org.junit.Test;

public class HttpStringDataSourceTest {

	String TEST = "Raw data now!";
	String urlString = "http://pastebin.com/raw.php?i=VEXSFeaB";
	
	@Test
	public void test() {
		HttpGetDataSource<String> ds = null;
		ds = new HttpGetDataSource<String>(urlString, new StringParser());

		Map<String, String> params= new HashMap<String, String>();
		Map<String, String> header = new HashMap<String, String>();
		
		String actual = ds.getData(new HttpGetRequestConfiguration(params, header));
		
		assertEquals(TEST, actual);
	}

}
