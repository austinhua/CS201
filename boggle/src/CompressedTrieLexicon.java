//Name: Austin Hua ah335
//Course: CS 201
//Purpose: Compressed version of TrieLexicon

import java.util.*;


public class CompressedTrieLexicon extends TrieLexicon {

	@Override
	public void load(ArrayList<String> list) {
		super.load(list);
		compress();
	}

	public void compress(){
		List<Node> leaves = findLeaves(myRoot);
		for (Node leaf : leaves) {
			compressLeaf(leaf);
		}
	}
	
	private void compressLeaf(Node leaf) {
		if (leaf.parent.children.size() > 1 || leaf.parent.isWord) {
			leaf.isWord = true;
			return;
		}
		leaf.parent.info += leaf.info;
		leaf.parent.children.clear();
		compressLeaf(leaf.parent);
	}
	
	public List<Node> findLeaves(Node curNode) {
		List<Node> leaves = new ArrayList<Node>();
		if(curNode.children.isEmpty())
			leaves.add(curNode);
		for(Node childNode : curNode.children.values()) {
			leaves.addAll(findLeaves(childNode));
		}
		return leaves;
	}
	
	@Override
	public LexStatus wordStatus(StringBuilder s){
	    Node t = myRoot;
	    for (int k = 0; k < s.length(); k++) {
	        char ch = s.charAt(k);
	        t = t.children.get(ch);
	        if (t == null) {
	            return LexStatus.NOT_WORD;
	        }
        	for(int i = 1; i < t.info.length(); i++){
        		if (k + i >= s.length()) return LexStatus.PREFIX;
        		if (!((""+s.charAt(k+i)).equals(""+t.info.charAt(i)))){
        			return LexStatus.NOT_WORD;
        		}
        	}
        	k += t.info.length() - 1;
	    }
	    return t.isWord ? LexStatus.WORD : LexStatus.PREFIX; 
	}
	
}
