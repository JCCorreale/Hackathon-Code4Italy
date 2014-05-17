package it.camera.hackathon.textmining;

public class SingleDelimiterCharFilter implements ICharFilter {

	private CharSequence _delims;
	
	public SingleDelimiterCharFilter(CharSequence delims) {
		_delims = delims;
	}
	
	@Override
	public boolean accept(Character value) {
		for(char c : _delims.toString().toCharArray()) {
			if(c == value)
				return false;
		}
		return true;
	}

}
