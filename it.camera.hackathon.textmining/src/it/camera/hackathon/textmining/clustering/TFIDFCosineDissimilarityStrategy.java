package it.camera.hackathon.textmining.clustering;

public class TFIDFCosineDissimilarityStrategy implements IDissimilarityStrategy {

	private IDocumentCollection documents;
	
	public TFIDFCosineDissimilarityStrategy(IDocumentCollection documents) {
		this.documents = documents;
	}

	@Override
	public float getDissimilarity(IDocument d1, IDocument d2) {
		// the cosine is a SIMILARITY function, we want a DISSIMILARITY function
		// TODO: the use of a similarity or dissimilarity function should be a parameter of the algorithm
		
		// returns 1 if the documents are orthogonal, 0 if they are parallel
		return 1 - Math.abs(this.documents.getTFIDFCosine(d1, d2));
	}
}