// Name: Austin Hua (ah335)
// Course: CompSci 201
// Purpose: This class is used to create a Markov Model more efficiently by creating map structures that can be reused

import java.util.*;

public class MapMarkovModel extends MarkovModel{
    private boolean changed = true;
    private int oldK = -1;
    private Map<String, List<String>> nGramMap= new HashMap<String, List<String>>();
    private String seed;
    
    
    public MapMarkovModel() {
        myRandom = new Random(RANDOM_SEED);
    }
	
    @Override
    protected int readChars(Scanner s) {
        changed = true;
    	return super.readChars(s);
    }
    
    
	@Override
	  /**
     * Generates random text that is similar to the reference text (myString).
     * 
     * @param k order of n-gram       
     * @requires k > 0
     * @param numLetters number of characters to generate      
     * @return numLetters of randomly selected characters based on picking
     *         representative characters that follow each k characters
     */
    protected String makeNGram(int k, int numLetters) {
        StringBuilder build = new StringBuilder();
        // Checks if a new hashmap is needed
        if (oldK != k || changed) {
	        oldK = k;
        	// Pick a random starting index
	        int start = myRandom.nextInt(myString.length() - k + 1);
	        seed = myString.substring(start, start + k);
	        // Allow for wraparound 
	        String wrapAroundString = myString + myString.substring(0,k);
	        // Create a hashmap of all possible next characters
	        for (int i = 0; i < myString.length(); i++) {
	        	String key = wrapAroundString.substring(i, i + k);
	        	if (nGramMap.get(key) == null) {
	        		List<String> list = new ArrayList<String>();
	        		list.add(wrapAroundString.substring(i + 1, i + k + 1));
	        		nGramMap.put(key, list);
	        	}
	        	else {
	        		nGramMap.get(key).add(wrapAroundString.substring(i + 1, i + k + 1));
	        	}
	        }
        }
        changed = false;
        	
        // generate numLetters characters onto build
        for (int i = 0; i < numLetters; i++) {
        	List<String> list1 = nGramMap.get(seed);
            int pick = myRandom.nextInt(list1.size());
            char ch = list1.get(pick).charAt(list1.get(pick).length() - 1);
            build.append(ch);
            seed = seed.substring(1) + ch;
        }
        
        return build.toString();
	}
}
