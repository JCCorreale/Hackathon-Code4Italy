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

	public ActContentByDateQuery(ActContentByDateConfiguration conf) {
		this(conf.getActUrl(), conf.getActRevisionDate());
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
				"?descrTestoAtto dc:date " + actRevisionDate + ". " +
				"?descrTestoAtto <http://purl.org/dc/terms/isReferencedBy> ?finalContent. " +
				"}";
	}
	
	public class ActContentByDateConfiguration {
		private String actUrl;
		private String actRevisionDate;
		
		public String getActUrl() {
			return actUrl;
		}

		public String getActRevisionDate() {
			return actRevisionDate;
		}
		
		public ActContentByDateConfiguration(String actUrl, String actRevisionDate) {
			if(actUrl == null || actRevisionDate == null)
				throw new IllegalArgumentException();
			
			this.actUrl = actUrl;
			this.actRevisionDate = actRevisionDate;
		}
	}
}
