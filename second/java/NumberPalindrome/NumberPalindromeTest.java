import static org.junit.Assert.*;
import org.junit.Test;

public class NumberPalindromeTest 
{
	
	@Test
	public void testReverse() 
	{
		new NumberPalindrome();
		assertEquals("Testing reverse with 1234567", 7654321, NumberPalindrome.reverse(1234567));
		assertEquals("Testing reverse with 1111111", 1111111, NumberPalindrome.reverse(1111111));
	}
	
	@Test
	public void testIsPalindrome() 
	{
		assertEquals("Testing isPalindrome with 123456", false, NumberPalindrome.isPalindrome(123456));
		assertEquals("Testing isPalindrome with 123321", true, NumberPalindrome.isPalindrome(123321));
	}
	
	@Test 
	public void testLargestPalindromeThreeDigitNumberProduct() 
	{
		assertEquals("Testing LargestPalindromeThreeDigitNumberProduct", 906609, NumberPalindrome.largestPalindromeThreeDigitNumberProduct());
	}
	
	@Test 
	public void testLargestPalindromeNDigitProduct() 
	{
		assertEquals("Testing LargestPalindromeNDigitNumberProduct", 906609, NumberPalindrome.largestPalindromeNDigitProduct(3));
		assertEquals("Testing LargestPalindromeNDigitNumberProduct", 9966006699L, NumberPalindrome.largestPalindromeNDigitProduct(5));
		assertEquals("Testing LargestPalindromeNDigitNumberProduct", 99956644665999L, NumberPalindrome.largestPalindromeNDigitProduct(7));
	}
	

}