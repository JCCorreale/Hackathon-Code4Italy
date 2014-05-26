package it.camera.hackathon.textmining.clustering;

public abstract class ICluster implements Iterable<IDocument>
{
	public int count()
	{
		int res = 0;
		for (@SuppressWarnings("unused") IDocument doc : this)
			res++;
		return res;
	}
}