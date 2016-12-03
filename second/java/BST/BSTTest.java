import static org.junit.Assert.*;
 
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
 
//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @version 4.0 24/11/2015 20:30
 *
 *  @author  Ailbhe Redmond
 */
 
@RunWith(JUnit4.class)
public class BSTTest
{
	/** Test {@link BST#isEmpty()}. */
    @Test
    public void testIsEmpty()
    {
    	BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	assertTrue("Checking if an empty int bst returns empty(true)", bst.isEmpty());
    	bst.put(1,1);
    	assertFalse("Checking if a 1 element int bst returns not empty(false)", bst.isEmpty());
    	bst.put(2,2);
    	bst.put(3,3);
    	assertFalse("Checking if a 3 element int bst returns not empty(false)", bst.isEmpty());
        bst.put(4,4);
        bst.put(5,5);
        bst.put(6,6);
        bst.put(7,7);
        bst.put(8,8);
    	assertFalse("Checking if an 8 element int bst returns not empty(false)", bst.isEmpty());

    	BST<String, Integer> alphabst = new BST<String, Integer>();
    	assertTrue("Checking if an empty String bst returns empty(true)", alphabst.isEmpty());
    	alphabst.put("A",1);
    	assertFalse("Checking if a 1 element String bst returns not empty(false)", alphabst.isEmpty());
    	alphabst.put("B",2);
    	alphabst.put("C",3);
    	assertFalse("Checking if a 3 element String bst returns not empty(false)", alphabst.isEmpty());
    	alphabst.put("D",4);
        alphabst.put("E",5);
        alphabst.put("F",6);
        alphabst.put("G",7);
        alphabst.put("H",8);
    	assertFalse("Checking if an 8 element String bst returns not empty(false)", alphabst.isEmpty());
    }
	
    /** Test {@link BST#size()}. */
    @Test
    public void testSize()
    {
    	BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	assertEquals("Checking size of an empty tree", 0, bst.size());
    	bst.put(1,1);
    	assertEquals("Checking size of a 1 element tree", 1, bst.size());
    	bst.put(2,2);
    	bst.put(3,3);
    	assertEquals("Checking size of a 3 element tree", 3, bst.size());
    	bst.put(4,4);
        bst.put(5,5);
        bst.put(6,6);
        bst.put(7,7);
        bst.put(8,8);
    	assertEquals("Checking size of an 8 element tree", 8, bst.size());
    	
    	BST<String, Integer> alphabst = new BST<String, Integer>();
    	alphabst.put("A",1);
    	assertEquals("Checking size of a 1 element String tree", 1, alphabst.size());
    	alphabst.put("B",2);
    	alphabst.put("C",3);    	
    	assertEquals("Checking size of a 3 element String tree", 3, alphabst.size());
    	alphabst.put("D",4);
        alphabst.put("E",5);
        alphabst.put("F",6);
        alphabst.put("G",7);
        alphabst.put("H",8);
    	assertEquals("Checking size of an 8 element String tree", 8, alphabst.size());
    }

    /** Test {@link BST#contains()}. */
    @Test
    public void testcontains()
    {
    	BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	assertFalse("Checking if an empty int bst does not contain key 1", bst.contains(1));
    	bst.put(1,1);
    	assertTrue("Checking if a 1 element int bst does contain key 1", bst.contains(1));
    	assertFalse("Checking if a 1 element int bst does not contain key 2", bst.contains(2));
    	bst.put(2,2);
    	bst.put(3,3);
    	assertTrue("Checking if a 3 element int bst does contain key 3", bst.contains(3));
    	assertTrue("Checking if a 3 element int bst does contain key 2", bst.contains(2));
    	bst.put(4,4);
        bst.put(5,5);
        bst.put(6,6);
        bst.put(7,7);
        bst.put(8,8);
    	assertTrue("Checking if an 8 element int bst does contain key 6", bst.contains(6));
    	assertTrue("Checking if an 8 element int bst does contain key 1", bst.contains(1));
    	assertFalse("Checking if an 8 element int bst does not contain key 10", bst.contains(10));
    	assertFalse("Checking if an 8 element int bst does not contain key -1", bst.contains(-1));

    	BST<String, Integer> alphabst = new BST<String, Integer>();
    	assertFalse("Checking if an empty String bst does not contain key A", alphabst.contains("A"));
    	alphabst.put("A",1);
    	assertTrue("Checking if a 1 element String bst does contain key A", alphabst.contains("A"));
    	assertFalse("Checking if a 1 element String bst does not contain key B", alphabst.contains("B"));    	
    	alphabst.put("B",2);
    	alphabst.put("C",3);
    	assertTrue("Checking if a 3 element String bst does contain key C", alphabst.contains("C"));
    	assertTrue("Checking if a 3 element String bst does contain key B", alphabst.contains("B"));
    	alphabst.put("D",4);
        alphabst.put("E",5);
        alphabst.put("F",6);
        alphabst.put("G",7);
        alphabst.put("H",8);
        assertTrue("Checking if an 8 element String bst does contain key F", alphabst.contains("F"));
    	assertTrue("Checking if an 8 element String bst does contain key A", alphabst.contains("A"));
    	assertFalse("Checking if an 8 element String bst does not contain key J", alphabst.contains("J"));
    }
    
