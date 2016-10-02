import java.util.*;


/**
 * Name: Austin Hua ah335
 * Course: CompSci 201
 * Date: September 8, 2014
 * Purpose:
 *   Learn about sets by implementing some common set algorithms.
 */
public class SetAlgorithms
{
    /**
     * Create a set of words from the given input data.
     * 
     * @param input scanner for some input source
     * @return a set of the words in the input source
     */
    public Set<String> makeSet (Scanner input)
    {
        Set<String> results = new TreeSet<String>();

        while (input.hasNext())
        {
            results.add(input.next());
        }

        return results;
    }

    
    /**
     * Prints a given set to the Console window.
     * 
     * @param toPrint a set of words
     */
    public void printSet (Set<String> toPrint)
    {
        System.out.println("Number of items in set = " + toPrint.size());

        for (String o : toPrint)
        {
            System.out.print(o + " ");
        }

        System.out.println();
    }

    
    /**
     * Creates a new set that is the union of the given sets. 
     * The union of two sets is the set that contains all of the 
     * elements of both sets.
     *
     * @param a a set of words
     * @param b another set of words
     * @return union of sets a and b
     */
    public Set<String> union (Set<String> a, Set<String> b)
    {
        Set<String> results = new TreeSet<String>();
        
        results.addAll(a);
        results.addAll(b);

        return results;
    }


    /**
     * Creates a new set that is the intersection of the given sets.
     * The intersection of two sets is the set that contains only 
     * those elements that are contained in both sets. 
     *
     * @param a a set of words
     * @param b another set of words
     * @return intersection of sets a and b
     */
    public Set<String> intersection (Set<String> a, Set<String> b)
    {
        Set<String> results = new TreeSet<String>();
        results.addAll(a);
        results.retainAll(b);
        return results;
    }


    /**
     *  Creates a new set that is the difference of the given sets. 
     *  The difference of two sets is the set that contains only 
     *  those elements that are in the first set, but not the second 
     *  set.
     *
     * @param a a set of words
     * @param b another set of words
     * @return difference of sets a and b
     */
    public Set<String> difference (Set<String> a, Set<String> b)
    {
        Set<String> results = new TreeSet<String>();

        results.addAll(a);
        results.removeAll(b);
        return results;
    }


    /**
     *  Creates a new set that is the union of the given sets.
     * 
     * @param sets list of sets of words
     * @return union of all the given sets
     */
    public Set<String> union (List<Set<String>> sets)
    {
        Set<String> results = new TreeSet<String>();

        for (Set<String> set: sets) {
        	results.addAll(set);
        }
        return results;
    }


    /**
     *  Creates a new set that is the intersection of the given sets.
     * 
     * @param sets list of sets of words
     * @return intersection of all the given sets
     */
    public Set<String> intersection (List<Set<String>> sets)
    {
        Set<String> results = new TreeSet<String>();
        
        if (sets.size() < 1)
        	return results;
        
        Set<String> first = sets.get(0);
        results.addAll(first);
        for (int i = 1; i < sets.size(); i++) {
        	results.retainAll(sets.get(i));
        }
        return results;
    }
}
