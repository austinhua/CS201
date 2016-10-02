
public class NewHouse {
	public int count(int[] x, int[] y){
		int count = 0;
	
		final boolean[] RESET = {false, false, false, false};
		boolean[] dir = RESET;
		boolean valid;
		
		for (int i = -100; i <= 100; i++) {
			for (int j = -100; j <= 100; j++) {
				valid = true;
				for (int k = 0; k < x.length; k ++) {
					if (x[k] == i) {
						if (y[k] > j) dir[0] = true;
						else if (y[k] < j) dir[1] = true;
					}
					else if (y[k] == j) {
						if (x[k] < i) dir[2] = true;
						else if (x[k] > i) dir[3] = true;
					}
				}
				for (int l = 0; l < 4; l++){
					if (dir[l] == false) valid = false;
					dir[l] = false;//dir = RESET doesn't work
				}
				if (valid) count++;
				//dir = RESET;
			}
		}
		return count;
	}
	
}