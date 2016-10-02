import java.util.*;


public class AuntUncle {
   public String[] list(String[] parents, String target) {
       Set<String> result = new TreeSet<String>();
	   String[] targParents = findParents(parents, target);	   
	   List<String> grandparents = new ArrayList<String>();
	   
	   if (targParents != null) {
		   for(String targParent : targParents) {
			   String[] x = findParents(parents, targParent);
			   if (x != null) {
				   grandparents.add(x[0]);
				   grandparents.add(x[1]);
			   }
		   }
	   }
	   
	   for(String grandparent : grandparents) {
		   for (String s : parents) {
			   // Check if target is a parent of any children
			   String[] x = s.split("\\s");
			   if (x[0].equals(grandparent) || x[1].equals(grandparent)) 
				   if (!x[2].equals(targParents[0]) && !x[2].equals(targParents[1]) && !x[2].equals(target))
				   result.add(x[2]);
		   }
	   }
	  
	   return result.toArray(new String[0]);
   }
   
   public String[] findParents(String[] parents, String target) {
	   for (String s : parents) {
    	   String[] family = s.split("\\s");
    	   if (family[2].equals(target)) {
    		   String[] results = Arrays.copyOf(family, 2);
    		   return results;
    	   }
	   }
	   return null;
   }
   
   public static void main(String[] args) {
	   AuntUncle test = new AuntUncle();
	   String[] parents = {"A B C", "C D E", "D E F", "D E G", "H Z D"};
	   test.list(parents, "F");
   }
   
}