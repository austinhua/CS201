import java.awt.Color;
import java.util.Random;

import java.awt.Point;
import java.util.*;

import javax.swing.JOptionPane;

import princeton.*;

/**
 * Animates the results of opening sites in a percolation system
 * 
 * From Princeton COS 226, Kevin Wayne 
 * Modified by Owen Astrachan, January 2008
 * Modified by Jeff Forbes, October 2008
 */


public class PercolationVisualizer {
    public static int RANDOM_SEED = 1234; 
    public static Random ourRandom = new Random(RANDOM_SEED);
    public static List<Point> blockedSites = new ArrayList<>();
    
    
    /**
     * Draws a square of color c at (row,col) on a N*N grid
     */
    public static void draw(int row, int col, int N, Color c) {
       StdDraw.setPenColor(c);
       StdDraw.filledSquare(col + .5, N - row - .5, .45);
    }
    
	public static void main(String[] args) {
		// Animate 20 times a second if possible
		final int DEFAULT_DELAY = 1000 / 20; // in milliseconds
		String input = "20";   //default
	    if (args.length == 1)  // use command-line arguments for testing/grading
	    	input = args[0];
	    else
	    	input = JOptionPane.showInputDialog("Enter N", "20");
		int N = Integer.parseInt(input); // N-by-N lattice

		// set x- and y-scale
		StdDraw.setXscale(0, N);
		StdDraw.setYscale(0, N);
		// draw a black box
		StdDraw.setPenColor(Color.BLACK);
        StdDraw.filledSquare(N / 2.0, N / 2.0, N / 2.0);

		IPercolate perc = new PercolationDFS(N);
		//IPercolate perc = new PercolationUF(N, new QuickFind());
		//IPercolate perc = new PercolationUF(N, new QuickUWPC());
		for (int row = 0; row < N; row++)
			for (int col = 0; col < N; col++)
				blockedSites.add(new Point(row, col));
		Collections.shuffle(blockedSites, ourRandom);
		
		//repeatedly declare sites open, draw, & pause until the system percolates
		while (!perc.percolates()) {
			//draw grid and reset full sites to open
			drawGrid(perc, N);
			// wait DEFAULT_DELAY milliseconds and then display
			StdDraw.show(DEFAULT_DELAY);
			open(perc);
		}	
        // draw percolation system
		drawGrid(perc, N);
		StdDraw.show(DEFAULT_DELAY);
	}
	
	public static void open(IPercolate perc) {
		//Choose and open a random point
		Point pick = blockedSites.remove(0);
		perc.open(pick.x, pick.y);
	}
	
	public static void drawGrid(IPercolate perc, int N) {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++) {
				if (perc.isOpen(i, j)) 
					draw(i, j, N, StdDraw.WHITE);
				if (perc.isFull(i, j)) {
					draw(i, j, N, StdDraw.CYAN);
					perc.open(i, j);
				}
			}
	}
	

}
