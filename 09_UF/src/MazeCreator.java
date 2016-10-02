import javax.swing.JOptionPane;

import princeton.StdDraw;

import java.awt.Point;
import java.util.*;

public class MazeCreator
{
    private int myWidth;

    private int myHeight;

    private LinkedList<Edge> myEdges; // List of interior edges

    private IUnionFind myUF;

    public static final int DELAY = 1000 / 20;

    public MazeCreator(int width, int height, IUnionFind uf)
    {
        // Todo: complete constructor so it initializes data and draws initial
        // grid
    	myWidth = width;
    	myHeight = height;
    	myEdges = new LinkedList<Edge>();
    	myUF = uf;
    	StdDraw.setXscale(-1, myWidth +1);
    	StdDraw.setYscale(-1, myHeight + 1);
    	createInnerEdges();
    	drawOuterEdges();
    	/*
    	while(!isDone())
    		step();
    	*/
    }

    public void step()
    {
        // Todo: complete step

        // Grab random edge

        // If the two cells (call them x and y) adjacent to this wall
        // are in different sets, remove the wall and perform a union
        // on x and y

        // Redraw as necessary

    }

    /*
     * Returns true if there is a path between any grid squares
     */
    public boolean isDone()
    {
        // Todo: complete isDone
        return true;
    }

    private void createInnerEdges()
    {
    	for(int i = 0; i < myWidth; i++) {
    		for(int j = 0; j < myHeight; j++) {
    			Edge aEdge = new Edge(i, j, true, myWidth, myHeight);
    			aEdge.draw(); 
    			if(i != 0)
    				myEdges.add(aEdge);
    			Edge bEdge = new Edge(i, j, false, myWidth, myHeight);
    			bEdge.draw();
    			if(j != 0)
    				myEdges.add(bEdge); 
    		}
    	}
    }

    private void drawOuterEdges()
    {
        // Todo complete drawOuterEdges
    	for (int i = 0; i < myWidth; i++) {
    		Edge aEdge = new Edge(1, myHeight, false, myWidth, myHeight);
    		aEdge.draw();
    	}
    	for (int i = 0; i < myHeight; i++) {
    		Edge aEdge = new Edge(myWidth, i, true, myWidth, myHeight); 
    		aEdge.draw();
    	}
    }

    public static void main(String[] args)
    {
        int gridSize = Integer.parseInt(JOptionPane.showInputDialog("Enter N",
                "10"));
        MazeCreator c = new MazeCreator(gridSize, gridSize, new QuickFind(
                gridSize * gridSize));
        while (!c.isDone())
            c.step();
    }

}
