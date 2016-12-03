// -------------------------------------------------------------------------
/**
 *  This class contains only two static methods that search for points on the
 *  same line in three arrays of integers. 
 *
 *  @author  Ailbhe Redmond
 *  @version 13/10/15 20:40:00
 */

class Collinear
{
    // ----------------------------------------------------------
    /**
     * Counts for the number of non-hoizontal lines that go through 3 points in arrays a1, a2, a3.
     * This method is static, thus it can be called as Collinear.countCollinear(a1,a2,a3)
     * @param a1: An UNSORTED array of integers. Each integer a1[i] represents the point (a1[i], 1) on the plane.
     * @param a2: An UNSORTED array of integers. Each integer a2[i] represents the point (a2[i], 2) on the plane.
     * @param a3: An UNSORTED array of integers. Each integer a3[i] represents the point (a3[i], 3) on the plane.
     * @return the number of points which are collinear and do not lie on a horizontal line.
     *
     * Array a1, a2 and a3 contain points on the horizontal line y=1, y=2 and y=3, respectively.
     * A non-horizontal line will have to cross all three of these lines. Thus
     * we are looking for 3 points, each in a1, a2, a3 which lie on the same
     * line.
     *
     * Three points (x1, y1), (x2, y2), (x3, y3) are collinear (i.e., they are on the same line) if
     * 
     * x1(y2−y3)+x2(y3−y1)+x3(y1−y2)=0 
     *
     * In our case y1=1, y2=2, y3=3. Therefore the condition we need to check for collinearity is
     *
     * x1(2-3)+x2(3-1)+x3(1-2)=0
     *
     * or equivalently:
     *
     * -x1 + 2*x2 - x3 = 0
     *
     * Therefore, this method returns the number of triples a1[i], a2[j], a3[k] (for some i,j,k) for which
     *
     * 2*a2[j] - a1[i] - a3[k] = 0
     *
     * You should implement this using a BRUTE FORCE approach (check all possible combinations of numbers from 
     * a1, a2, a3)
     *
     * ----------------------------------------------------------
     *
     *  Give the performance of the running time of your implementation using the Theta notation. Explain your
     *  answer.
     *
     *  Performance: Theta(n^3)
     *
     *  Explanation: There are 3 nested for loops which generates n^3 running time. A single for loop has n
     *  running time thus two for loops give nxn running time (n^2) and 3 for loops give nxnxn running time
     *  (n^3). All other lines have constant running time of 1 thus n^3 being the highest power running time
     *  becomes the theta running time.  
     */
	
    static int countCollinear(int[] a1, int[] a2, int[] a3)
    {
      int result = 0;
      for (int i = 0; i < a1.length; i++)
      {
          for (int j = 0; j < a2.length; j++)
	  {
	     for (int k = 0; k < a3.length; k++)
	     {
	        if (((2*a2[j] - a1[i] - a3[k]) == 0))
	    	   result++;
	     }
	  }
      }
      return result;
    }

    // ----------------------------------------------------------
    /**
     * Counts for the number of non-horizontal lines that go through 3 points in arrays a1, a2, a3.
     * This method is static, thus it can be called as Collinear.countCollinearSorted(a1,a2,a3)
     * @param a1: A SORTED array of integers. Each integer a1[i] represents the point (a1[i], 1) on the plain.
     * @param a2: A SORTED array of integers. Each integer a2[i] represents the point (a2[i], 2) on the plain.
     * @param a3: A SORTED array of integers. Each integer a3[i] represents the point (a3[i], 3) on the plain.
     * @return the number of points which are collinear and do not lie on a horizontal line.
     *
     * This time implement the method by the following algorithm:
     * - consider all combinations of points from a1 and a2
     * - for each such combination a1[i], a2[j] compute c = 2*a2[j] - a1[i]. If
     *   a3 contains a point collinear to a1[i], a2[j] then this point is c.
     * - Search for c in a3. If it exists then you have found a collinear triple.
     *
     * Implement and use binary search for looking up c in a3.
     *
     */
    static int countCollinearSorted(int[] a1, int[] a2, int[] a3)
    {
      int count = 0;
      for (int i = 0; i < a1.length; i++)
      {
         for (int j = 0; j < a2.length; j++)
	 {
	    int c = (2*a2[j] - a1[i]);
	    if (Collinear.binarySearch(a3, c))
	    {
	    	count++;
	    }
	 }
      }
      return count;
    }

    // ----------------------------------------------------------
    /**
     * Searches for an integer inside an array of integers.
     * This method is static, thus it can be called as Collinear.binarySearch(a,x)
     * @param a: A SORTED array of integers. 
     * @param x: An integer.
     * @return true if 'x' is contained in 'a'; false otherwise.
     *
     * ----------------------------------------------------------
     *
     *  Your implementation should run in Theta(log(n)) time.
     *
     */
    static boolean binarySearch(int[] a, int x)
    {
      int low = 0;
      int hi = a.length - 1;
      while (low <= hi)
      {
    	  int mid = low + (hi - low)/2;
    	  if (x < a[mid])
    	  {
    		  hi = mid - 1;
    	  }
    	  else if (x > a[mid])
    	  {
    		  low = mid + 1;
    	  }
    	  else
    		return true;  
      }
      return false;
    }
}
