package it.camera.hackathon.textmining;

public interface IWordCountResult {
	String[] getWords();
	int getCount(String word);
}

