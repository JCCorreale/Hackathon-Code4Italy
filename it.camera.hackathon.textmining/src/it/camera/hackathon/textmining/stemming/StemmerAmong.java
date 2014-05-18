package it.camera.hackathon.textmining.stemming;

import java.lang.reflect.Method;

public class StemmerAmong
{
    final int s_size; /* search string */
    final char[] s; /* search string */
    final int substring_i; /* index to longest matching substring */
    final int result; /* result of the lookup */
    final Method method; /* method to use if substring matches */
    final Stemmer methodobject; /* object to invoke method on */
	
    public StemmerAmong (String s, int substring_i, int result, String methodname, Stemmer methodobject) 
    {
        this.s_size = s.length();
        this.s = s.toCharArray();
        this.substring_i = substring_i;
		this.result = result;
		this.methodobject = methodobject;
		if (methodname.length() == 0)
		{
		    this.method = null;
		} 
		else 
		{
		    try 
		    {
				this.method = methodobject.getClass().
				getDeclaredMethod(methodname, new Class[0]);
		    } 
		    catch (NoSuchMethodException e) 
		    {
		    	throw new RuntimeException(e);
		    }
		}
    }
}
