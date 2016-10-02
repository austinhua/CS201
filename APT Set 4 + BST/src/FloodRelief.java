import java.awt.Point;


public class FloodRelief {
	public int[][] myGrid;
	private int pumps = 0;
	private int cellsToBePumped;
	private static final int DONE = 26;
	
	private Point findSpot(int level) {
		Point result = null;
		for (int i=0; i<myGrid.length; i++)
			for (int j=0; j<myGrid[0].length; j++)
				if (myGrid[i][j] == level) {
					result = new Point(i,j);
					return result;
				}
		return result;
	}
	public int minimumPumps(String[] heights) {
		myGrid = new int[heights.length][heights[0].length()];
		for (int k=0; k < heights.length; k++)
			for (int col=0; col < heights[k].length(); col++)
				myGrid[k][col] = (heights[k].charAt(col) - 'a');
		cellsToBePumped = myGrid.length * myGrid[0].length;
		for (int level = 0; level < 26; level++) {

			// find a spot at level
			Point start = findSpot(level);
			while (start != null) {
				// pump all water starting at spot
				// mark all places where pumped as DONE(=26)
				pump (start, level);
				pumps++;
				start = findSpot(level);
				if (cellsToBePumped == 0)
					break; //end for-loop
			}
			
		}
		return pumps;
	}
	/**
	 * Pump all the water that is reachable from start and is at a level which is >= level
	 * @return the number of cells pumped
	 */
	public void pump(Point start, int level) {
		// base cases:
		// 1. out of bounds
		if (start.x < 0 || start.x >= myGrid.length || start.y < 0 || start.y >= myGrid[0].length) return;
		// 2. already pumped
		if (myGrid[start.x][start.y] == DONE) return;
		// 3. cell is lower than level
		if (myGrid[start.x][start.y] < level) return;
		
		int newLevel = myGrid[start.x][start.y];
		//pump here
		myGrid[start.x][start.y] = DONE; 
		cellsToBePumped--;
		//pump my neighbors
//		if (start.x+1 < myGrid.length)
			pump(new Point(start.x+1, start.y), newLevel);
	//	if (start.x-1 >= 0)
			pump(new Point(start.x-1, start.y), newLevel);
		//if (start.y+1 < myGrid[0].length)
			pump(new Point(start.x, start.y+1), newLevel);
		//if (start.y-1 >= 0)
			pump(new Point(start.x, start.y-1), newLevel);
		
	}
	
	public static void main(String[] args) {
		FloodRelief a = new FloodRelief();
		String[] heights = {"cbabcbabc", "cbabcbabc", "cbabcbabc", "cbabcbabc"};
		a.minimumPumps(heights);
		
	}
	
}
