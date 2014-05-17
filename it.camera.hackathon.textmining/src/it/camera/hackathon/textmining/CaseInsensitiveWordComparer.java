package it.camera.hackathon.textmining;

public class CaseInsensitiveWordComparer implements IWordComparer {

	@Override
	public boolean areEqualWords(String w1, String w2) {
		return w1.toLowerCase().equals(w2.toLowerCase());
	}

}
