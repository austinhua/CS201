
public class ClassScores {
	
    private static final int MAX_VALUE = 100;
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
    return results;
	}
}
