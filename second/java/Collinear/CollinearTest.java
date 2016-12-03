import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

//-------------------------------------------------------------------------
/**
 *  Test class for Collinear.java
 *
 *  @author Ailbhe Redmond 
 *  @version 14/10/15 14:30:00
 */
@RunWith(JUnit4.class)
public class CollinearTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new Collinear();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the two methods work for empty arrays
     */
    @Test
    public void testEmpty()
    {
        int expectedResult = 0;

        assertEquals("countCollinear failed with 3 empty arrays",       expectedResult, Collinear.countCollinear(new int[0], new int[0], new int[0]));
        assertEquals("countCollinearSorted failed with 3 empty arrays", expectedResult, Collinear.countCollinearSorted(new int[0], new int[0], new int[0]));
    }

    // ----------------------------------------------------------
    /**
     * Check for no false positives in a single-element array
     */
    @Test
    public void testSingleFalse()
    {
        int[] a3 = { 15 };
        int[] a2 = { 5 };
        int[] a1 = { 10 };

        int expectedResult = 0;

        assertEquals("countCollinear({10}, {5}, {15})",       expectedResult, Collinear.countCollinear(a1, a2, a3) );
        assertEquals("countCollinearSorted({10}, {5}, {15})", expectedResult, Collinear.countCollinearSorted(a1, a2, a3) );
    }

    // ----------------------------------------------------------
    /**
     * Check for no false negatives in a single-element array
     */
    @Test
    public void testSingleTrue()
    {
        int[] a3 = { 15, 5 };       int[] a2 = { 5 };       int[] a1 = { 10, 15, 5 };
        int[] a3sorted = { 5, 15 }; int[] a2sorted = { 5 }; int[] a1sorted = { 5, 10, 15 };

        int expectedResult = 1;

        assertEquals("countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," 
        										+ Arrays.toString(a3) + ")", expectedResult, 
        										Collinear.countCollinear(a1, a2, a3));
        assertEquals("countCollinearSorted(" + Arrays.toString(a1sorted) + "," + Arrays.toString(a2sorted) 
        										+ "," + Arrays.toString(a3sorted) + ")", expectedResult, 
        										Collinear.countCollinearSorted(a1sorted, a2sorted, a3sorted));
    }

    // ---------------------------------------------------------
    /**
     *  Test both elements of binary search (searching above and below)
     */
    @Test
    public void testBinarySearch()
    {
        int low = 6; int high = 30; int wrong = 5; int[] sorted = {4, 6, 10, 15, 30, 45};

        assertTrue("binarySearch low", Collinear.binarySearch(sorted, low));
        assertTrue("binarySearch high", Collinear.binarySearch(sorted, high)); 
        assertFalse("binarySearch false" , Collinear.binarySearch(sorted, wrong)); 
    }

    // ---------------------------------------------------------
    /**
     *  Check for no false negatives in a multi-element array
     */
    
    @Test
    public void testMultipleTrue()
    {
    	int[] a3 = {15, 5, 10};       int[] a2 = {5, 10, 15};       int[] a1 = {10, 15, 5};
        int[] a3sorted = {5, 10, 15}; int[] a2sorted = {5, 10, 15}; int[] a1sorted = {5, 10, 15};

        int expectedResult = 5;

        assertEquals("countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," 
        										+ Arrays.toString(a3) + ")", expectedResult, 
        										Collinear.countCollinear(a1, a2, a3));
        assertEquals("countCollinearSorted(" + Arrays.toString(a1sorted) + "," + Arrays.toString(a2sorted) 
        										+ "," + Arrays.toString(a3sorted) + ")", expectedResult, 
        										Collinear.countCollinearSorted(a1sorted, a2sorted, a3sorted));
    }
    
    // ---------------------------------------------------------
    /**
     *  Check for no false positives in a multi-element array
     */
    
    @Test
    public void testMultipleFalse()
    {
    	int[] a3 = {15, 25, 5};       int[] a2 = {30, 35, 45};       int[] a1 = {20, 40, 10};
        int[] a3sorted = {5, 15, 25}; int[] a2sorted = {30, 35, 45}; int[] a1sorted = {10, 20, 40};

        int expectedResult = 0;

        assertEquals("countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," 
        										+ Arrays.toString(a3) + ")", expectedResult, 
        										Collinear.countCollinear(a1, a2, a3));
        assertEquals("countCollinearSorted(" + Arrays.toString(a1sorted) + "," + Arrays.toString(a2sorted) 
        										+ "," + Arrays.toString(a3sorted) + ")", expectedResult, 
        										Collinear.countCollinearSorted(a1sorted, a2sorted, a3sorted));
    }

    
}
