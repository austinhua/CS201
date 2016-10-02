import java.util.Set;
import java.util.TreeSet;

public class SimpleWordGame {
      public int points(String[] player, String[] dictionary) {
          int score = 0;
    	  Set<String> remembered = new TreeSet<String>();
          Set<String> dict = new TreeSet<String>();
          for (String s1: player)
        	  remembered.add(s1);
          for (String s2: dictionary)
        	  dict.add(s2);
          Set<String> correct = intersection(remembered, dict);
          for (String s3: correct)
        	  score += Math.pow(s3.length(), 2);
          return score;
      }
      
      public Set<String> intersection (Set<String> a, Set<String> b)
      {
          Set<String> results = new TreeSet<String>();
          results.addAll(a);
          results.retainAll(b);
          return results;
      }
  }