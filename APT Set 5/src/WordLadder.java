public class WordLadder {
   
	// public String ladderExists(String[] words, String from, String to) {
   // 
   // }
    	// returns true iff the words idffer by just one character
    	public boolean oneAway(String s, String t) {
    		if (s.length() != t.length()){
    			return false;
    		}
    		// calculate letters in common
    		// is that number length -1 then true
    		int inCommon = 0;
    		for (int k=0; k < s.length(); k++)
    			if (s.charAt(k) == t.charAt(k))
    				inCommon += 1;
    		if (inCommon == s.length() -1) return true;
    		return false;
    	}
  }