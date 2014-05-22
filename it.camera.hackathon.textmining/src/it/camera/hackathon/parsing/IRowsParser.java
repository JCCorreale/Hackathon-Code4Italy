package it.camera.hackathon.parsing;

public interface IRowsParser<TI, TO> extends IParser<TI, Iterable<TO>> {
	public IParser<TI, TO> getRowParser();
}
