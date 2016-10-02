import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

//Name: Austin Hua (ah335)
//Course: CompSci 201
//Purpose: The SimpleHuffProcessor class contains the methods necessary to compress/uncompress a file

public class SimpleHuffProcessor implements IHuffProcessor {
    
    private HuffViewer myViewer;
    private String[] encodings = new String[ALPH_SIZE + 1];
    private TreeNode myHuffTree;
    private int bitsSaved;
    
    public int compress(InputStream in, OutputStream out, boolean force) throws IOException {
    	// if force is false, checks to see if new file will be bigger than old file
    	if (!force) {
    		if (bitsSaved < 0) {
    			throw new IOException("Compression uses " + -bitsSaved + " more bits.\nUse force compression to compress.");
    		}
    	}
    	if (in == null || out == null)
    		return 0;
    	int count = 0;
    	// create bis and bos
    	BitInputStream bis = new BitInputStream(in);
        BitOutputStream bos = new BitOutputStream(out);
        int chunk;
        // write header to file
        count += writeHeader(bos);
        // reread from file to translate to compressed version, which it writes to new source
        while ((chunk = bis.readBits(BITS_PER_WORD)) != -1) {
        	char[] encoding = encodings[chunk].toCharArray();
        	for (int j = 0; j < encoding.length; j++) {
        		bos.writeBits(1, Character.getNumericValue(encoding[j]));
        	}
        	count += encoding.length;
        }
        // add pseudo-eof character
        String[] eof = encodings[encodings.length - 1].split("");
    	for (int j = 0; j < eof.length; j++) {
    		bos.writeBits(1, Integer.parseInt(eof[j]));
    	}
        count += eof.length;
        // flush remaining characters
        bos.flush();
        return count;
    }

    /**
     * Count the number of times each char/chunk
     * occurs, create the Huffman tree, and
     * build a map from char/chunk to encoding
     */
    public int preprocessCompress(InputStream in) throws IOException {
    	if (in == null)
    		return 0;
    	bitsSaved = 0;
		BitInputStream bis = new BitInputStream(in);
		int[] freqs = new int[ALPH_SIZE];
		int chunk;
		while (( chunk = bis.readBits(BITS_PER_WORD)) != -1)
			freqs[chunk]++;
		// from freqs create a Huffman tree
		myHuffTree = createTree(freqs);
		// build array from chunks to encodings from tree
		int treeBitSize = buildArrayMap(myHuffTree, "");
		// return number of bits saved by compression
		for (int i = 0; i < freqs.length; i++) {
			bitsSaved += freqs[i] * (BITS_PER_WORD - encodings[i].length()); // amount saved from encoding
		}
		bitsSaved -= (BITS_PER_INT + (BITS_PER_WORD + 1)); // subtract by bits required for Magic_Number and Pseudo_EOF
		bitsSaved -= treeBitSize; // subtract bits required for the tree in header
		return bitsSaved;
    }

    public void setViewer(HuffViewer viewer) {
        myViewer = viewer;
    }

    /** 
     * Uncompresses the file and writes the uncompressed version to a new file.
     */
    public int uncompress(InputStream in, OutputStream out) throws IOException {
    	int count = 0;
    	BitInputStream bis = new BitInputStream(in);
    	BitOutputStream bos = new BitOutputStream(out);
    	// Read Header
    	int magic = bis.readBits(BITS_PER_INT);
        if (magic != MAGIC_NUMBER){
            myViewer.showError("magic number not right");
        }
    	// Build huffTree from header
        TreeNode decoderTree = build(bis);
        TreeNode tnode = decoderTree;
    	// Begin Decompression
        while (true) {
        	
            int bits = bis.readBits(1);      
            if (bits == -1) { 
            	throw new IOException("error reading bits, no PSEUDO-EOF"); 
            }
            // use the zero/one value of the bit read
            // to traverse Huffman coding tree
            // if a leaf is reached, decode the character and print UNLESS
            // the character is pseudo-EOF, then decompression done

            if ( (bits & 1) == 0) tnode = tnode.myLeft; 
            else                  tnode = tnode.myRight;
            if (tnode.myLeft == null && tnode.myRight == null) { // at leaf node in tree
                if (tnode.myValue == PSEUDO_EOF) { // has pseudo-eof
                    break;
                    }   // out of while-loop
                else {
                    bos.writeBits(BITS_PER_WORD, tnode.myValue); // write-out character stored in leaf-node
                    count += BITS_PER_WORD;
                    tnode = decoderTree;  // start back at top
               	}
           	}
        }
        bos.flush();
    	return count;
        }
    
    private void showString(String s){
        myViewer.update(s);
    }
    public TreeNode build(BitInputStream bis) throws IOException {
        int bits = bis.readBits(1);
        if (bits == -1) {
        	throw new IOException("Nothing read"); // signal error, nothing read
        }
        if (bits == 0) {
            TreeNode root = new TreeNode(0,0,null,null);
            root.myLeft = build(bis);
            root.myRight = build(bis);
            return root;
        } 
        bits = bis.readBits(BITS_PER_WORD + 1);
        TreeNode root = new TreeNode(bits,0,null,null);
        return root;
   }
    
    /**
     * Creates a huffTree by using weighting from the list of frequencies.
     */
    public TreeNode createTree(int[] freqs) {
    	PriorityQueue<TreeNode> pq = new PriorityQueue<TreeNode>();
    	for(int i = 0; i < freqs.length; i++){
    		pq.add(new TreeNode(i, freqs[i], null, null));
    	}
    	pq.add(new TreeNode(PSEUDO_EOF, 1, null, null)); // pseudoEOF
    	while(pq.size() > 1) {
    		TreeNode left = pq.remove();
    		TreeNode right = pq.remove();
    		// all nonleaf TreeNode Values set to -1
    		pq.add(new TreeNode(-1, left.myWeight + right.myWeight, left, right)); 
    	}
    	return pq.remove();
    }
    
    
    /** 
     * Builds an array to map out the encodings from the huffTree. 
     * Returns the number of bits required for the tree in the header of a compressed file.
     */
    public int buildArrayMap(TreeNode curr, String path){
    	if (curr.myLeft == null && curr.myRight == null) { // leaf node
    		encodings[curr.myValue] = path;
    		return 1 + (BITS_PER_WORD + 1);
    	}
    	return 1 + buildArrayMap(curr.myLeft, path + "0") + 
    			buildArrayMap(curr.myRight, path + "1");
    }
    
    /**
     * Writes the header to the compressed file and returns the number of bits used for the header.
     */
    public int writeHeader(BitOutputStream bos) {
        int count = 0;
    	// write magic number to file
        bos.writeBits(BITS_PER_INT, MAGIC_NUMBER);
        count += BITS_PER_INT;
        // write tree to file
        count += writeTree(myHuffTree, bos);
        return count;
    }
    /** 
     * Helper recursive method for writeHeader that recurses through huffTree
     * and writes out the header.
     * Returns the number of bits required for the tree in the header.
     */
    public int writeTree(TreeNode curr, BitOutputStream bos) {
    	// curr is a leaf
    	if (curr.myLeft == null && curr.myRight == null) {
    		bos.writeBits(1, 1);
    		bos.writeBits(BITS_PER_WORD + 1, curr.myValue);
    		return 1 + (BITS_PER_WORD + 1);
    	}
    	
    	// curr is an intermediate node
    	bos.writeBits(1, 0);
    	
    	return 1 + writeTree(curr.myLeft, bos) + writeTree(curr.myRight, bos);
    }
    
}