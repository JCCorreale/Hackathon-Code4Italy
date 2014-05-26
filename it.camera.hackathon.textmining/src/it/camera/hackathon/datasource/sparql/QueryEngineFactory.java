package it.camera.hackathon.datasource.sparql;

import it.camera.hackathon.parsing.IParser;

// TODO
public class QueryEngineFactory 
{
	public static IQueryEngine<?> get(String type, String format) 
	{		
		// setup parser
		IParser<?, ?> parser = ParserFactory.get(type, format);
		
		// setup query engine
		switch(type) 
		{
			case "Atto":
			
			break;
			default:
				throw new UnsupportedOperationException();
		}
		return null;
	}
}
