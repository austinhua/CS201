
/****************************************************************************
 *
 *  Compilation:  javac QuickFind.java
 *
 ****************************************************************************/

public class QuickFind implements IUnionFind {
    private int[] myID;
    private int myComponents;

    
    public QuickFind(int N) {
        initialize(N);
    }
    public QuickFind(){
        
    }
    
    // instantiate N isolated components 0 through N-1
    public void initialize(int n){
        myComponents = n;
        myID = new int[n];
        for (int i = 0; i < n; i++){
            myID[i] = i;
        }
    }

    // return number of connected components
    public int components() { return myComponents; }

    // return id of component corresponding to element x
    public int find(int x) { return myID[x]; }

    // are elements p and q in the same component?
    public boolean find(int p, int q) {
        return myID[p] == myID[q];
    }

    // merge components containing p and q
    public void unite(int p, int q) {
        if (find(p,q)) return;
        int pid = myID[p];
        for(int i=0; i < myID.length; i++)
            if (myID[i] == pid) 
                myID[i] = myID[q];
        myComponents -= 1;
    }
	@Override
	public boolean connected(int p, int q) {
		// Todo Auto-generated method stub
		return false;
	}
	@Override
	public void union(int p, int q) {
		// Todo Auto-generated method stub
		
	}   
}

