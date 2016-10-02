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
        // todo compute ratRow and ratCol
        int ratRow=0,ratCol=0;
        for(int r=0; r < myRows; r++){
            Arrays.fill(myGrid[r], '.');
            for(int c=0; c < myCols; c++){
                char ch = enc[r].charAt(c);
                // todo find rat, cheese, and blocks
                if (ch == 'R') {
                	ratRow = r;
                	ratCol = c;
                }
                else if (ch == 'C') {
                	//set myCheeseRow & myCheeseColumn
                }
                else if (ch == 'X') {
                	myGrid[r][c] = 'X';
                }
            }
        }
        // todo add appropriate call to numRoutes(int, int)
        return numRoutes(ratRow, ratCol);
    }
    
    /**
     * Returns the number of rat-legal ways of getting from (row,col) to cheese
     */
    private int numRoutes(int row, int col){
        int sum = 0;
    	// todo add base cases
    	if (row < 0 || row >= myRows || col < 0 || col >= myCols) return 0;
        // todo: complete routeCount
    	sum = numRoutes(row,col+(int)Math.signum(myCheeseCol - col));
        return 0;
    }
}
