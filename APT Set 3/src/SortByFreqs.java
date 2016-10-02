import java.util.*;

public class SortByFreqs {
	Map<String, Integer> myFreqs;
	
	class FreqCompare implements Comparator<String> {
		@Override
		// Tells the compiler that a method must be overriden or else it will return an error message
		public int compare(String o1, String o2) {
			// Todo Auto-generated method stub
			return myFreqs.get(o2) - myFreqs.get(o1);
		}
		
	}
	public String[] sort(String[] data) {
		myFreqs = new TreeMap<String, Integer>();
		for (String s: data){
			if (myFreqs.containsKey(s))
				// if we've seen this word before
				myFreqs.put(s,  myFreqs.get(s) + 1);
			else
				// new word
				myFreqs.put(s, 1);
		}
		String[] result = myFreqs.keySet().toArray(new String[0]);
		Arrays.sort(result, new FreqCompare());
		return result;
	}
}
