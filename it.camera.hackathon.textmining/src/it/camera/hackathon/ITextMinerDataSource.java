package it.camera.hackathon;

import java.util.List;
import java.util.Map;

import it.camera.hackathon.datasource.IDataSource;
import it.camera.hackathon.textmining.clustering.ITerm;

public interface ITextMinerDataSource extends IDataSource<Map<Atto, List<ITerm>>> {
	
}
