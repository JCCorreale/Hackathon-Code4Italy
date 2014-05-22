package it.camera.hackathon.parsing;

public class IdentityParser<T> implements IParser<T, T> {
	
	@Override
	public T parse(T input) {
		return input;
	}

}
