public class StringLinkedList implements Comparable<StringLinkedList> {

	private class Node {
		String myValue;
		Node myNext;

		Node(String value, Node next) {
			myValue = value;
			myNext = next;
		}
	}

	// You can add other instance variables, but you may then
	// need to update other methods
	Node myHead;

	// BE SURE YOU TEST THIS CODE WORKS WITH THE StringLinkedListTester
	// BEFORE YOU SUBMIT!

	/**
	 * Adds a new node with valueToAdd as value to the <em>end</em> of this
	 * linked list
	 */
	public void addAtEnd(String valueToAdd) {
		if (myHead == null) {
			myHead = new Node(valueToAdd, null);
			return;
		}
		
		Node current = myHead;
		while (current.myNext != null) {
			current = current.myNext;
		}
		current.myNext = new Node(valueToAdd, null);
	}

	/**
	 * Adds a new node with valueToAdd as value to the <em>beginning</em> of
	 * this linked list
	 */
	public void addAtBeginning(String valueToAdd) {
		myHead = new Node(valueToAdd, myHead);
	}

	/**
	 * Removes the longest string from the list,
	 * 
	 * if you have the list [a,b,longstring,z,q] after this function runs you
	 * end up with [a,b,z,q] if more than one string has the same longest
	 * length, remove the first one if the list is empty, do nothing if the list
	 * has only 1 element, remove it
	 */
	public void removeLongestString() {
		if (myHead == null) return;
		int longest = myHead.myValue.length();
		Node current = myHead;
		Node prev = myHead;
		
		while (current.myNext != null) {
			if (current.myNext.myValue.length() > longest)	{
				longest = current.myNext.myValue.length();
				prev = current;
			}
			current = current.myNext;
		}
		if (prev == myHead) {
			myHead = myHead.myNext;
		}
		else
			prev.myNext = prev.myNext.myNext;

	}

	/**
	 * Repeats (doubles) each element [a,b,c] -> [a,a,b,b,c,c]
	 */
	public void doubleList() {
		Node current = myHead;
		while (current != null)	{
			current.myNext = new Node(current.myValue, current.myNext);
			current = current.myNext.myNext;
		}
	}

	/**
	 * Move k elements from the beginning of the list to the end [a,b,c,d,e] ->
	 * moveToEnd(2) -> [c,d,e,a,b]
	 */
	public void moveToEnd(int k) {
		// Todo 4. completeMoveToEnd
	}

	public String toString() {
		// I've written this one for you!
		StringBuilder b = new StringBuilder();
		Node current = myHead;
		while (current != null) {
			b.append(current.myValue);
			// this is a little crude because it prints out a blank for last
			// element too
			// I opted to keep the code extra simple rather than print right
			b.append(" ");
			current = current.myNext;
		}
		return b.toString();
	}

	/**
	 * Implements a natural ordering on lists To compare string lists, first
	 * compare 1st elements, than second elements, etc. using the standard
	 * lexicographic string comparison. (e.g. [a z c] < [aa b c]) If one list
	 * has the same starting elements as another, but is shorter (e.g. [a] and
	 * [a b]) the shorter one is less if two StringLists have the same elements
	 * and are are the same length, then they are equal
	 */
	public int compareTo(StringLinkedList other) {
		// Todo 5. complete compareTo

		return 0;
	}

}
