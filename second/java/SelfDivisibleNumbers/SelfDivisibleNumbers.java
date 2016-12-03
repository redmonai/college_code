import java.util.ArrayList;

/**
 * CS2010 (Hilary Term) - Assignment 3
 * 
 * Self Divisible Numbers
 * 
 * Self divisible numbers are those, that satisfy the following property:
 * 		a) All the 9 digits in the number are different, i.e. each of the 9 digits 1..9 is used once.
 * 		b) The number denoted by the first k-digits is divisible by k (i.e. the first k-digits are a multiple of k)
 *  
 *  	Consider the number 723654981; 
 *  	We have:   1|7,  2|72, 3|723, 4|7236, 5|72365, 6|723654  [read  a|b  as â€œa divides bâ€ or â€œb is a multiple of aâ€ ] 
 *  	but 7 does not divide  7236549. So this number does not satisfy property b).
 *  
 * Create a Java program that generates all 9-digit numbers.
 * 
 * 1) Implement all methods described bellow - like in HT assignment 1, calculate the values in the constructor
 * 2) Implement tests, which sufficiently cover your code
 *  
 * @author: Ailbhe Redmond
 */

public class SelfDivisibleNumbers 
{
	public static final int MIN_NINE = 123456789;
	public static final int MAX_NINE = 987654321;
	
	ArrayList<Integer> numbers;
	
	public SelfDivisibleNumbers() 
	{
		numbers = new ArrayList<Integer>();
	}
	
	/*
	 * Method to produce a number corresponding to first k digits of the digits array
	 * @param digits
	 * @param k number of digits to construct the result from
	 * @return number
	 */
	public int getFirstKDigitNumber(int[] digits, int k)
	{
		int result = 0;
		int mul = 1;
		for (int i = k-1; i >= 0; i--)
		{
			result += digits[i]*mul;
			mul *= 10;
		}
		return result;
	}
	
	/*
	 * Method to check if the specified number is divisible by the divisor
	 * @param number
	 * @param divisor
	 * @return true if number is divisible by the divisor
	 */
	public boolean isDivisible(int number, int divisor) 
	{
		return (number%divisor == 0);
	}
	
	/*
	 * Method to return a list containing all self divisible numbers found
	 * @return 9-digit self divisible numbers
	 */
	public ArrayList<Integer> getSelfDivisibleNumbers() 
	{
		for (int i = 370000008; i < 390000000; i+=9)
		{		
			boolean valid = true;
			
			if (i%9 == 0)
			{
				if (containsAllDigitsOnce(i))
				{
					int temp = i;
					int[] digits = new int[9];
					for (int j = 8; j >= 0; j--)
					{
						digits[j] = temp%10;
						temp/=10;
					}
					int k = 1;
					while (valid && k < 10)
					{
						int subnum = getFirstKDigitNumber(digits, k);
						if (!isDivisible(subnum, k))
						{
							valid = false;
						}
						k++;
					}
					if (valid == true)
					{
						numbers.add(i);
					}
				}
			}
		}
		return numbers;
	}
	
	/*
	 * Method to return the number of self divisible numbers found
	 * @return number of 9-digit self divisible numbers
	 */
	public int getNumberOfSelfDivisibleNumbers() 
	{
		return numbers.size();
	}

	/*
	 * Method to return if a passed number contains all digits between 1 and 9 exactly once
	 * @param number
	 * @return boolean - true if contains all digits once
	 */
    public boolean containsAllDigitsOnce(int number) 
    {
    	boolean[] usedDigits = new boolean[9];
        for (int i = 0; i < 9; i++)
        {
        	usedDigits[i] = false;
        }
    	int currentDigit = 0;
    	int num = number;
    	while (num > 0)
    	{
    		currentDigit = num%10;
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
    		num/=10;
    	}
    	return true;
    }
    
//    void all_perms(int k)
//    {       
//        //p is the int array
//    	//used is the boolean array
//    	
//    	if (k == 9)
//        {
//        	//everything is good so do something
//        }
//        else
//        {
//            for (int j = 0; j < p.length; j = j+1)
//            {
//                if (!used[j])
//                {
//                    p[k] = j;
//                    used[j] = true;
//                    all_perms(k+1);
//                    used[j] = false;
//                }
//            }
//        }
//    }
//    
//    
}