package it.camera.hackathon.textmining.scraping;

import it.camera.hackathon.stemming.StemmingUtils;

import java.util.ArrayList;
import java.util.List;

import com.jaunt.*;

public class SynonimScraper 
{
	private static String URL = "http://trovami.altervista.org/it/sinonimi/";
	private static String ERROR_MESSAGE = "Nessun sinonimo è stato trovato, controlla la corretta digitazione della parola e la lingua selezionata (Italiano).";

	public static List<String> FindSynonims(String s)
	{
		List<String> res = new ArrayList<String>(); 
		try
		{
			UserAgent userAgent = new UserAgent();
			userAgent.visit(URL+s);
			
			if(userAgent.doc.findFirst("<form>").nextNonDescendantNode().nextNonDescendantNode().nextNonDescendantNode().toString().equals(ERROR_MESSAGE))
			{
				return res;
			}
			
			for(Element el : userAgent.doc.findEach("<li>").findEach("<a>"))
			{
				if(!StemmingUtils.Stem(s).equals(StemmingUtils.Stem(el.innerText())))
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
		for(String s : FindSynonims("cretini"))
		{
			System.out.println(s);
		}
	}
}
