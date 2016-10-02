Name: Austin Hua
NetID: ah335
Hours to complete assignment: 10
Collaborators: None

Resources Used: Austin Lu Walkthrough, Piazza, Princeton WeightedQuickUnionUF.java Code

/**********************************************************************
 *  Describe how you implemented PercolationUF.java. How did you check
 *  whether the system percolates?
 **********************************************************************/
PercolationUF was implemented by unioning opened cells together using a Union-Find structure. It checked 
whether a system percolates based on whether cells from the top virtual site were connected to cells 
from the bottom virtual site.

/**********************************************************************
 *  Using Percolation with DFS, give a formula (using Big-Oh 
 *  notation) for the running time (in seconds) of PercolationStats.java
 *  as a function of N and T. Estimate the largest experiment you could
 *  perform in 1 { minute, day, year } assuming your computer has
 *  enough memory.
 **********************************************************************/

The running time as a function of T will always be linear because T merely multiplies the number of times
the program is run. Thus, only N should need to be measured to determine the relation between N and running time.

measurements:
With T = 300,

 N          time (seconds)
------------------------------			
40			.457
60			2.169
80			5.998
100			12.991

Time is O(N^2)

running time as a function of N and T:  ~ O(N^2*T)
doubling N should quadruple run time, and doubling T should double run time.

Keeping a constant T of 300,
1 minute: N = 172.88
1 day: N = 5154.94
1 year: N = 97819.49

percolation threshold for N = 100: 0.5937, [.05919, 0.5955]

/**********************************************************************
 *  Repeat the previous question, but use quick find 
 **********************************************************************/

measurements:
with T = 300,

 N          time (seconds)
------------------------------
40			.366
60			1.655
80			5.120
100			13.425

Time is O(N^2)

running time as a function of N and T:  ~ O(N^2*T)
doubling N should double run time, and doubling T should quadruple run time

Keeping a constant T of 300,
1 minute: N = 162.31
1 day: N = 4484.55
1 year: N = 84879.15

percolation threshold for N = 100: .5937, [.5919, .5955]

/**********************************************************************
 *  Repeat the previous question, but use weighted quick union with
 *  path compression.
 **********************************************************************/

measurements:
with T = 300,

 N          time (seconds)
------------------------------
40			.068
60			.095
80			.134
100			.182

Time is around O(N) (closer to O(N^1.07))

running time as a function of N and T: ~O(N*T)
doubling N should double run time, and doubling T should double run time

Keeping a constant T of 300,
1 minute: N = 2134.73
1 day: N = 81132.22
1 year: N = 1550606.52

percolation threshold for N = 100: 0.5937, [0.5919, 0.5955]


/**********************************************************************
 *  How many bytes does your Percolation.java object use as a function
 *  of N? Use big-Oh notation to simplify your answer.
 **********************************************************************/

using dfs
O(N^2) - N^2 for myGrid
using quick find:
O(N^2) - N^2 for myGrid and myID
using weighted quick union with path compression:
O(N^2) - N^2 for myGrid, myID, and mySize


/**********************************************************************
 *  Known bugs / limitations.
 **********************************************************************/




/**********************************************************************
 *  List whatever help (if any) that you received, including help
 *  from other students, staff members or UTAs.
 **********************************************************************/


/**********************************************************************
 *  Describe any serious problems you encountered.                    
 **********************************************************************/




/**********************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **********************************************************************/

