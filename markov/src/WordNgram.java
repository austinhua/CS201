/*  Name: Austin Hua (ah335)
	Course: CompSci 201
 	Purpose: This class encapsulates N words/strings so that 
 	the group of N words can be treated as a key in a map or an element in a set, 
 	or an item to be searched for in an array.
*/

public class WordNgram implements Comparable<WordNgram>{
    
    private String[] myWords;
    
    /*
     * Store the n words that begin at index start of array list as
     * the N words of this N-gram.
     * @param list contains at least n words beginning at index start
     * @start is the first of the N words to be stored in this N-gram
     * @n is the number of words to be stored (the n in this N-gram)
     */
    public WordNgram(String[] list, int start, int n) {
        myWords = new String[n];
        System.arraycopy(list, start, myWords, 0, n);
    }
    
    /**
     * Return value that meets criteria of compareTo conventions.
     * @param wg is the WordNgram to which this is compared
     * @return appropriate value less than zero, zero, or greater than zero
     */
    public int compareTo(WordNgram wg) {
    	for (int i = 0; i < myWords.length; i++) {
    		if (myWords[i].compareTo(wg.getWords()[i]) != 0) {
    			return myWords[i].compareTo(wg.getWords()[i]);
    		}
    	}
    	return 0;
    }
    
    public String[] getWords() {
    	return myWords;
    }
    
    //Sets the String at the given index to s
    public void set(int n, String s) {
    	myWords[n] = s;
    	
    }
    
    
    
    /**
     * Return true if this N-gram is the same as the parameter: all words the same.
     * @param o is the WordNgram to which this one is compared
     * @return true if o is equal to this N-gram
     */

    public boolean equals(Object o) {
    	if (this == o) // point to the same Object
    	return true;
    	if (o == null ||    // Nothing is equal to null
    	    getClass() != o.getClass()) // Different kinds of objects
    	  return false;
    	WordNgram wg = (WordNgram) o;
    	// Check if all the words are equal
    	for (int k = 0; k < myWords.length; k++)
    	  if (!myWords[k].equals(wg.myWords[k]))
    	    return false;
    	return true;
    	}
    
    /**
     * Returns a good value for this N-gram to be used in hashing.
     * @return value constructed from all N words in this N-gram
     */
    public int hashCode() {
        int sum = 0;
    	for (int i = 0; i < myWords.length; i++) {
    		sum += myWords[i].hashCode() * (int)Math.pow(19, i);
    	}
        return sum;
    }
}
