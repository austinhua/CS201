import java.util.*;

public class Test {	
	static int[] xyz = {1, 3};
	
	static int global = 3;
	
	public static void main(String[] args) {
		int global = 4;
		p(global);
		HashMap<String, Integer>[] a = new HashMap[4];
		Map<String, Integer> x = new HashMap<String, Integer>();		
		Map<String, Integer> y = new TreeMap<String, Integer>();		
		a[0] = (HashMap)x;
		a[1] = (HashMap<String, Integer>)y;
	}

	public static void doStuff() {
		
	}
	
	public static <U, E> boolean contains(HashMap<U, E> mem, int U) {
		return mem.containsKey(U);
	}
	

	
	
	public static void printArray(Object[] a) {
		System.out.print("[");
	  	for (int k =0; k < a.length - 1; k++)
	  		System.out.print(a[k] + ", ");
	  	if (a.length > 0)
	  		System.out.print(a[a.length-1]);
			System.out.println("]");
	}
	
	public static void p(Object s) {
		System.out.println(s.toString());
	}
	
}
