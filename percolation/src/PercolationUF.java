/**
 * Simulate a system to see its Percolation Threshold, but use a UnionFind
 * implementation to determine whether simulation occurs. The main idea is that
 * initially all cells of a simulated grid are each part of their own set so
 * that there will be n^2 sets in an nxn simulated grid. Finding an open cell
 * will connect the cell being marked to its neighbors --- this means that the
 * set in which the open cell is 'found' will be unioned with the sets of each
 * neighboring cell. The union/find implementation supports the 'find' and
 * 'union' typical of UF algorithms.
 * <P>
 * 
 * @author Owen Astrachan
 * @author Jeff Forbes
 *
 */

public class PercolationUF implements IPercolate {
	private final int OUT_BOUNDS = -1;
	int[][] myGrid;
	int mySize;
	IUnionFind myUF;
	
	/**
	 * Constructs a Percolation object for a nxn grid that uses unionThing to
	 * store sets representing the cells and the top/source and bottom/sink
	 * virtual cells
	 */
	public PercolationUF(int n, IUnionFind unionThing) {
		myGrid = new int[n][n];
		mySize = n;
		myUF = unionThing;
		myUF.initialize(n*n + 2); //N^2 cells + top & bottom virtual cell
	}

	/**
	 * Return an index that uniquely identifies (row,col), typically an index
	 * based on row-major ordering of cells in a two-dimensional grid. However,
	 * if (row,col) is out-of-bounds, return OUT_BOUNDS.
	 */
	public int getIndex(int row, int col) {
		if (row < 0 || row >= mySize || col < 0 || col >= mySize) return OUT_BOUNDS;
		return (row * mySize + col);
	}

	public void open(int i, int j) {
		if (isInvalid(i, j)) throw new IndexOutOfBoundsException("Out of Bounds");
		//mark as open
		myGrid[i][j] = OPEN;
		//connect with all valid adjecent cells
		connect(i, j);
		//connect open top cells to top virtual cell
		if (i == 0) myUF.union(getIndex(i, j), mySize*mySize);
		//connect open bottom cells to bottom virtual cell
		if (i == mySize - 1) myUF.union(getIndex(i, j), mySize*mySize + 1); 
	}

	public boolean isOpen(int i, int j) {
		if (isInvalid(i, j)) throw new IndexOutOfBoundsException("Out of Bounds");
		return myGrid[i][j] == OPEN;
	}

	public boolean isFull(int i, int j) {
		if (isInvalid(i, j)) throw new IndexOutOfBoundsException("Out of Bounds");
		return myUF.connected(getIndex(i, j), mySize*mySize);
	}

	public boolean isInvalid(int i, int j) {
		return (i < 0 || i >= mySize || j < 0 || j >= mySize);
	}
	
	public boolean percolates() {
		return myUF.connected(mySize*mySize, mySize*mySize + 1);
	}

	/**
	 * Connect new site (row, col) to all adjacent open sites
	 */
	private void connect(int row, int col) {
		//union with 4 adjacent cells, doing checks for out of bounds and openness
		if (!isInvalid(row+1, col) && isOpen(row+1, col))
    		myUF.union(getIndex(row, col), getIndex(row+1, col)); //Down
    	
    	if (!isInvalid(row, col+1) && isOpen(row, col+1))
    		myUF.union(getIndex(row, col), getIndex(row, col+1)); //Right
    	
    	if (!isInvalid(row, col-1) && isOpen(row, col-1))
    		myUF.union(getIndex(row, col), getIndex(row, col-1)); //Left
    	
    	if (!isInvalid(row-1, col) && isOpen(row-1, col))
    		myUF.union(getIndex(row, col), getIndex(row-1, col)); //Up
	}

}
