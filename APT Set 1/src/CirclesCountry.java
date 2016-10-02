

public class CirclesCountry {
	public int leastBorders(int[] x, int[] y, int[] r, int x1, int y1, int x2, int y2) {
		int minBorders = 0;
		for (int i = 0; i < x.length; i++) {
			if (isInCircle(x1, y1, x[i], y[i], r[i]) != isInCircle(x2, y2, x[i], y[i], r[i]))
				minBorders++;
		}
	return minBorders;
	}
	
	public boolean isInCircle(int x, int y, int circleX, int circleY, int radius) {
		return radius > Math.sqrt(Math.pow(x-circleX, 2) + Math.pow(y-circleY, 2));
	}
}
