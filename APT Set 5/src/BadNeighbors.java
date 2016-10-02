


public class BadNeighbors {
      public int maxDonations(int[] donations) {
    	  if(donations.length == 0) return 0;
    	  //first element
    	  int first = donations[0] + maxHelper(subArray(donations, 2, donations.length - 2));
    	  //last
    	  int last = donations[donations.length - 1] + maxHelper(subArray(donations, 1, donations.length - 3));
    	  //neither
    	  int neither = donations[1] + maxHelper(subArray(donations, 3, donations.length - 1));
    	  return Math.max(Math.max(first,last), neither);
      }
      
      public int maxHelper(int[] donations) {
    	  if(donations.length == 0) return 0;
    	  if(donations.length == 1) return donations[0];
    	  int first = donations[0] + maxHelper(subArray(donations, 2, donations.length - 1));
    	  int second = donations[1] + maxHelper(subArray(donations, 3, donations.length - 1));
    	  return Math.max(first, second);
      }
      
      public int[] subArray(int[] array, int start, int end) {
    	  if (start > end) return new int[0];
    	  int[] result = new int[end - start + 1];
    	  System.arraycopy(array, start, result, 0, result.length);
    	  return result;
      }
      
      
  }