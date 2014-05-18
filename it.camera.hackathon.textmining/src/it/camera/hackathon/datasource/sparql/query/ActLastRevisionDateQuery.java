package it.camera.hackathon.datasource.sparql.query;

import it.camera.hackathon.datasource.sparql.IQuery;

public class ActLastRevisionDateQuery implements IQuery {
	private String actUrl;
	
	public String getActUrl() {
		return actUrl;
	}

	public ActLastRevisionDateQuery(String url) {
		actUrl = url;
	}
	
	public String toString() {
		return 	"SELECT ( MAX(?dataTestoAtto) AS ?lastEdit ) " + 
				"WHERE { " +
				url + " ocd:rif_versioneTestoAtto ?descrTestoAtto. " +
				"?descrTestoAtto dc:date ?dataTestoAtto. " +
				"}";
}
