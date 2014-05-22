package it.camera.hackathon.textmining.tests;

import static org.junit.Assert.*;

import it.camera.hackathon.datasource.remote.HttpGetDataSource;
import it.camera.hackathon.datasource.remote.HttpGetDataSource.HttpGetRequestConfiguration;
import it.camera.hackathon.parsing.StringReceiver;
import org.junit.Test;

public class HttpStringDataSourceTest {

	String TEST = "Raw data now!";
	String urlString = "http://pastebin.com/raw.php?i=VEXSFeaB";
	
	@Test
	public void test() {
		HttpGetDataSource<String> ds = null;
		ds = new HttpGetDataSource<String>(urlString, new StringReceiver());
		
		String actual = ds.getData(HttpGetRequestConfiguration.getDefault());
		
		assertEquals(TEST, actual);
	}

}