    /** Test {@link BST#get()}. */
    @Test
    public void testGet()
    {
    	BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	assertNull("Checking that an empty int bst cannot get a value", bst.get(1));
    	bst.put(1,1);
    	assertEquals("Getting a value from a one element int bst", Integer.valueOf(1), bst.get(1));
    	assertNull("Checking that getting from a non existent key does not return "
    										+ "a value in a 1 element tree", bst.get(2));
    	bst.put(2,2);
    	bst.put(3,3);
    	assertEquals("Getting a value from a three element int bst", Integer.valueOf(2), bst.get(2));
    	assertEquals("Getting a value from a three element int bst", Integer.valueOf(1), bst.get(1));
    	assertNull("Checking that getting from a non existent key does not return "
    										+ "a value in a 3 element tree", bst.get(6));
    	bst.put(4,4);
        bst.put(5,5);
        bst.put(6,6);
        bst.put(7,7);
        bst.put(8,8);
        assertEquals("Getting a value from an 8 element int bst", Integer.valueOf(6), bst.get(6));
    	assertEquals("Getting a value from an 8 element int bst", Integer.valueOf(4), bst.get(4));
    	assertNull("Checking that getting from a non existent key does not return "
    										+ "a value in an 8 element tree", bst.get(10));
    	assertNull("Checking that getting from a non existent key does not return "
											+ "a value in an 8 element tree", bst.get(-1));	
    }
    
    /** Test {@link BST#put()}. */
    @Test
    //returns the value
    public void testPut()
    {
    	BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	assertEquals("Checking an empty tree where nothing has been put", "()", bst.printKeysInOrder());
    	bst.put(1, null);
    	assertEquals("Checking the tree remains empty after putting a null value", "()", bst.printKeysInOrder());
    	bst.put(1, 1);
    	assertEquals("Checking a tree after putting one element", "(()1())", bst.printKeysInOrder());
        bst.put(2, 2);       
        bst.put(3, 3); 
        assertEquals("Checking a tree after putting three elements", "(()1(()2(()3())))", bst.printKeysInOrder());
        bst.put(3, 6);       
        assertEquals("Checking a tree after putting an element with the same key as a previous one", "(()1(()2(()3())))", bst.printKeysInOrder());
        bst.put(4, null);
        assertEquals("Checking a tree remains the same after putting a null value", "(()1(()2(()3())))", bst.printKeysInOrder());

    }
    
    /** Test {@link BST#height()}. */
    @Test
    public void testHeight()
    {
    	BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	assertEquals("Checking that an empty tree has height -1", -1, bst.height());
    	bst.put(7,7);
    	assertEquals("Checking that a 1 element tree has height 0", 0, bst.height());
    	bst.put(8,8);       
        bst.put(3,3); 
    	assertEquals("Checking that a 3 element tree has height 1", 1, bst.height());
    	bst.put(1,1);       
        bst.put(2,2);       
        bst.put(6,6);       
        bst.put(4,4);       
        bst.put(5,5);
    	assertEquals("Checking that a 8 element tree has height 4", 4, bst.height());
    }
    
