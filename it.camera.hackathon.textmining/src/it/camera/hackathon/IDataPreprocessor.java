package it.camera.hackathon;

import it.camera.hackathon.datasource.IDataProvider;
import it.camera.hackathon.datasource.IDataSource;
import it.camera.hackathon.textmining.clustering.IDocument;

public interface IDataPreprocessor extends IDataProvider<IDocument, IDataSource<String>> {

}
