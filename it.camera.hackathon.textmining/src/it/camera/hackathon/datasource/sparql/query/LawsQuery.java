package it.camera.hackathon.datasource.sparql.query;

import it.camera.hackathon.datasource.sparql.IQuery;

public class LawsQuery implements IQuery {
	public String toString() {
		return 	"SELECT DISTINCT ?atto " +
				"WHERE { " +
				"?s <http://dati.camera.it/ocd/lavoriPreparatori> ?o; a ocd:legge. ?o ocd:rif_attoCamera ?atto. " + 
				"?atto a ocd:atto; ocd:rif_versioneTestoAtto ?descrTestoAtto. " +
				"}";
	}
}
