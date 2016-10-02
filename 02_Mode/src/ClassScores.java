import java.io.FileNotFoundException;
import java.io.File;
import java.lang.reflect.Array;
import java.util.*; // for Scanner and ArrayList

import javax.swing.JFileChooser;


public class ClassScores {
    // chooser allows users to select a file by navigating through
    // directories
    private static JFileChooser ourChooser = new JFileChooser(System
            .getProperties().getProperty("user.dir"));
    private static final int MAX_VALUE = 100;
    
    /**
     * Brings up chooser for user to select a file
     * @return Scanner for user selected file, null if file not found
     */
    public  Scanner getScanner(){
        
        int retval = ourChooser.showOpenDialog(null);
        
        if (retval == JFileChooser.APPROVE_OPTION){
            File f = ourChooser.getSelectedFile();	
            Scanner s;
            try {
                s = new Scanner(f);
            } catch (FileNotFoundException e) {
                return null;
            }
            return s;
	    }
        return null;
    }

    /**
     * Reads in all of the words in a file and prints each one out
     * one per line to the console.
     * @param in initialized Scanner for file
     */
    public void readAndPrintFile(Scanner in) {
    	//in.useDelimiter("\\Z"); 
    	while (in.hasNext()) {
         	System.out.println(in.next());      	
        }
    }

    /**
     * Returns the "mode" of a set of data points: the most common
     * value(s)
     * @param scores scores on each test papers in range [0,100]
     * @return mode of the set of scores. In the case of more than
     * one number, they should be returned in increasing order. 
     */
    public int[] findMode(int[] scores) {
        // Todo: complete findMode for an array
    	int[] counts = new int[MAX_VALUE +1];
    	int max_occur = 0;
    	// counts[k] is the number of times k occurs in scores
    	// compute the max in counts
    	for (int score: scores)
    	{
    		counts[score] += 1;
    		if (counts[score] > max_occur)
    			max_occur = counts[score];
    	}
    	int modes = 0;
    	for (int count: counts)
    		if (count == max_occur)
    			modes +=1;
    	int[] results = new int[modes];
    	// find all modes and put them in result
    	int j = 0;
    	for (int i=0; i < counts.length; i++) {
    		if (counts[i] == max_occur) {
    			results[j++] = i;
    		}
    	}
    	//Arrays.sort(results);
        return results;
    }

    /**
     * Returns the "mode" of a set of data points: the most common
     * value(s)
     * @param in legal (i.e., non-null) input where each token is a
     * integer
     * @return mode of the set of scores. In the case of more than
     * one number, they should be returned in increasing order. 
     */
    public int[] findMode(Scanner in) {
        // Todo: complete findMode for a file
    	ArrayList<Integer> a = new ArrayList<Integer>();
    	while (in.hasNextInt()){
    		a.add(in.nextInt());
    	}
    	// list has all integers from the file
    	int[] result = new int[a.size()];
    	int k = 0;
    	for (int b : a)
    		result[k++] = b;
        return result;
    }

    public int count(int[] scores, int val) {
    	int occur = 0;
    	for (int k: scores) {
    		if (k == val)
    			occur += 1;
    	}
    	return occur;
    }
	
    

    public static void main(String[] args) {
        int[] example = {88, 70, 65, 70, 88};
        ClassScores cs = new ClassScores();
        /*Scanner in = cs.getScanner();
        cs.readAndPrintFile(in);*/
        //int[] modes = cs.findMode(example);
        int[] modes = cs.findMode(cs.getScanner());
        //print out int[] modes
        
    }
}