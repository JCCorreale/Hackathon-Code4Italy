package it.camera.hackathon.parsing;

public interface IParser<TI, TO> {
	public TO parse(TI input);
}
