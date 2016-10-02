import java.util.*;

public class TheBestName {
	public int weight(String name) {
		if (name.equals("JOHN")) {
			return Integer.MAX_VALUE;
		}
		int sum = 0;
		for (int k=0; k < name.length(); k++) {
			sum += name.charAt(k) - 'A' + 1;
		}
		return sum;
	}
	class APTComp implements Comparator<String> {
		  public int compare (String a, String b){
			  if (weight(b) - weight(a) != 0)
				  return weight(b) - weight(a);
			  return a.compareTo(b);
		}
	}
	public String[] sort(String[] names) {
		Arrays.sort(names, new APTComp());
		return names;
	}
}
