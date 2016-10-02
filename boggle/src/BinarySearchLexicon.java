import java.util.*;

//Name: Austin Hua ah335
//Course: CS 201
//Purpose: Implementation of ILexicon using Collections.binarySearch and an ArrayList

public class BinarySearchLexicon implements ILexicon {

    private ArrayList<String> myWords;
    
    public BinarySearchLexicon() {
        myWords = new ArrayList<String>();
    }
    
    public void load(Scanner s) {
        myWords.clear();
        while (s.hasNext()){
            myWords.add(s.next().toLowerCase());
        }
        Collections.sort(myWords);
    }

    public void load(ArrayList<String> list) {
        myWords.clear();
        myWords.addAll(list);
        Collections.sort(myWords);
    }

    public LexStatus wordStatus(StringBuilder s) {
        return wordStatus(s.toString());
    }

    public LexStatus wordStatus(String s) {
        int pos = Collections.binarySearch(myWords, s);
    	if (pos >= 0) 
        	return LexStatus.WORD;
        if (pos > -myWords.size() - 1 && myWords.get(-pos - 1).startsWith(s)) 
        	return LexStatus.PREFIX;   
        return LexStatus.NOT_WORD;
    }

    public Iterator<String> iterator() {
        return myWords.iterator();
    }

    public int size() {
        return myWords.size();
    }

}
