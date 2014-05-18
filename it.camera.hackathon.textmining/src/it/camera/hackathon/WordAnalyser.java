package it.camera.hackathon;

import java.util.List;
import java.util.Map.Entry;

public class WordAnalyser implements IWordAnalyser {

	@Override
	public List<Entry<String, Integer>> getData(String args)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	

	
	// WORD COUNTER only
//	@Override
//	public List<Entry<String, Integer>> getData(String plainText)
//			throws IllegalArgumentException {
//		System.out.println("Counting word occurences (may take a while!)...");
//		TextMiningWordCounter counter = buildWordCounter();
//		IWordCountResult result = counter.parse(plainText);
//		
//		List<Entry<String, Integer>> res = new ArrayList<>();
//		for(String s : result.getWords()) {
//			res.add(new AbstractMap.SimpleEntry<String, Integer>(s, result.getCount(s)));
//		}
//		
//		return res;
//	}

}
