import java.util.*;

public class Dirsort {
     public String[] sort(String[] dirs) {
          Arrays.sort(dirs, new APTComp());
          return dirs;
     }
     
     public class APTComp implements Comparator<String> {
    	 public int compare(String a, String b) {
    		 String[] x = a.split("/");
    		 String[] y = b.split("/");
    		 if (x.length != y.length) return x.length - y.length;
    		 for (int i = 0; i < x.length; i++)
				 if (x[i].compareTo(y[i]) != 0)
					 return x[i].compareTo(y[i]);
    		 return 0;
    	 }
     }
      
  }
