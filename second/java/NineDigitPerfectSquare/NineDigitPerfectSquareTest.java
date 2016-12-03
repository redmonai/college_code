import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Test class for NineDigitPerfectSquare
 *
 * @author: Ailbhe Redmond
 *
 *	TODO: Implement tests to cover the functionality provided by your methods.
 *
 */

public class NineDigitPerfectSquareTest 
{

    @Test
    public void testConstructor() 
    {
    	NineDigitPerfectSquare tmp = new NineDigitPerfectSquare();
        assertNotNull("Testing the constructor of NineDigitPerfectSquare", tmp);
    }

    /**
     * Test method for the containsAllDigitsOnce method
     */
    @Test
    public void testContainsAllDigitsOnce() 
    {
        NineDigitPerfectSquare ndps = new NineDigitPerfectSquare();
        assertEquals("Testing containsAllDigitsOnce with 123456789, expected true", true, ndps.containsAllDigitsOnce(123456789) );
        assertEquals("Testing containsAllDigitsOnce with 987654321, expected true", true, ndps.containsAllDigitsOnce(987654321) );
        assertEquals("Testing containsAllDigitsOnce with 432198765, expected true", true, ndps.containsAllDigitsOnce(432198765) );
        assertEquals("Testing containsAllDigitsOnce with 111111111, expected false", false, ndps.containsAllDigitsOnce(111111111) );
        assertEquals("Testing containsAllDigitsOnce with 222222222, expected false", false, ndps.containsAllDigitsOnce(222222222) );
    }

    @Test
    public void testCountNineDigitPerfectSquares() 
    {
        NineDigitPerfectSquare ndps = new NineDigitPerfectSquare();
        assertEquals("Testing countNineDigitPerfectSquares", 30, ndps.countNineDigitPerfectSquares());
    }

    @Test
    public void testGetNineDigitPerfectSquares() 
    {
    	NineDigitPerfectSquare ndps = new NineDigitPerfectSquare();
    	ndps.countNineDigitPerfectSquares();
        int[] actual = ndps.getNineDigitPerfectSquares();
        Arrays.sort(actual);
        int[] expected = new int[] { 139854276, 152843769, 157326849,
              215384976, 245893761, 254817369, 326597184, 361874529,
              375468129, 382945761, 385297641, 412739856, 523814769,
              529874361, 537219684, 549386721, 587432169, 589324176,
              597362481, 615387249, 627953481, 653927184, 672935481,
              697435281, 714653289, 735982641, 743816529, 842973156,
              847159236, 923187456 };
        Arrays.sort(expected);
        assertArrayEquals("Testing getNineDigitPerfectSquares", expected, actual);
    }
}
