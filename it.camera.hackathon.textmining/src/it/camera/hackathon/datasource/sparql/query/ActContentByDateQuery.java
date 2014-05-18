package it.camera.hackathon.datasource.sparql.query;

import it.camera.hackathon.datasource.sparql.IQuery;

public class ActContentByDateQuery implements IQuery {
	private String actUrl;
	private String actRevisionDate;
	
	public String getActUrl() {
		return actUrl;
	}

	public String getActRevisionDate() {
		return actRevisionDate;
	}

	public ActContentByDateQuery(String url, String date) {
		actUrl = url;
		actRevisionDate = date;
	}
	
	@Override
	public String toString() {
		return  "SELECT DISTINCT ?finalContent " +
				"WHERE { " +
				"<" + actUrl + "> ocd:rif_versioneTestoAtto ?descrTestoAtto. " +
				"?descrTestoAtto dc:date <" + actUrl + ">. " +
				"?descrTestoAtto <http://purl.org/dc/terms/isReferencedBy> ?finalContent. " +
				"}";
	}
}
