package it.camera.hackathon.datasource.sparql;

import it.camera.hackathon.datasource.IDataProvider;
import it.camera.hackathon.datasource.sparql.query.ActContentByDateQuery.ActContentByDateConfiguration;

// TODO probabilmente inutile in quanto basta il SingleResultDataProvider per ora
public class VirtuosoActContentByDateDataProvider implements IDataProvider<String, ActContentByDateConfiguration> {

	@Override
	public String getData(ActContentByDateConfiguration args)
			throws IllegalArgumentException {
		
		return null;
	}
	
}
