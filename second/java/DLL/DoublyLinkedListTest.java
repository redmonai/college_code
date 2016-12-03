import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @author Ailbhe Redmond 
 *  @version 28/10/15
 */

@RunWith(JUnit4.class)
public class DoublyLinkedListTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new DoublyLinkedList<Integer>();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check if isEmpty method works
     */
    @Test
    public void testIsEmpty()
    {
    	// test empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        assertTrue("Checking isEmpty for an empty list", testDLL.isEmpty());
        
        // test one element list
        testDLL.insertBefore(0,1);
        assertFalse("Checking isEmpty for a 1 element list", testDLL.isEmpty());

        // test two element list
        testDLL.insertBefore(1,2);
        assertFalse("Checking isEmpty for a 2 element list", testDLL.isEmpty());

        // test three element list
        testDLL.insertBefore(2,3);
        assertFalse("Checking isEmpty for a 3 element list", testDLL.isEmpty());
        testDLL.deleteAt(0);
        testDLL.deleteAt(0);
        testDLL.deleteAt(0);
        assertTrue("Checking isEmpty for a newly empty (3 element) list", testDLL.isEmpty());

        // test list as stack
        DoublyLinkedList<Integer> testStack = new DoublyLinkedList<Integer>();
        assertTrue("Checking isEmpty for an empty stack", testStack.isEmpty());
        
        // test one element stack
        testStack.push(1);
        assertFalse("Checking isEmpty for a 1 element stack", testStack.isEmpty());
        
        // test two element stack
        testStack.push(2);
        assertFalse("Checking isEmpty for a 2 element stack", testStack.isEmpty());
        testStack.pop();
        testStack.pop();
        assertTrue("Checking isEmpty for a newly empty (2 element) stack", testStack.isEmpty());
        
        // test list as queue
        DoublyLinkedList<Integer> testQueue = new DoublyLinkedList<Integer>();
        assertTrue("Checking isEmpty for an empty queue", testQueue.isEmpty());
        
        // test one element stack
        testQueue.enqueue(1);
        assertFalse("Checking isEmpty for a 1 element queue", testQueue.isEmpty());
        
        // test two element stack
        testQueue.enqueue(2);
        assertFalse("Checking isEmpty for a 2 element queue", testQueue.isEmpty());
        testQueue.dequeue();
        testQueue.dequeue();
        assertTrue("Checking isEmpty for a newly empty (2 element) queue", testQueue.isEmpty());
    }
    
    // ----------------------------------------------------------
    /**
     * Check if the insertBefore method works
     */
    @Test
    public void testInsertBefore()
    {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);

        testDLL.insertBefore(0,4);
        assertEquals("Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3", 
        								testDLL.toString());
        testDLL.insertBefore(1,5);
        assertEquals("Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3", 
        								testDLL.toString());
        testDLL.insertBefore(2,6);       
        assertEquals("Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3", 
        								testDLL.toString());
        testDLL.insertBefore(-1,7);        
        assertEquals("Checking insertBefore to a list containing 6 elements at position -1 - expected the "
        								+ "element at the head of the list", "7,4,5,6,1,2,3", testDLL.toString());
        testDLL.insertBefore(7,8);        
        assertEquals("Checking insertBefore to a list containing 7 elemenets at position 8 - expected the "
        								+ "element at the tail of the list", "7,4,5,6,1,2,3,8", testDLL.toString() );
        testDLL.insertBefore(700,9);        
        assertEquals("Checking insertBefore to a list containing 8 elements at position 700 - expected the element "
        								+ "at the tail of the list", "7,4,5,6,1,2,3,8,9", testDLL.toString());
        testDLL.insertBefore(-100,10);        
        assertEquals("Checking insertBefore to a list containing 9 elements at position -100 - expected the "
        								+ "element at the head of the list", "10,7,4,5,6,1,2,3,8,9", testDLL.toString());
        
        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);        
        assertEquals("Checking insertBefore to an empty list at position 0 - expected the element at the head "
        								+ "of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(10,1);        
        assertEquals("Checking insertBefore to an empty list at position 10 - expected the element at the head "
        								+ "of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(-10,1);        
        assertEquals("Checking insertBefore to an empty list at position -10 - expected the element at the head "
        								+ "of the list", "1", testDLL.toString() );
     }
    
    // ----------------------------------------------------------
    /**
     * Check if the get function works
     */
    @Test
    public void testGet()
    {
    	//test empty list
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	assertNull("Checking get to an empty list at pos -100 - expect null return", testDLL.get(-100));
    	assertNull("Checking get to an empty list at pos 2 - expect null return", testDLL.get(2) );
    	assertNull("Checking get to an empty list at pos 1 - expect null return", testDLL.get(1) );
    	assertNull("Checking get to an empty list at pos -2 - expect null return", testDLL.get(-2));
    	assertNull("Checking get to an empty list at pos -1 - expect null return", testDLL.get(-1));
    	assertNull("Checking get to an empty list at pos 0 - expect null return", testDLL.get(0));
    	assertNull("Checking get to an empty list at pos 100 - expect null return", testDLL.get(100));
    	
    	//test 1 element list
        testDLL.insertBefore(0,1);
        assertNull("Checking get to a 1 element list at pos -2 - expect null return", testDLL.get(-2));
    	assertNull("Checking get to a 1 element list at pos -1 - expect null return", testDLL.get(-1));
    	assertEquals("Checking get to a 1 element list at pos 0 - expect value of only element", 
    								Integer.valueOf(1), testDLL.get(0));
    	assertNull("Checking get to a 1 element list at pos 1 - expect null return", testDLL.get(1));
    	assertNull("Checking get to a 1 element list at pos 2 - expect null return", testDLL.get(2));
    	assertNull("Checking get to a 1 element list at pos 100 - expect null return", testDLL.get(100));
    	assertNull("Checking get to a 1 element list at pos -100 - expect null return", testDLL.get(-100));

    	//test 2 element list
        testDLL.insertBefore(1,2);
        assertNull("Checking get to a 2 element list at pos -2 - expect null return", testDLL.get(-2));
    	assertNull("Checking get to a 2 element list at pos -1 - expect null return", testDLL.get(-1));
    	assertEquals("Checking get to a 2 element list at pos 0 - expect value of head element", 
    								Integer.valueOf(1), testDLL.get(0));
    	assertEquals("Checking get to a 2 element list at pos 1 - expect value of tail element", 
									Integer.valueOf(2), testDLL.get(1));
    	assertNull("Checking get to a 2 element list at pos 2 - expect null return", testDLL.get(2));
    	assertNull("Checking get to a 2 element list at pos 100 - expect null return", testDLL.get(100));
    	assertNull("Checking get to a 2 element list at pos -100 - expect null return", testDLL.get(-100));
        
        //test 3 element list
        testDLL.insertBefore(2,3);
    	assertEquals("Checking get to a list containing 3 elements at pos 0 - expect the element at "
    								+ "the head of the list", Integer.valueOf(1), testDLL.get(0));
    	assertEquals("Checking get to a list containing 3 elements at pos 1 - expect the element at "
    								+ "the middle of the list", Integer.valueOf(2), testDLL.get(1));
    	assertEquals("Checking get to a list containing 3 elements at pos 2 - expect the tail element", 
    								Integer.valueOf(3), testDLL.get(2));
    	assertNull("Checking get to a list containing 3 elements at pos 6 - expect null return", testDLL.get(6) );
    	assertNull("Checking get to a 3 element list at pos -1 - expect null return", testDLL.get(-1));
    	assertNull("Checking get to a 3 element list at pos -2 - expect null return", testDLL.get(-2));
    	assertNull("Checking get to a 3 element list at pos -3 - expect null return", testDLL.get(-3));
    	assertNull("Checking get to a 3 element list at pos 3 - expect null return", testDLL.get(3));
    	assertNull("Checking get to a 3 element list at pos 100 - expect null return", testDLL.get(100));
    	assertNull("Checking get to a 3 element list at pos -100 - expect null return", testDLL.get(-100));
    }
    
    // ----------------------------------------------------------
    /**
     * Check if the deleteAt method works
     */
    @Test
    public void testDeleteAt()
    {
    	// test empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	assertFalse("Checking delete from an empty list at pos 0", testDLL.deleteAt(0));
    	assertFalse("Checking delete from an empty list at pos -2", testDLL.deleteAt(-2));
    	assertFalse("Checking delete from an empty list at pos -1", testDLL.deleteAt(-1));
    	assertFalse("Checking delete from an empty list at pos 1", testDLL.deleteAt(1));
    	assertFalse("Checking delete from an empty list at pos 2", testDLL.deleteAt(2));

    	// test a 1 element list
        testDLL.insertBefore(0,1);
        assertFalse("Checking delete from a 1 element list at pos -2", testDLL.deleteAt(-2));
    	assertFalse("Checking delete from a 1 element list at pos -1", testDLL.deleteAt(-1));
    	assertFalse("Checking delete from a 1 element list at pos 1", testDLL.deleteAt(1));
    	assertFalse("Checking delete from a 1 element list at pos 2", testDLL.deleteAt(2));
        assertTrue("Checking delete of the only element of a 1 element list", testDLL.deleteAt(0));	
        
        //test a 2 element list
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        assertTrue("Checking delete of the head of a 2 element list", testDLL.deleteAt(0));	
        assertTrue("Checking delete of the new head of a 2 element list", testDLL.deleteAt(0));	

        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        assertFalse("Checking delete from a 2 element list at pos -2", testDLL.deleteAt(-2));
    	assertFalse("Checking delete from a 2 element list at pos -1", testDLL.deleteAt(-1));
    	assertFalse("Checking delete from a 2 element list at pos 2", testDLL.deleteAt(2));
        assertTrue("Checking delete of the tail of a 2 element list", testDLL.deleteAt(1));	
        assertTrue("Checking delete at head after delete at tail of 2 element list", testDLL.deleteAt(0));
        
        //test a 3 element list
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        assertFalse("Checking delete from a 3 element list at pos -2", testDLL.deleteAt(-2));
    	assertFalse("Checking delete from a 3 element list at pos -1", testDLL.deleteAt(-1));
        assertTrue("Checking delete from the middle of a 3 element list", testDLL.deleteAt(1));
        testDLL.insertBefore(1,2);
        assertTrue("Checking delete from the head of a 3 element list", testDLL.deleteAt(0));
        testDLL.insertBefore(0,1);
        assertTrue("Checking delete from the tail of a 3 element list", testDLL.deleteAt(2)); 
        testDLL.insertBefore(2,3);
        assertFalse("Checking delete from beyond the 3 element list", testDLL.deleteAt(10));  
        
    }
    
    // ----------------------------------------------------------
    /**
     * Check if the push method works
     */
    @Test
    public void testPush()
    {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.push(1);
        assertEquals("Checking push to an empty list", "1", testDLL.toString());
        
        testDLL.push(4);
        assertEquals("Checking push to a 1 element list", "4,1", testDLL.toString());
    }
    
    // ----------------------------------------------------------
    /**
     * Check if the pop method works
     */
    @Test
    public void testPop()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	
    	//test empty list
    	assertNull("Checking pop from an empty list[" + testDLL + "] element", testDLL.pop());
    	
    	//test 1 element list
        testDLL.push(1);
        assertEquals("Checking pop from a 1 element list[" + testDLL + "] element", Integer.valueOf(1), testDLL.pop());
    	assertNull("Checking pop from a newly empty (1 element) list[" + testDLL + "] element", testDLL.pop());
        
    	//test 2 element list
        testDLL.push(1);
        testDLL.push(2);
        assertEquals("Checking pop from a 2 element list[" + testDLL + "] element", Integer.valueOf(2), testDLL.pop());
        assertEquals("Checking pop from a 2 element list[" + testDLL + "] element", Integer.valueOf(1), testDLL.pop());
    	assertNull("Checking pop from a newly empty (2 element) list[" + testDLL + "] element", testDLL.pop());
        
    	//test 3 element list
    	testDLL.push(1);
        testDLL.push(2);
        testDLL.push(3);
        assertEquals("Checking pop from a 3 element list[" + testDLL + "] element", Integer.valueOf(3), testDLL.pop());
        assertEquals("Checking pop from a 3 element list[" + testDLL + "] element", Integer.valueOf(2), testDLL.pop());
        assertEquals("Checking pop from a 3 element list[" + testDLL + "] element", Integer.valueOf(1), testDLL.pop());
    	assertNull("Checking pop from a newly empty (3 element) list[" + testDLL + "] element", testDLL.pop());
    }
    
    // ----------------------------------------------------------
    /**
     * Check if the enqueue method works
     */
    @Test
    public void testEnqueue()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.enqueue(1);
        assertEquals("Checking enqueue to an empty list", "1", testDLL.toString());
        
        testDLL.enqueue(2);
        assertEquals("Checking enqueue to a 1 element list", "1,2", testDLL.toString());
    }
    
    // ----------------------------------------------------------
    /**
     * Check if the dequeue method works
     */
    @Test
    public void testDequeue()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	//test empty list
    	assertNull("Checking dequeue from an empty list[" + testDLL + "] element", testDLL.dequeue());
    	
    	//test 1 element list
        testDLL.enqueue(1);
        assertEquals("Checking dequeue from a 1 element list[" + testDLL + "] element", Integer.valueOf(1), testDLL.dequeue());
    	assertNull("Checking dequeue from a newly empty (1 element) list[" + testDLL + "] element", testDLL.dequeue());
        
    	//test 2 element list
    	testDLL.enqueue(1);
        testDLL.enqueue(2);
        assertEquals("Checking dequeue from a 2 element list[" + testDLL + "] element", Integer.valueOf(1), testDLL.dequeue());
        assertEquals("Checking dequeue from a 2 element list[" + testDLL + "] element", Integer.valueOf(2), testDLL.dequeue());
    	assertNull("Checking dequeue from a newly empty (2 element) list[" + testDLL + "] element", testDLL.dequeue());
        
    	//test 3 element list
    	testDLL.enqueue(1);
        testDLL.enqueue(2);
        testDLL.enqueue(3);
        assertEquals("Checking dequeue from a 3 element list[" + testDLL + "] element", Integer.valueOf(1), testDLL.dequeue());
        assertEquals("Checking dequeue from a 3 element list[" + testDLL + "] element", Integer.valueOf(2), testDLL.dequeue());
        assertEquals("Checking dequeue from a 3 element list[" + testDLL + "] element", Integer.valueOf(3), testDLL.dequeue());
    	assertNull("Checking dequeue from a newly empty (3 element) list[" + testDLL + "] element", testDLL.dequeue());
  }
    
}
