package it.camera.hackathon.textmining;

import org.jsoup.Jsoup;

public class HtmlRemover 
{
    public static String text(String html) 
    {
        return Jsoup.parse(html).text();
    }
}
