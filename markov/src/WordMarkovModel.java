/*  Name: Austin Hua (ah335)
	Course: CompSci 201
 	Purpose: This class does the same thing as MapMarkovModel but with WordNGrams instead of Strings
*/

import java.util.*;

public class WordMarkovModel extends AbstractModel{
	protected String myString;
	protected String[] myWords;
    protected Random myRandom;
    public static final int DEFAULT_COUNT = 100; // default # random letters generated
    public static int RANDOM_SEED = 1234; 
    private boolean changed = true;
    private int oldK = -1;
    private Map<WordNgram, List<WordNgram>> nGramMap= new TreeMap<WordNgram, List<WordNgram>>();
    private WordNgram seed;
	
    public WordMarkovModel() {
        myRandom = new Random(RANDOM_SEED);
    }
	
	public void initialize(Scanner s) {
        double start = System.currentTimeMillis();
        int count = readChars(s);
        double end = System.currentTimeMillis();
        double time = (end - start) / 1000.0;
        super.messageViews("#read: " + count + " chars in: " + time + " secs");
    }
	
    protected int readChars(Scanner s) {
        changed = true;
    	myString = s.useDelimiter("\\Z").next();
        s.close();
        return myString.length();
    }
	
    public void process(Object o) {
        String temp = (String) o;
        String[] nums = temp.split("\\s+");
        int k = Integer.parseInt(nums[0]);
        int numWords = DEFAULT_COUNT;
        if (nums.length > 1) {
            numWords = Integer.parseInt(nums[1]);
        }
        
        double stime = System.currentTimeMillis();
        String text = makeWordNGram(k, numWords);
        double etime = System.currentTimeMillis();
        double time = (etime - stime) / 1000.0;
        this.messageViews("time to generate: " + time);
        this.notifyViews(text);
        
    }
    
    protected String makeWordNGram(int k, int numWords) {
        String[] build = new String[numWords];
        // Checks if a new TreeMap is needed
        if (oldK != k || changed) {
	        oldK = k;
	        myWords = myString.split("\\s+");
        	// choose a random starting index
	        int start = myRandom.nextInt(myWords.length - k + 1);
	        seed = new WordNgram(myWords, start, k);
	        // Allow for wraparound 
	        String[] wrapAroundWords = new String[myWords.length + k];
	        for (int j = 0; j < wrapAroundWords.length; j ++) {
	        	wrapAroundWords[j] = myWords[j % (myWords.length)];
	        }
	        // Create a TreeMap of all possible next characters
	        for (int i = 0; i < myWords.length; i++) {
	        	WordNgram key = new WordNgram(wrapAroundWords, i, k);
	        	if (nGramMap.get(key) == null) {
	        		List<WordNgram> list = new ArrayList<WordNgram>();
	        		list.add(new WordNgram(wrapAroundWords, i + 1, k));
	        		nGramMap.put(key, list);
	        	}
	        	else {
	        		nGramMap.get(key).add(new WordNgram(wrapAroundWords, i + 1, k));
	        	}
	        }
        }
        changed = false;
        // generate numLetters characters onto build
        for (int i = 0; i < numWords; i++) {
        	List<WordNgram> list1 = nGramMap.get(seed);
            int pick = myRandom.nextInt(list1.size());
            String str = list1.get(pick).getWords()[list1.get(pick).getWords().length - 1];
            build[i] = str;
            for (int j = 0; j < seed.getWords().length - 1; j++) {
            	seed.set(j, seed.getWords()[j+1]); 
            }
            seed.set(seed.getWords().length - 1, build[i]);
        }
        return ArrayToString(build);
    }
     
	public String ArrayToString(String[] a) {
	  	String result = "";
		for (int k =0; k < a.length; k++)
	  		result += a[k] + " ";
		return result;
	}

}
