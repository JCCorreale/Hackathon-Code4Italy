package it.camera.hackathon.textmining.clustering;

public class CosineDistanceStrategy implements IDistanceStrategy {

	private IDocumentCollection documents;
	
	public CosineDistanceStrategy(IDocumentCollection documents) {
		this.documents = documents;
	}

	@Override
	public float getDistance(IDocument d1, IDocument d2) {
		return this.documents.getTFIDFCosine(d1, d2); // TODO Cablato TFIDF
	}
}