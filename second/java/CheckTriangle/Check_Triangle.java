import java.util.Arrays;

public class Check_Triangle
{
    public static final int ACUTE_TRI = 2;
    public static final int RIGHT_ANGLED_TRI = 1;
    public static final int OBTUSE_TRI = 3;
    public static final double TINY_DIFF = 0.0001;
    public static final int NOT_TRI = 0;

    static boolean form_triangle(double[] ls)
    {
    	 if (2*max(ls) < sum(ls))
    		 return true;
    	 else
    		 return false;
    }

    static int kind_triangle(double[] ls)
    {
    	if (form_triangle(ls))
    	{
    		Arrays.sort(ls);
    		double c = (ls[2]*ls[2]) - ((ls[1]*ls[1]) + (ls[0]*ls[0]));
    		if (c == 0)
    		{
    			return RIGHT_ANGLED_TRI;
    		}
    		else if (c < 0)
    		{
    			return ACUTE_TRI;
    		}
    		else if (c > 0)
    		{
    			return OBTUSE_TRI;
    		}
    	}
    	return NOT_TRI;
    }


    static double sum(double[] arr)
    {
    	double result = 0;
        for (int k = 0; k < arr.length; k++)
            result += arr[k];
        return result;
    }

    static double max(double[] arr)
    {
        int j = 0;
        int k = arr.length- 1;
        while ( j < k ) 
        {
        	if (arr[j] < arr[k])
                j++;
            else
                k--;
        }
        return arr[j];
    }
}