    /** Test {@link BST#median()}. */
    @Test
    public void testMedian()
    {
    	BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	assertNull("Checking that an empty tree has null median", bst.median());
    	bst.put(7,7);
    	assertEquals("Checking that a 1 element tree has median of the root key", Integer.valueOf(7), bst.median());
    	bst.put(8,8);
    	bst.put(3,3);
    	bst.put(1,1);
    	assertEquals("Checking that a 4 element tree (1,3,7,8) has median 3", Integer.valueOf(3), bst.median());
    	bst.put(2, 2);       
        bst.put(6, 6);       
        bst.put(4, 4); 
    	assertEquals("Checking that a multi element (1,2,3,4,6,7,8) (uneven number) tree has median 4", Integer.valueOf(4), bst.median());
        bst.put(5, 5);
    	assertEquals("Checking that a multi element (1,2,3,4,5,6,7,8) (even number) tree has median 4", Integer.valueOf(4), bst.median());
    }
     
	/** Test {@link BST#printKeysInOrder()}. */
    @Test
    public void testPrintKeysInOrder()
    {
    	BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	assertEquals("Checking print in order of an empty tree", "()", bst.printKeysInOrder());
    	bst.put(7, 7);
    	assertEquals("Checking print in order of a 1 element tree", "(()7())", bst.printKeysInOrder());
        bst.put(8, 8);       
        bst.put(3, 3); 
        assertEquals("Checking print in order of a 3 element tree", "((()3())7(()8()))", bst.printKeysInOrder());
        bst.put(1, 1);       
        bst.put(2, 2);       
        bst.put(6, 6);       
        bst.put(4, 4);       
        bst.put(5, 5);
    	assertEquals("Checking print in order for multiple elements", 
    									"(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());
        bst.delete(8);
        bst.delete(7);
        bst.delete(6);
        bst.delete(5);
        bst.delete(4);
        bst.delete(3);
        bst.delete(2);
        bst.delete(1);
    	assertEquals("Checking print in order of a tree with all elements deleted", "()", 
    									bst.printKeysInOrder());
    
    	BST<String, Integer> alphabst = new BST<String, Integer>();
    	alphabst.put("G",7);
    	assertEquals("Checking print in order of a 1 element String tree", "(()G())", 
    									alphabst.printKeysInOrder());
    	alphabst.put("H",8);
    	alphabst.put("C",3);    	
    	assertEquals("Checking print in order of a 3 element String tree", "((()C())G(()H()))", 
    									alphabst.printKeysInOrder());
    	alphabst.put("A",1);
        alphabst.put("B",2);
        alphabst.put("F",6);
        alphabst.put("D",4);
        alphabst.put("E",5);
    	assertEquals("Checking print in order of a 3 element String tree", 
    									"(((()A(()B()))C((()D(()E()))F()))G(()H()))", alphabst.printKeysInOrder());
    
    }
	
    /** Test {@link BST#prettyPrintKeys()}. */
    @Test
    public void testPrettyPrint() 
    {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        assertEquals("Checking pretty printing of empty tree", "-null\n", bst.prettyPrintKeys());
        
        bst.put(7,7);
        String result1 = "-7\n"
        			  + " |-null\n"
        		      + "  -null\n";
        assertEquals("Checking pretty print of a one element tree", result1, bst.prettyPrintKeys());
        
        bst.put(8,8);
        bst.put(3,3);
        String result2 = "-7\n"
  			  		   + " |-3\n"
  			  		   + " | |-null\n"
  			  		   + " |  -null\n"
		               + "  -8\n"
  			  		   + "   |-null\n"
		               + "    -null\n";
        assertEquals("Checking pretty print of a three element tree", result2, bst.prettyPrintKeys());

                              //  -7
                              //   |-3
                              //   | |-1
                              //   | | |-null
        					  //   | |  -2
        					  //   | |   |-null
        					  //   | |    -null
        bst.put(1, 1);        //   |  -6
        bst.put(2, 2);        //   |   |-4
        bst.put(6, 6);        //   |   | |-null
        bst.put(4, 4);        //   |   |  -5
        bst.put(5, 5);        //   |   |   |-null
                              //   |   |    -null
                              //   |    -null
                              //    -8
                              //     |-null
                              //      -null
          
        String result3 = 
          "-7\n" +
          " |-3\n" + 
          " | |-1\n" +
          " | | |-null\n" + 
          " | |  -2\n" +
          " | |   |-null\n" +
          " | |    -null\n" +
          " |  -6\n" +
          " |   |-4\n" +
          " |   | |-null\n" +
          " |   |  -5\n" +
          " |   |   |-null\n" +
          " |   |    -null\n" +
          " |    -null\n" +
          "  -8\n" +
          "   |-null\n" +
          "    -null\n";
        assertEquals("Checking pretty printing of non-empty tree", result3, bst.prettyPrintKeys());
        
        
        BST<String, Integer> alphabst = new BST<String, Integer>();
    	alphabst.put("G",7);
    	String result4 = "-G\n"
  			  		   + " |-null\n"
  			  		   + "  -null\n";
    	assertEquals("Checking pretty print of a 1 element String tree", result4, alphabst.prettyPrintKeys());
    	alphabst.put("H",8);
    	alphabst.put("C",3); 
    	String result5 = "-G\n"
		  		       + " |-C\n"
		  		       + " | |-null\n"
		  		       + " |  -null\n"
		  		       + "  -H\n"
		  		       + "   |-null\n"
		  		       + "    -null\n";
    	assertEquals("Checking pretty print of a 3 element String tree", result5, alphabst.prettyPrintKeys());

    	alphabst.put("A",1);
        alphabst.put("B",2);
        alphabst.put("F",6);
        alphabst.put("D",4);
        alphabst.put("E",5);
        String result6 = 
                "-G\n" +
                " |-C\n" + 
                " | |-A\n" +
                " | | |-null\n" + 
                " | |  -B\n" +
                " | |   |-null\n" +
                " | |    -null\n" +
                " |  -F\n" +
                " |   |-D\n" +
                " |   | |-null\n" +
                " |   |  -E\n" +
                " |   |   |-null\n" +
                " |   |    -null\n" +
                " |    -null\n" +
                "  -H\n" +
                "   |-null\n" +
                "    -null\n";
    	assertEquals("Checking pretty print of a 3 element String tree", result6, alphabst.prettyPrintKeys());
    }
 
    /** Test {@link BST#delete(Comparable)}. */
    @Test
    public void testDelete() 
    {
    	BST<Integer, Integer> bst = new BST<Integer, Integer>();
        bst.delete(1);
        assertEquals("Deleting from empty tree", "()", bst.printKeysInOrder());
          
        bst.put(7, 7);   //        _7_
        bst.put(8, 8);   //      /     \
        bst.put(3, 3);   //    _3_      8
        bst.put(1, 1);   //  /     \
        bst.put(2, 2);   // 1       6
        bst.put(6, 6);   //  \     /
        bst.put(4, 4);   //   2   4
        bst.put(5, 5);   //        \
                          //         5
          
        assertEquals("Checking order of constructed tree", "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());
         
        bst.delete(9);
        assertEquals("Deleting non-existent key", "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());
  
        bst.delete(8);
        assertEquals("Deleting leaf", "(((()1(()2()))3((()4(()5()))6()))7())", bst.printKeysInOrder());
  
        bst.delete(6);
        assertEquals("Deleting node with no right child", "(((()1(()2()))3(()4(()5())))7())", bst.printKeysInOrder());
  
        bst.delete(3);
        assertEquals("Deleting node with two children", "(((()1())2(()4(()5())))7())", bst.printKeysInOrder());
        
        bst.delete(1);
        assertEquals("Deleting node with no left child", "((()2(()4(()5())))7())", bst.printKeysInOrder());
        
        bst = new BST<Integer, Integer>();
        //create right leaning bst
        bst.put(4,4);
        bst.put(5,5);
        bst.put(6,6);
        bst.put(7,7);
        bst.put(8,8);
        bst.delete(6);
        assertEquals("Deleting node from right leaning bst", "(()4(()5(()7(()8()))))", bst.printKeysInOrder());    
    }
}