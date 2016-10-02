import java.util.*;

/**
 * Name: Austin Hua
 * Course: CompSci 201
 * Problem: Recitation 3
 * Date: September 12, 2014
 * Purpose:
 *   Solve a problem by applying some set operations 
 */

public class Thesaurus
{
    /**
     * Converts the elements of a String to a set. The format
     * of a n-word String should be "word1 word2 word3... wordn"
     * That is, each word should be separated by exactly one space
     * and there should be no leading or trailing spaces. 
     * @param s words with individual spaces separating
     * words
     * @return elements of s as a Set
     */
     public Set<String> sToSet(String s)
     {
         Set<String> result = new TreeSet<String>();
         String[] words = s.split("\\s+");
         for (String str : words) {
        	 result.add(str);
         }
         return result;
     }
     
     /**
      * Converts the elements in a collection to a space-separated list.
      * That is, if the collection contains [A, B, C, D], the method
      * should return "A B C D". There should be no leading or trailing
      * spaces
      * 
      * @return the elements of elems as a space-separated String
      */
     public String collToS(Collection<String> elems)
     {
    	 String result = new String();
    	 for(String s : elems ) {
    		 result += s + " ";
    	 }
         return result.substring(0, result.length() - 1);
         }

     
     /**
      * Returns the number of elements contained in both sets.
      * The sets passed in should not be changed.
      * @param a a set of words
      * @param b another set of words
      * @return number of elements in common to a and b
      */
     public int numInCommon(Set<String> a, Set<String> b)
     {
	    Set<String> results = new TreeSet<String>();
	    results.addAll(a);
	    results.retainAll(b);
	    return results.size();
     }


     /**
      * Creates a new set that is the union of the given sets. 
      * The union of two sets is the set that contains all of the 
      * elements of both sets. The sets passed in should not
      * be changed.
      *
      * @param a a set of words
      * @param b another set of words
      * @return union of sets a and b
      */
     public Set<String> union(Set<String> a, Set<String> b)
    {
         Set<String> results = new TreeSet<String>();
         results.addAll(a);
         results.addAll(b);
         return results;
    }
 
    /**
     * Creates an edited version of  Thesaurus entries.
     * 
     * If any two entries have 2 or more words in common 
     * then they should be combined into a single entry.
     * The final Thesaurus must contain no pair of entries
     * that have 2 or more words in common. Each entry must
     * contain no duplicates. The words within each element 
     * of the returned value must be in alphabetical order,
     * and the elements must appear in alphabetical order
     *  
     * @param entry each element is a list of words that are all synonyms
     * @return edited version of Thesaurus entries
     */
    public String[] edit(String[] entry) {
        // Convert entries from array of Strings to ArrayList of Sets
        ArrayList<Set<String>> words = new ArrayList<Set<String>>();
        String[] merged;
    	for(String s : entry) {
    		Set<String> set = sToSet(s);
    		words.add(set);
        }
        // Keep merging entries until nothing gets merged
    	int n = words.size();
    	int oldSize = 0;
    	while(n != oldSize) {
	    	oldSize = n;
    		for(int i = 0; i < n; i++) {
	    		for(int j = 0; j < n; j++) {
	    			if(i != j && i < n && numInCommon(words.get(i), words.get(j)) >= 2) {
	    				words.get(i).addAll(words.get(j));
	    				words.remove(j);    				
	    				n = words.size();
	    			}
	    		}
	    	}
    	}
        // Convert list of Sets to an array of Strings
    	merged = new String[n];
    	for (int i = 0; i < n; i++) {
    		merged[i] = collToS(words.get(i));
    	}
        // Todo: Sort entries in alphabetical order
    	Arrays.sort(merged);
        return merged;
    }
    
    public static void printArray(Object[] a) {
		System.out.print("[");
	  	for (int k =0; k < a.length - 1; k++)
	  		System.out.print(a[k] + ", ");
	  	if (a.length > 0)
	  		System.out.print(a[a.length-1]);
			System.out.println("]");
	}
    
    public static void main(String[] args) {
        Thesaurus t = new Thesaurus();
        String[] input = {"ape monkey wrench", "wrench twist strain", "monkey twist frugue strain" };
        //System.out.println(t.edit( input));
        printArray(t.edit(input));
        // Todo: add more tests
    }
}
