package it.camera.hackathon.textmining.tests;

import it.camera.hackathon.datasource.IDataProvider;
import it.camera.hackathon.datasource.remote.HttpGetDataSource;
import it.camera.hackathon.datasource.remote.HttpGetDataSource.HttpGetRequestConfiguration;
import it.camera.hackathon.parsing.StringParser;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class HttpGetRequestTest {

	public static String URL_TARGET = "http://requestb.in/1iq0kt81";
	
	@Test
	public void test() {
		Map<String, String> headerConf = new HashMap<String, String>();
		Map<String, String> params = new HashMap<String, String>();

		headerConf.put("Content-Type", "text/plain");
		headerConf.put("Accept", "text/html");

		params.put("param1", "value1");
		params.put("param2", "value2");
		
		HttpGetRequestConfiguration conf = new HttpGetRequestConfiguration(params, headerConf);
		
		IDataProvider<String, HttpGetRequestConfiguration> ds = new HttpGetDataSource<String>(URL_TARGET, new StringParser());
		System.out.println("Received: '" + ds.getData(conf) + "'");
	}

}
