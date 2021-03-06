package it.camera.hackathon.dictionary;

import it.camera.hackathon.stemming.StemmingUtils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SynonimsDictionary 
{
	private final static String definitionFile = "th_it/th_it_IT.dat";
	private static Map<String, List<String>> dictionary = new HashMap<String, List<String>>();
	
	public SynonimsDictionary()
	{
		try 
		{
			 BufferedReader defReader = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(definitionFile))));
			 String strLine, currentDefinition = null;
			 List<String> currentDefinitionSynonims = new ArrayList<String>();
			 
			 //Read File Line By Line
			 while ((strLine = defReader.readLine()) != null) 
			 {
				 // non considero le prime righe di intestazione (che non sono definizioni)
				 if(strLine.contains("|"))
				 {
					 // se la linea inizia con una lettera, allora � una definizione
					 if(strLine.matches("^[A-z].*$"))
					 {
						 // nuova definizione trovata -> solo ora posso salvare i dati della definizione precedente
						 // questo � dovuto al fatto che i sinonimi delle definizioni possono trovarsi su pi� linee
						 if(currentDefinition != null)
						 {
							 dictionary.put(currentDefinition, currentDefinitionSynonims);
						 }
						 
						 currentDefinition = strLine.split("\\|")[0];
						 // azzero le definizioni
						 currentDefinitionSynonims = new ArrayList<String>();
					 }
					 // altrimenti mi trovo nella parte dei sinonimi dell'ultima definizione trovata
					 else
					 {
						 String[] synonimsArray = strLine.split("\\|");
						 // tolgo le indicazioni tra parentesi, non essendo sinonimi
						 for(String s : synonimsArray)
						 {
							 if(!s.startsWith("("))
								 currentDefinitionSynonims.add(s);
						 }
					 }
				 }
			 }
			 
			 // Salvo l'ultima definizione rimasta alla fine del file (attualmente � 'zuppo')
			 dictionary.put(currentDefinition, currentDefinitionSynonims);
			 // Chiudo il BufferedReader
			 defReader.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param wordToInspect
	 * @param radix Indicates whether to perform a common-radix search.
	 * @return
	 */
	public List<String> getSynonims(String wordToInspect, boolean compareRoots)
	{
		// se trova la parola nel dizionario la restituisce
		if(dictionary.containsKey(wordToInspect))
		{
			return dictionary.get(wordToInspect);
		}
		// altrimenti cerca parole con radice comune (tipo plurali o verbi coniugati)
		else if (compareRoots)
		{
			for(String s : dictionary.keySet())
			{
				if(StemmingUtils.SameRoot(wordToInspect, s))
					return dictionary.get(s);
			}
		}
		
		// se non trova risultati restituisce lista vuota
		return new ArrayList<String>();
	}
	
	public List<String> getSynonims(String wordToInspect)
	{
		return getSynonims(wordToInspect, true);
	}
	
	public boolean areSynonims(String s1, String s2)
	{
		return getSynonims(s1).contains(s2) && getSynonims(s2).contains(s1);
	}

}
