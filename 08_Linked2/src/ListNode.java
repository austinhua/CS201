// Todo Add your names and NetIDs below (comma-separated)
/*
 * Linked list practice code
 * 
 * SUBMIT-NAMES:
 * SUBMIT-NetIDs:
 */
public class ListNode {
    String info;
    ListNode prev;   
    ListNode next;
    
    public ListNode(String s, ListNode before, ListNode after){
        info = s;
        prev = before;
        next = after;
    }

    /**
     * @returns the node whose info field is largest (alphabetically), 
     * null if list is null
     */
    public static ListNode maxNodeIter (ListNode  list) {
    	if (list == null)
    		return null;
    	ListNode max = null;
    	for (ListNode current = list; current != null; current = current.next) {
    		if (max == null || max.info.compareTo(current.info) < 0)
    			max = current;
    	}
    	return max;
    }

    /**
     * @returns the node whose info field is largest (alphabetically), 
     * null if list is null. Should be recursive
     */
    public static ListNode maxNode (ListNode  list) {
        // Todo Complete recursive version of maxNode
        return null;
    }

    /**
     * Move node containing word to front of list and
     * return modified list. If word not found, no changes
     * made to list, original list returned
     * @param list is list being modified
     * @param word is targetted node for move-to-font
     * @return first node in possibly modified list.
     */
    public static ListNode moveToFront(ListNode list, String word) {
        ListNode first = list;
        // Todo Complete moveToFront

        return null;
    }

    public static void main(String[] args) {
        // Todo make a list ("A", "B", "C", "D")
        
        
    }
}
