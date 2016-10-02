import java.util.Arrays;

import princeton.*;

/**
 * Simulate percolation thresholds for a grid-base system using depth-first-search,
 * aka 'flood-fill' techniques for determining if the top of a grid is connected
 * to the bottom of a grid.
 * <P>
 * Modified from the COS 226 Princeton code for use at Duke. The modifications
 * consist of supporting the <code>IPercolate</code> interface, renaming methods
 * and fields to be more consistent with Java/Duke standards and rewriting code
 * to reflect the DFS/flood-fill techniques used in discussion at Duke.
 * <P>
 * @author Kevin Wayne, wayne@cs.princeton.edu
 * @author Owen Astrachan, ola@cs.duke.edu
 * @author Jeff Forbes, forbes@cs.duke.edu
 */


public class PercolationDFS implements IPercolate {
	// instance variable for storing grid state
	public int[][] myGrid;
	public int mySize;
    /**
     * Initialize a grid so that all cells are blocked.
     * @param n is the size of the simulated (square) grid
     */
    public PercolationDFS(int n) {
    	mySize = n;
    	myGrid = new int[n][n];
    	for (int[] x: myGrid)
    		Arrays.fill(x, BLOCKED);

    }


	public void open(int i, int j) {
		if (isInvalid(i, j)) throw new IndexOutOfBoundsException("Out of Bounds");
		myGrid[i][j] = OPEN;
	}

	
	public boolean isOpen(int i, int j) {
		if (isInvalid(i, j)) throw new IndexOutOfBoundsException("Out of Bounds");
		return myGrid[i][j] == OPEN;
	}

	public boolean isFull(int i, int j) {
		if (isInvalid(i, j)) throw new IndexOutOfBoundsException("Out of Bounds");
		return myGrid[i][j] == FULL;
	}

    public boolean percolates() {
    	resetGrid();
    	for (int col = 0; col < mySize; col++)
        	dfs(0, col);
    	// Check bottom row
        for (int col = 0; col < mySize; col++)
        	if (isFull(mySize-1, col)) return true;
        return false;
    }

	
	public boolean isInvalid(int i, int j) {
		return (i < 0 || i >= mySize || j < 0 || j >= mySize);
	}
    
    /**
     * Private helper method to mark all cells that are open and reachable
     * from (row,col).
     * @param row is the row coordinate of the cell being checked/marked
     * @param col is the col coordinate of the cell being checked/marked
     */
    private void dfs(int row, int col) {
    	// Base Cases:
    	// Out of Bounds
    	if (isInvalid(row, col)) return;
    	// Either Blocked or Filled
    	if (!isOpen(row, col)) return; 
    	
    	myGrid[row][col] = FULL;
    	dfs(row+1, col); //Down
    	dfs(row, col+1); //Right
    	dfs(row, col-1); //Left
    	dfs(row-1, col); //Up
    }
    
	public void resetGrid() {
		for (int i = 0; i < mySize; i++)
			for (int j = 0; j < mySize; j++)
				if (isFull(i, j)) 
					open(i, j);		
	}


}
