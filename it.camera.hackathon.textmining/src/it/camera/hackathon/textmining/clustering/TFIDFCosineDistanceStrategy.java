package it.camera.hackathon.textmining.clustering;

public class TFIDFCosineDistanceStrategy implements IDistanceStrategy {

	private IDocumentCollection documents;
	
	public TFIDFCosineDistanceStrategy(IDocumentCollection documents) {
		this.documents = documents;
	}

	@Override
	public float getDistance(IDocument d1, IDocument d2) {
		// the cosine is a SIMILARITY function, we want a DISSIMILARITY (distance) function
		// TODO: the use of a proximity or distance function should be a parameter of the algorithm
		return - this.documents.getTFIDFCosine(d1, d2);
	}
}