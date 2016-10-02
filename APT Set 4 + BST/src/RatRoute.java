import java.util.*;

public class RatRoute {
	// dimensions of the world
	private int myRows, myCols;
	
	// grid for keeping track of enclosure 
	// Each myGrid[i][j] should be 'X' if blocked and '.' otherwise
    private char[][] myGrid;
    
    // location of the goal/cheese
    private int myCheeseRow, myCheeseCol;

    /**
     * Returns the number of possible routes the rat can take to get to
     * the cheese without ever increasing the distance between itself
     * and the cheese at any point on its path
     * @param enc each entry represents one row of the maze
     */
    public int numRoutes(String[] enc) {
        myRows = enc.length;
        myCols = enc[0].length();
        myGrid = new char[myRows][myCols];
        int ratRow=0,ratCol=0;
        for(int r=0; r < myRows; r++){
            Arrays.fill(myGrid[r], '.');
            for(int c=0; c < myCols; c++){
                char ch = enc[r].charAt(c);
                if (ch == 'R') {
                	ratRow = r;
                	ratCol = c;
                }
                else if (ch == 'C') {
                	//set myCheeseRow & myCheeseColumn
                	myCheeseRow = r;
                	myCheeseCol = c;
                }
                else if (ch == 'X') {
                	myGrid[r][c] = 'X';
                }
            }
        }
        return numRoutes(ratRow, ratCol);
    }
    
    /**
     * Returns the number of rat-legal ways of getting from (row,col) to cheese
     */
    private int numRoutes(int row, int col){
        int sum = 0;
    	// Base cases
        // 1. out of bounds
    	if (row < 0 || row >= myRows || col < 0 || col >= myCols) return 0;
    	// 2. blocked
    	if (myGrid[row][col] == 'X') return 0;
    	// 3. reached cheese
    	if (row == myCheeseRow && col == myCheeseCol) return 1;
    	
    	if (myCheeseCol != col)
    		sum += numRoutes(row, col+(int)Math.signum(myCheeseCol - col));
    	if (myCheeseRow != row)
    		sum += numRoutes(row+(int)Math.signum(myCheeseRow - row), col);
        return sum;
    }
}
