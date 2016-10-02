
public class QuickUWPC implements IUnionFind{
    private int[] myID;    // myID[i] = parent of i
    private int[] mySize;    // mySize[i] = number of objects in subtree rooted at i
    private int myComponents;   // number of components

	public QuickUWPC() {
		myID = null;
		mySize = null;
		myComponents = 0;
	}
    

    public QuickUWPC(int n) {
        initialize(n);
    }

    public void initialize(int n) {
    	myComponents = n;
        myID = new int[n];
        mySize = new int[n];
        for (int i = 0; i < n; i++) {
            myID[i] = i;
            mySize[i] = 1;
            }
	}
    

    public int components() {
        return myComponents;
    }


    public int find(int p) {
        int root = p;
    	while (root != myID[root])
            root = myID[root];
    	//path compression
    	while (p != root) {
    		int temp = myID[p];
    		myID[p] = root;
    		p = temp;
    	}	
    	return root;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

  
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        // make smaller root point to larger one
        if   (mySize[rootP] < mySize[rootQ]) { myID[rootP] = rootQ; mySize[rootQ] += mySize[rootP]; }
        else                         { myID[rootQ] = rootP; mySize[rootP] += mySize[rootQ]; }
        myComponents--;
    }


}
