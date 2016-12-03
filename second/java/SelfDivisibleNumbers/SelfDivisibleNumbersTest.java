import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;


public class SelfDivisibleNumbersTest {

	static SelfDivisibleNumbers sdn;
	
	@BeforeClass
	public static void oneTimeSetUp() throws Exception {
		sdn = new SelfDivisibleNumbers();
	}
	
	@Test
	public void constructorTest() {
		assertNotNull("Checking the constructor",sdn);
	}
	
	@Test
	public void testGetFirstKDigitNumber()
	{
		int[] digits = {1,2,3,4,5,6,7,8,9};
		assertEquals("Grabbing the first digit of 123456789", 1, sdn.getFirstKDigitNumber(digits, 1));
		assertEquals("Grabbing the first 3 digits of 123456789", 123, sdn.getFirstKDigitNumber(digits, 3));
		assertEquals("Grabbing the first 5 digits of 123456789", 12345, sdn.getFirstKDigitNumber(digits, 5));
		assertEquals("Grabbing the first 7 digits of 123456789", 1234567, sdn.getFirstKDigitNumber(digits, 7));
		assertEquals("Grabbing the first 9 digits of 123456789", 123456789, sdn.getFirstKDigitNumber(digits, 9));
	}
	
	@Test
	public void testIsDivisible()
	{
		assertTrue("Dividing 4 by 2, expect true", sdn.isDivisible(4,2));
		assertFalse("Dividing 5 by 2, expect false", sdn.isDivisible(5,2));
		assertTrue("Dividing 381654729 by 9, expect true", sdn.isDivisible(381654729, 9));
	}
	
	@Test
	public void testGetSelfDivisibleNumbers()
	{
		ArrayList<Integer> result = new ArrayList<Integer>();
		result.add(381654729);
		ArrayList<Integer> numbers = sdn.getSelfDivisibleNumbers();
		assertEquals("Checking if getSelfDivisibleNumbers returns the right value", result.toString(), numbers.toString());
		assertEquals("Checking that elements in the result are 1", 1, sdn.getNumberOfSelfDivisibleNumbers());
		assertFalse("Checking that elements in the result are not 0", (0 == sdn.getNumberOfSelfDivisibleNumbers()));
	}
	
//	@Test
//	public void testGetNumberOfSelfDivisibleNumbers()
//	{
//		assertEquals("Checking that elements in the result are 1", 1, sdn.getNumberOfSelfDivisibleNumbers());
//		assertFalse("Checking that elements in the result are not 0", (0 == sdn.getNumberOfSelfDivisibleNumbers()));
//	}
	
	@Test
	public void testContainsAllDigitsOnce()
	{
		assertEquals("Testing containsAllDigitsOnce with 123456789, expected true", true, sdn.containsAllDigitsOnce(123456789) );
        assertEquals("Testing containsAllDigitsOnce with 987654321, expected true", true, sdn.containsAllDigitsOnce(987654321) );
        assertEquals("Testing containsAllDigitsOnce with 432198765, expected true", true, sdn.containsAllDigitsOnce(432198765) );
        assertEquals("Testing containsAllDigitsOnce with 111111111, expected false", false, sdn.containsAllDigitsOnce(111111111) );
        assertEquals("Testing containsAllDigitsOnce with 222222222, expected false", false, sdn.containsAllDigitsOnce(222222222) );
        assertEquals("Testing containsAllDigitsOnce with 101111011, expected false", false, sdn.containsAllDigitsOnce(111111111) );
	}
}