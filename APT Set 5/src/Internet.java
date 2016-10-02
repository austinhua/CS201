import java.util.*;

public class Internet {
	 // adjList[k] is all the adjacent routers to k
	int[][] adjList;
	
	// return number of nodes reachable beginning at start but after removing removeIndex
	private int traverse(int removeIndex) {
		Queue<Integer> qu = new LinkedList<Integer>();
		TreeSet<Integer> visited = new TreeSet<Integer>();
		int start = 0;
		if (removeIndex == 0) start = 1;
		qu.add(start);
		visited.add(start);
		int reachable = 1;
		while (!qu.isEmpty()) {
			int current = qu.remove();
			//visited.add(current); doesn't work here because produces duplicate adds to qu
			for (int neighbor: adjList[current]) {
				if (!visited.contains(neighbor) && neighbor != removeIndex) {
					visited.add(neighbor);
					qu.add(neighbor);
					//System.out.printf("Added %d\n", neighbor);
					reachable++;
				}
			}
		}
		return reachable;
	}
	
	public int articulationPoints(String[] routers) {
		int points = 0;
		adjList = new int[routers.length][0];
		for (int k = 0; k < routers.length; k++) {
			String[] adjs = routers[k].split("\\s");
			adjList[k] = new int[adjs.length];
			for (int j = 0; j < adjs.length; j++) {
				adjList[k][j] = Integer.parseInt(adjs[j]);
			}
		}
		for (int i = 0; i < routers.length; i++){
			if (traverse(i) != routers.length - 1) {
				points++;
			}
		}
    	return points;
    }
  }