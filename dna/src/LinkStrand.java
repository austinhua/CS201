
public class LinkStrand implements IDnaStrand{
	public class Node {
		String value;
		Node next;
		
		public Node(String s, Node link) {
			value = s;
			next = link;
		}
	}
	private Node myFront, myBack;
	private int mySize;
	
	public LinkStrand()
	{
		this("");
	}
	
	public LinkStrand(String dna) {
		myFront = new Node(dna, null);
		myBack = myFront;
		mySize = 1;
	}
	
	
	
	
	
	@Override
	public IDnaStrand cutAndSplice(String enzyme, String splicee) {
		if (myFront.next != null) {throw new RuntimeException("Link strand has more than one node"); }
		int pos = 0;
        int start = 0;
        StringBuilder search = new StringBuilder(myFront.value);
        boolean first = true;
        LinkStrand ret = null;
        
        while ((pos = search.indexOf(enzyme, pos)) >= 0) {
            if (first){
                ret = new LinkStrand(search.substring(start, pos));
                first = false;
            }
            else {
                 ret.append(search.substring(start, pos));
                 
            }
            start = pos + enzyme.length();
            ret.append(splicee);
            pos++;
        }

        if (start < search.length()) {
        	// NOTE: This is an important special case! If the enzyme
        	// is never found, return an empty String.
        	if (ret == null){
        		ret = new LinkStrand("");
        	}
        	else {
        		ret.append(search.substring(start));
        	}
        }
        return ret;
    }

	@Override
	public long size() {
		if (mySize == 1) return myFront.value.length();
		return myFront.value.length() + myFront.next.value.length() * (mySize/2);
	}

	@Override
	public void initializeFrom(String source) {
		myFront = new Node(source, null);
		myBack = myFront;
		mySize = 1;
	}

	@Override
	public String strandInfo() {
		return this.getClass().getName();
	}

	@Override
	public IDnaStrand append(IDnaStrand dna){
		if (dna instanceof LinkStrand) {
        myBack.next = ((LinkStrand) dna).myFront;
        myBack = myBack.next;
        mySize++;
        return this;
    } else {
        return append(dna.toString());
    }
}

	@Override
	public IDnaStrand append(String dna) {
		append(new LinkStrand(dna));
		return this;
	}

	@Override
	public IDnaStrand reverse() {
		myBack = myFront;
		Node current = myFront;
		Node prev = current;
		boolean first = true;
		while (current != null) {
			StringBuilder copy = new StringBuilder(current.value);
			current.value = copy.reverse().toString();
			if (first) {
				current = current.next;
				first = false;
			}
			else {
				Node temp = current;
				current = current.next;
				temp.next = prev;
				prev = temp;
			}
		}
		myFront = prev;
        return this;
	}

	@Override
	public String getStats() {
		return String.format("# append calls = %d",mySize);
	}
	
	@Override
    public String toString() {
		Node current = myFront;
		StringBuilder result = new StringBuilder();
        while (current != null) {
        	result.append(current.value);
        	current = current.next;
        }
		return result.toString();
    }

}
