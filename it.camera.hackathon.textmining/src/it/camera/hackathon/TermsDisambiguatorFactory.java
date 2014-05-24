package it.camera.hackathon;

import it.camera.hackathon.utils.MapUtils;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class TermsDisambiguatorFactory {

	private TermsDisambiguatorFactory() {
	}
	
	public static ITermsDisambiguator getDummyDisambiguator()
	{
		return new ITermsDisambiguator() {

			@Override
			public Map<String, Integer> getDisambiguatedTerms(
					List<Entry<String, Integer>> terms) {
				return MapUtils.entryListToMap(terms);
			}
			
		};
	}
	
	public static ITermsDisambiguator getSimpleDisambiguator()
	{
		return new SimpleTermsDisambiguator();
	}

	public static ITermsDisambiguator getOptimizedDisambiguator()
	{
		return new OptimizedTermsDisambiguator();
	}
}
