package it.camera.hackathon.textmining.stemming;

public class StemmingUtils 
{
	public static String Stem(String s)
	{
		ItalianStemmer stemmer = new ItalianStemmer();
		stemmer.setCurrent(s);
		stemmer.stem();
		return stemmer.getCurrent();
	}
	
	public static boolean SameRoot(String s1, String s2)
	{
		return Stem(s1).equals(Stem(s2));
	}
}
