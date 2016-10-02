import java.io.*;
import java.util.*;

public class SimpleWordCount {
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner in = new Scanner(new File("test.txt"));
        in.useDelimiter("\\Z"); 
        
// 2 backslashes needed to represent backslash since one is used like \n
// \Z = end of file; reads entire file 
// \Z	The end of the input but for the final terminator, if any; \z	The end of the input
        String text = in.next();
        String[] words = text.split("\\s+");
// \s = space, \s+ = one or more spaces
// /S = non whitespace characters
        System.out.println("Total # words = " + words.length);

        for (int k = 0; k < words.length; k = k + 1) {
            System.out.println(words[k]);
        }
            
    }
}
