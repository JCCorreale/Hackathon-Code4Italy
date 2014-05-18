package it.camera.hackathon;

import java.util.List;
import java.util.Map.Entry;

import it.camera.hackathon.datasource.IDataProvider;
import it.camera.hackathon.textmining.clustering.IDocument;

public interface ITextDocumentBuilder extends IDataProvider<IDocument, List<Entry<String, Integer>>> {

}
