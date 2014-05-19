package it.camera.hackathon.stemming;

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
		return Stem(s1).equals(Stem(s2)) || Stem(s1).equals(s2) || s1.equals(Stem(s2));
	}
}
