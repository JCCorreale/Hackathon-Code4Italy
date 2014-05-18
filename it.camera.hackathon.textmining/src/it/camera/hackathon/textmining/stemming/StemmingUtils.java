package it.camera.hackathon.textmining.stemming;

import org.tartarus.snowball.SnowballStemmer;
import org.tartarus.snowball.ext.italianStemmer;

public class StemmingUtils 
{
	public static String Stem(String s)
	{
		SnowballStemmer stemmer = (SnowballStemmer) new italianStemmer();
		stemmer.setCurrent(s);
		stemmer.stem();
		return stemmer.getCurrent();
	}
	
	public static boolean SameRoot(String s1, String s2)
	{
		return Stem(s1).equals(Stem(s2));
	}
}
