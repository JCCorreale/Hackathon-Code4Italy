package it.camera.hackathon.textmining.clustering.tests;

import it.camera.hackathon.textmining.clustering.ICluster;
import it.camera.hackathon.textmining.clustering.IDocument;

import java.util.Iterator;

class DummyCluster implements ICluster
{
	private String name;
	
	public DummyCluster(String name)
	{
		this.name = name;
	}
	
	@Override
	public Iterator<IDocument> iterator() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public String toString()
	{
		return this.name;
	}

	@Override
	public int getDocumentsCount() {
		// TODO Auto-generated method stub
		return 0;
	}
}