package it.camera.hackathon;

import java.util.List;
import java.util.Map.Entry;

import it.camera.hackathon.datasource.IDataProvider;

/**
 * Analizzatore di testo aggregato. Ritorna statistiche riguardo le occorrenze di stringhe nel testo.
 */
public interface IWordAnalyser extends IDataProvider<List<Entry<String, Integer>>, String>{

}
