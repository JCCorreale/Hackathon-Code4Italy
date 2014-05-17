package it.camera.hackathon.textmining.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import it.camera.hackathon.textmining.HttpDataSource;

import org.junit.Test;

public class HttpDataSourceTest {

	String TEST = "Raw data now!";
	String urlString = "http://pastebin.com/raw.php?i=VEXSFeaB";
	
	@Test
	public void test() {
		HttpDataSource ds = null;
		try {
			ds = new HttpDataSource((HttpURLConnection) new URL(urlString).openConnection());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String actual = ds.getData();
		assertEquals(TEST, actual);
	}

}
