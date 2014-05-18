package it.camera.hackathon;

import java.util.List;
import java.util.Map;

import it.camera.hackathon.datasource.IDataProvider;
import it.camera.hackathon.textmining.clustering.IDocument;
import it.camera.hackathon.textmining.clustering.ITerm;
import it.camera.opendata.model.Atto;

public interface IDocumentAnalyser extends IDataProvider<Map<Atto, List<ITerm>>, Iterable<IDocument>> {
	
}
