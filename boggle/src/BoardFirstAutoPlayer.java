import java.util.*;



public class BoardFirstAutoPlayer extends AbstractAutoPlayer {
    
    @Override
    public void findAllValidWords(BoggleBoard board, ILexicon lex, int minLength) {
    	clear();
    	
    	for (int r=0; r < board.size(); r++) {
        	for (int c=0; c < board.size(); c++) {
        		findOnBoard(board, r, c, new ArrayList<BoardCell>(), lex, minLength, "");
        	}
        }
    	
    }
    
    public void findOnBoard(BoggleBoard board, int row, int col, List<BoardCell> prevCells, ILexicon lex, int minLength, String s) {
        // check out of bounds
        if (row >= board.size() || row < 0 || col >= board.size() || col < 0) return;
    	
        //create board cell
        BoardCell curCell = new BoardCell(row, col);

        // check cell already used
        if (prevCells.contains(curCell)) return;
        else prevCells.add(curCell);

        // add current character to string
        s += board.getFace(row, col);
        
        // check if string is still prefix or a word
        LexStatus type = lex.wordStatus(s);
        if(type.equals(LexStatus.WORD) && s.length() >= minLength) {
        	add(s);
        }
        else if (type.equals(LexStatus.NOT_WORD)) {
            prevCells.remove(curCell);
        	return;
        }

        // check each adjacent cell
        int[] rdelta = {-1,-1,-1, 0, 0, 1, 1, 1};
        int[] cdelta = {-1, 0, 1,-1, 1,-1, 0, 1};
         for(int k=0; k < rdelta.length; k++){
            findOnBoard(board, row+rdelta[k], col+cdelta[k], prevCells, lex, minLength, s); 
        }

       prevCells.remove(curCell);       
    }
    
}
