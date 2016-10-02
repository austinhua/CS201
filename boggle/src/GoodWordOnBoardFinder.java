import java.util.List;
import java.util.ArrayList;


// Name: Austin Hua ah335
// Course: CS 201
// Purpose: Correctly checks if a given word is on the boggle board


public class GoodWordOnBoardFinder implements IWordOnBoardFinder {

    public List<BoardCell> cellsForWord(BoggleBoard board, String word) {
        List<BoardCell> myCells = new ArrayList<BoardCell>();
        for (int r=0; r < board.size(); r++) {
        	for (int c=0; c < board.size(); c++) {
        		if (helper(word, board, r, c, myCells, 0)) {
        			return myCells;
        		}
        	}
        }
        myCells.clear();
    	return myCells;
    }

    private boolean helper(String word, BoggleBoard board, int row, int col, List<BoardCell> myCells, int index) {
    	// check out of bounds
    	if (row >= board.size() || row < 0 || col >= board.size() || col < 0) return false;
    	// check index past end of string = all successful
    	if (index >= word.length()) return true;
    	
    	//create board cell
    	BoardCell curCell = new BoardCell(row, col);
 
    	// check cell already used
    	if (myCells.contains(curCell)) return false;

    	// check if characters match
    	// if so, add them to list; if not, backtrack
    	// normal case
    	if ((""+word.charAt(index)).equals(board.getFace(row, col))) 
    		myCells.add(curCell);
    	// special case with qu
    	else if (board.getFace(row, col).equals("qu") && word.length() >= index+2 && word.substring(index, index+2).equals("qu")) {			
    			myCells.add(curCell);
    			index++;
    	}
    	else return false;
    	
    	// check each adjacent cell
    	int[] rdelta = {-1,-1,-1, 0, 0, 1, 1, 1};
    	int[] cdelta = {-1, 0, 1,-1, 1,-1, 0, 1};
    	 for(int k=0; k < rdelta.length; k++){
    	 if (helper(word, board, row+rdelta[k], col+cdelta[k], myCells, index+1)) return true; 
    	}   	
    	
    	// undoes adding current BoardCell to list then backtracks
    	myCells.remove(curCell); 
    	return false;
    }
    
}
