package it.camera.hackathon.textmining.clustering;

public interface IClusteringEvaluator {

	/**
	 * Returns a score representing the quality of this cluster according to the policy of the specific implementation.
	 * @param clustering
	 * @return
	 */
	public float getClusteringScore(IClustering clustering);
}