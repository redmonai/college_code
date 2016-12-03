import java.util.ArrayList;

/**
 * CS2010 (Hilary Term) - Assignment 1
 *
 * Nine Digit Perfect Square
 *
 * A natural number, p, is a perfect square if for some natural number k, k^2=p.
 * For example, 16 is a perfect square, as 4^2=16. The number 20 is not a
 * perfect square as there is no natural number k such that k^2=20.
 *
 * Problem: Develop an algorithm that will find all nine-digit perfect squares
 * that use all nine digits exactly once. For example, 139,854,276 is a solution
 * (the first) as 11,826^2=139,854,276.
 *
 * 1) Fill in the implementation of the methods described in this file.
 *
 * 2) Test your implementation by developing suitable test suite in the
 * NineDigitPerfectSquareTest JUnit test case.
 *
 * @author: Ailbhe Redmond
 *
 */

public class NineDigitPerfectSquare
{
	public static final int MAX_DIGITS = 9;
	public static final int LOWEST_NINE = 139854276;
	public static final int HIGHEST_NINE = 923187456;

    /**
     * A method to return an array containing all squares between low and high
     *
     * @param low: lowest perfect square
     * @param high: largest perfect square
     *
     * @return an array containing all the perfect squares between low and high
     */
    public int[] perfectSquaresBetween(int low, int high)
    {
    	ArrayList<Integer> perfectSquares = new ArrayList<Integer>();   
    	
    	int odd, sum;
    	odd = 1;
    	sum = 1;
    	while (sum <= high)
    	{
    		odd+=2;
    		sum+=odd;
    		if (sum >= low && sum <= high)
    			perfectSquares.add(sum);
    	}

    	//convert arraylist to an array to be returned
    	int[] squares = new int[perfectSquares.size()];
    	for (int i=0; i < squares.length; i++)
    	{
    		squares[i] = perfectSquares.get(i);
    	}
    	return squares;
    }

    public int countNineDigitPerfectSquares()
    {
    	return getNineDigitPerfectSquares().length;
    }

    /**
     * A method to determine if the number specified in parameter "number"
     * contains all 9 digits exactly once.
     *
     * @param number
     *            : A number to be tested
     * @return whether the number contains all 9 digits exactly once
     */
    public boolean containsAllDigitsOnce(int number) 
    {
    	boolean[] usedDigits = new boolean[MAX_DIGITS];
        for (int i = 0; i < 9; i++)
        {
        	usedDigits[i] = false;
        }
    	int currentDigit = 0;
    	int prime = number;
    	while (prime > 0)
    	{
    		currentDigit = prime%10;
    		if (currentDigit == 0)
    		{
    			return false;
    		}
    		int index = currentDigit-1;  	//index of the array
    		if (usedDigits[index] == false)
    		{
    			usedDigits[index] = true;
    		}
    		else
    		{
    			return false;
    		}
    		prime/=10;
    	}
    	return true;
    }


    /**
     * A method to return an array containing all the squares discovered
     *
     * @return an array containing all of the perfect squares which
     * contain all digits 1..9 exactly once.
     */
    public int[] getNineDigitPerfectSquares() 
    {
    	ArrayList<Integer> perfectNines = new ArrayList<Integer>();
    	int[] perfectSquares = perfectSquaresBetween(LOWEST_NINE, HIGHEST_NINE);
    	for (int i = 0; i < perfectSquares.length; i++)
    	{
    		if (containsAllDigitsOnce(perfectSquares[i]))
    		{
    			perfectNines.add(perfectSquares[i]);
    		}
    	}
    	int[] nines = new int[perfectNines.size()];
    	for (int i=0; i < nines.length; i++)
    	{
    		nines[i] = perfectNines.get(i);
    	}
    	return nines;
    }
}