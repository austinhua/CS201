import java.util.*;

public class SortedFreqs {
      public int[] freqs(String[] data) {
    	  Map<String, Integer> freqs = new TreeMap<String, Integer>();
    	  for (String s : data) {
    		  if (freqs.get(s) == null)
    			  freqs.put(s, 1);
    		  else
    			  freqs.put(s, freqs.get(s) + 1);
    	  }
    	  int[] x = new int[freqs.size()];
    	  int i = 0;
    	  for (String s : freqs.keySet()) {
    		  x[i] = freqs.get(s);
    		  i++;
    	  }
    	  return x;
      }
   }