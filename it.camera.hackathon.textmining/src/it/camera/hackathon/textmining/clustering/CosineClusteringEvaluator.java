package it.camera.hackathon.textmining.clustering;

/**
 * Calculates the clustering score as the sum of cosine similarities calculated between each document and the centroid of its cluster.
 * @author JCC
 *
 */
public class CosineClusteringEvaluator implements IClusteringEvaluator {

	private IDocumentCollection documents;
	
	public CosineClusteringEvaluator(IDocumentCollection documents) {
		this.documents = documents;
	}

	@Override
	public float getClusteringScore(IClustering clustering) {
		
		float sum = 0;
		
		for (ICluster c : clustering)
		{
			float[] centroid = this.documents.getDocumentsTFIDFCentroid(c);
			for (IDocument d : c)
			{
				float[] docArray = this.documents.getDocumentTFIDFVector(d);
				sum += ClusteringUtils.getCosine(docArray, docArray);
			}
		}
		
		return sum;
	}
}