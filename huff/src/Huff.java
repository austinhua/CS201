
/**
 * Main/launch program for Huff assignment. Creates an instance of an IHuffProcessor
 * and through the GUI, methods of the IHuffProcessor are called to compress/decompress
 * bits read from a file and display the information on the GUI.
 * @author Austin Hua (ah335)
 * 
 */

// Name: Austin Hua (ah335)
// Course: CompSci 201
// Purpose: (above in documentation)

public class Huff {

    public static void main(String[] args){
        HuffViewer sv = new HuffViewer("Duke Compsci Huffing");
        IHuffProcessor proc = new SimpleHuffProcessor();
        sv.setModel(proc);    
    }
}
