import java.util.Arrays;


public class BSTcount {
	public static final long NOTSET = -1;
	private long[] myMemo;
	public long howMany(int[] vals) {
		myMemo = new long[vals.length + 1];
		Arrays.fill(myMemo, NOTSET);
		return howMany(vals.length);
	}
	
	public long howMany(int size) {
		if (myMemo[size] != NOTSET) return myMemo[size];
		if (size == 0) return 1;
		long trees = 0;
		for (int left = 0; left <= size -1; left++) {
			int right = size - left - 1;
			trees += howMany(left) * howMany(right);
		}
		myMemo[size] = trees;
		return trees;
	}
	
}
