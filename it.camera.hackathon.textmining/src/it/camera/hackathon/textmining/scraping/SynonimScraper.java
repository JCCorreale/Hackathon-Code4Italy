package it.camera.hackathon.textmining.scraping;

import java.util.ArrayList;
import java.util.List;

import com.jaunt.*;

public class SynonimScraper 
{
	public static List<String> FindSynonims(String s)
	{
		List<String> res = new ArrayList<String>(); 
		try
		{
			UserAgent userAgent = new UserAgent();
			userAgent.visit("http://trovami.altervista.org/it/sinonimi/"+s);
			
			for(Element el : userAgent.doc.findEach("<li>").findEach("<a>"))
			{
				res.add(el.innerText()); 
			}
		}
		catch(JauntException e)
		{             
			System.err.println(e);         
		}
		return res;
	}
	
	public static boolean areSynonims(String s1, String s2)
	{
		return FindSynonims(s1).contains(s2) && FindSynonims(s2).contains(s1);
	}
	
	public static void main(String[] args)
	{
		for(String ssss : FindSynonims("esordio"))
			System.out.println(ssss);
	}
}
