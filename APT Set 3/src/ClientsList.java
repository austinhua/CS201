import java.util.*;

public class ClientsList {
	public String[] dataCleanup(String[] names) {
		clean(names);
		Arrays.sort(names, new APTComp());
		return names;
	}
    
	class APTComp implements Comparator<String> {
		public int compare(String a, String b) {
			String[] x1 = a.split("\\s+");
			String x = x1[1] + x1[0];
			String[] y1 = b.split("\\s+");
			String y = y1[1] + y1[0];
			return x.compareTo(y);
		}
	}
	
	public static void clean(String[] names) {
		for (int i = 0; i < names.length; i++) {
			if (names[i].indexOf(',') >= 0) {
				String str = names[i].substring(names[i].indexOf(',') + 2) + " " + names[i].substring(0, names[i].indexOf(',')); 
				names[i] = str;
			}
		}
	}
}