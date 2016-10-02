//Name: Austin Hua ah335
//Course: CS 201
//Purpose: Program to analyze implementations of different lexicons
import java.io.InputStream;
import java.util.*;

import javax.swing.ProgressMonitorInputStream;

public class BoggleTester {

    ArrayList<Integer> myBoardScores, myLexiconScores;
   
    private static final int MIN_WORD = 3;
    private static final int NUM_TRIALS = 1000;
    
    public BoggleTester(){
        myBoardScores = new ArrayList<Integer>();
        myLexiconScores = new ArrayList<Integer>();
    }
    
    public String wordTester(IAutoPlayer player, ILexicon lex, ArrayList<Integer> log, int count){
        BoggleBoardFactory.setRandom(new Random());
        log.clear();
        double start = System.currentTimeMillis();
        
        for(int k=0; k < count; k++){
            BoggleBoard board = BoggleBoardFactory.getBoard(4);
            
            player.findAllValidWords(board, lex, MIN_WORD);
            log.add(player.getScore());           
        }
               
        double end = System.currentTimeMillis();
        int max = Collections.max(log);
        if(lex instanceof CompressedTrieLexicon || lex instanceof BinarySearchLexicon)
        	return String.format("%s\t%s\t count: %d\tmax: %d\ttime: %f", 
                    player.getClass().getName(),
                    lex.getClass().getName(),count,max,(end-start)/1000.0);
        return String.format("%s\t%s\t\t count: %d\tmax: %d\ttime: %f", 
               player.getClass().getName(),
               lex.getClass().getName(),count,max,(end-start)/1000.0);
    }
    
    
   	public String maxBoard(int count) {
   		 IAutoPlayer player = new BoardFirstAutoPlayer();
   		 ILexicon lex = new TrieLexicon();
   		 InputStream is = lex.getClass().getResourceAsStream("/ospd3.txt");   
         ProgressMonitorInputStream pmis = StoppableReader.getMonitorableStream(is, "reading..."); 
         Scanner s = new Scanner(pmis);
         ArrayList<String> list = new ArrayList<String>();
         while (s.hasNext()){
            list.add(s.next().toLowerCase());
         }
   		 lex.load(list);
   		
   		 BoggleBoard maxBoard = null;
		 int maxScore = 0;
		 BoggleBoardFactory.setRandom(new Random());
		 
		 for(int k=0; k < count; k++){
		     BoggleBoard board = BoggleBoardFactory.getBoard(4);
		     player.findAllValidWords(board, lex, MIN_WORD);
		     		     
		     if (maxScore < player.getScore()){
		    	 maxScore = player.getScore();
		    	 maxBoard = board;
		     }
		 }    
		return maxBoard.toString() + "  " + maxScore;
	}
    
    public void doTests(ILexicon lex){
        IAutoPlayer ap1 = new LexiconFirstAutoPlayer(); 
        String result = wordTester(ap1,lex,myBoardScores,NUM_TRIALS);
        System.out.println(result);
        IAutoPlayer ap2 = new BoardFirstAutoPlayer();
        String result2 = wordTester(ap2,lex,myLexiconScores, NUM_TRIALS);
        System.out.println(result2);
        for(int k=0; k < NUM_TRIALS; k++) {
            if (!myBoardScores.get(k).equals(myLexiconScores.get(k))){
                System.out.println(k+"\t"+myBoardScores.get(k)+"\t"+myLexiconScores.get(k));
            }
        }
    }
    
    public void runTests(ILexicon lex, ArrayList<String> list){
        lex.load(list);
        doTests(lex);
       
    }
    
    public static void main(String[] args){

        /*ILexicon lex1 = new SimpleLexicon();
        ILexicon lex2 = new BinarySearchLexicon();
        ILexicon lex3 = new TrieLexicon();
        ILexicon lex4 = new CompressedTrieLexicon();
        ILexicon[] lexicons = {lex1, lex2, lex3, lex4};

        
        InputStream is = lex1.getClass().getResourceAsStream("/ospd3.txt");   
        ProgressMonitorInputStream pmis = StoppableReader.getMonitorableStream(is, "reading..."); 
        Scanner s = new Scanner(pmis);
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNext()){
            list.add(s.next().toLowerCase());
        }
        
        for(ILexicon lex : lexicons) {
	        BoggleTester bs = new BoggleTester();
	        bs.runTests(lex,list);
        }*/
    	BoggleTester bs = new BoggleTester();
    	System.out.println(bs.maxBoard(50000));
    }
}



