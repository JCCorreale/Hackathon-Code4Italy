package it.camera.hackathon.dictionary;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class SynonimsDictionary 
{
	private final static String definitionFile = "th_it/th_it_IT.dat";
	private Dictionary<String, List<String>> dictionary = new Hashtable<String, List<String>>();
	
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
	
	public List<String> getSynonims(String wordToInspect)
	{
		return dictionary.get(wordToInspect);
	}
	
	public static void main(String[] args)
	{
		SynonimsDictionary d = new SynonimsDictionary();
		for(String s : d.getSynonims("zuppo"))
		{
			System.out.println(s);
		}
	}
}
