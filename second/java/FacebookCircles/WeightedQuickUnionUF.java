
/******************************************************************************
 *  From Algorithms by Robert Sedgewick and Kevin Wayne
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *  
 *  Compilation:  javac WeightedQuickUnionUF.java
 *  Execution:  java WeightedQuickUnionUF < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *
 *  Weighted quick-union (without path compression).
 *
 ******************************************************************************/

public class WeightedQuickUnionUF {
    public int[] parent;   // parent[i] = parent of i
    public int[] size;     // size[i] = number of sites in subtree rooted at i
    private int count;      // number of components

    /**
     * Initializes an empty union-find data structure with N sites
     * 0 through N-1. Each site is initially in its own 
     * component.
     *
     * @param  N the number of sites
     * @throws IllegalArgumentException if N < 0
     */
    public WeightedQuickUnionUF(int N) 
    {
        count = N;
        parent = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) 
        {
            parent[i] = i;
            size[i] = 1;
        }
    }

    /**
     * Returns the number of components.
     *
     * @return the number of components (between 1 and N)
     */
    public int count() 
    {
        return count;
    }
    
    /**
     * Returns the component identifier for the component containing site p.
     *
     * @param  p the integer representing one object
     * @return the component identifier for the component containing site p
     * @throws IndexOutOfBoundsException unless 0 < p < N
     */
    public int find(int p) 
    {
        while (p != parent[p])
            p = parent[p];
        return p;
    }
    
    /**
     * Merges the component containing site p with the 
     * the component containing site q.
     *
     * @param  p the integer representing one site
     * @param  q the integer representing the other site
     * @throws IndexOutOfBoundsException unless
     *         both 0 < p < N and 0 < q < N
     */
    public void union(int p, int q) 
    {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        // make smaller root point to larger one
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
    }
}

