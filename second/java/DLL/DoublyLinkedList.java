import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author  Ailbhe Redmond
 *  @version 28/10/15 
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data: 
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 * 
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>>
{
    
    /**
     * private class DLLNode: implements a *generic* Doubly Linked List node.
     */
    private class DLLNode
    {
        public final T data; // this field should never be updated. It gets its
                             // value once from the constructor DLLNode.
        public DLLNode next;
        public DLLNode prev;
    
        /**
         * Constructor
         * @param theData : data of type T, to be stored in the node
         * @param prevNode : the previous Node in the Doubly Linked List
         * @param nextNode : the next Node in the Doubly Linked List
         * @return DLLNode
         */
        public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) 
        {
          data = theData;
          prev = prevNode;
          next = nextNode;
        }
    }
    
    //instance variables
    private int lengthOfList;
    // Fields head and tail point to the first and last nodes of the list.
    private DLLNode head, tail;

    /**
     * Constructor
     * @return DoublyLinkedList
     */
    public DoublyLinkedList() 
    {
      head = null;
      tail = null;
      lengthOfList = 0;
    }

    /**
     * Tests if the doubly linked list is empty
     * @return true if list is empty, and false otherwise
     *
     * Worst-case asymptotic runtime cost: constant (Theta(1))
     *
     * Justification: A basic return statement has constant runtime 
     */
    public boolean isEmpty()   
    {
    	return head == null;
    }
    

    /**
     * Inserts an element in the doubly linked list
     * @param pos : The integer location at which the new data should be
     *      inserted in the list. We assume that the first position in the list
     *      is 0 (zero). If pos is less than 0 then add to the head of the list.
     *      If pos is greater or equal to the size of the list then add the
     *      element at the end of the list.
     * @param data : The new data of class T that needs to be added to the list
     * @return none
     *
     * Worst-case asymptotic runtime cost: Theta log(n)
     *
     * Justification: While if/else statements have constant runtime, while loops have log(n) runtime. This
     * takes precedence over an otherwise constant runtime, thus the runtime of the algorithm is log(n).
     */
    public void insertBefore(int pos, T data) 
    {
    	DLLNode newNode = new DLLNode(data, null, null);    

    	if(isEmpty())
    	{
    		head = newNode;
    		tail = newNode;
    	}
    	else if (pos <= 0)
	    {
	    	newNode.prev = null;
	    	newNode.next = head;
	    	head.prev = newNode;
	    	head = newNode;   
	    }
	    else if (pos >= lengthOfList)
	    {
	    	newNode.next = null;
	    	newNode.prev = tail;
	    	tail.next = newNode;
	    	tail = newNode; 
	    }
	    else
	    {
	    	DLLNode current = head;
	    	int count = 0;
	    	while (count != pos)
	    	{
	    		current = current.next;
	    		count++;
	    	}
    		DLLNode tempNode = current;
	   		DLLNode prevNode = tempNode.prev;
	   		newNode.next = tempNode;
	   		tempNode.prev = newNode;
	   		prevNode.next = newNode;
	    	newNode.prev = prevNode;	
	    }
	    lengthOfList++;
	}

    /**
     * Returns the data stored at a particular position
     * @param pos : the position
     * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
     *
     * Worst-case asymptotic runtime cost: As above, Theta log(n)
     *
     * Justification: While if/else statements have constant runtime, while loops have log(n) runtime. This
     * takes precedence over an otherwise constant runtime, thus the runtime of the algorithm is log(n).
     *
     * Worst-case precise runtime cost: log(n) + 3
     *
     * Justification: There are three nested if/else statements as well as the while loop. Each if/else
     * statement has a constant runtime. Combining the runtime of these statements with the runtime of the
     * while loop gives a combined precise runtime of log(n) + 3
     */
    public T get(int pos) 
    {
    	if(!isEmpty())
    	{
	    	if (pos == 0)						//head
	    	{
	    		return head.data;
	    	}
	    	else if (pos == lengthOfList-1)				//tail
	    	{
	    		return tail.data;
	    	}
	    	else if (pos >= 0 && lengthOfList > 1 && pos < lengthOfList-1)
	    	{
	    		DLLNode current = head;						//pos0 
	    		T atPos = null;
		        int count = 0;
		        while (count != pos)  
		    	{
		        	current = current.next;
		    		count++;
		    	}
			    atPos = current.data;
		        return atPos;
		    }
	    	else
	    		return null;
    	}
    	else
    		return null;
    }

    /**
     * Deletes the element of the list at position pos.
     * First element in the list has position 0. If pos points outside the
     * elements of the list then no modification happens to the list.
     * @param pos : the position to delete in the list.
     * @return true : on successful deletion, false : list has not been modified. 
     *
     * Worst-case asymptotic runtime cost: As above, theta log(n)
     *
     * Justification: While if/else statements have constant runtime, while loops have log(n) runtime. This
     * takes precedence over an otherwise constant runtime, thus the runtime of the algorithm is log(n).
     */
    public boolean deleteAt(int pos) 
    {
    	if(!isEmpty())
    	{
	    	//remove head of 1 element list
	    	if(pos == 0 && lengthOfList == 1)
	    	{
	    		head = null;
	    		tail = null;
	    		lengthOfList--;
	    		return true;
	    	}
	    	//remove head of populated list
	    	else if (pos == 0)    
	    	{
	    		DLLNode newHead = head.next;
	    		newHead.prev = null;
	    		head = newHead;
	    		lengthOfList--;
	    		return true;
	    	}
	    	//remove tail of a populated list
	    	else if (pos == lengthOfList-1)
	    	{
	    		DLLNode newTail = tail.prev;
	    		newTail.next = null;
	    		tail = newTail;
	    		lengthOfList--;
	    		return true;
	    	}
	    	//remove element from middle of list
	    	else if (pos > 0 && pos < (lengthOfList-1))
	        {
	    		DLLNode current = head;
		    	int count = 0;
		    	while (count !=  pos)		
		    	{
		    		current = current.next;
		    		count++;
		    	}
		    	DLLNode tempNode = current;
		    	DLLNode prevNode = tempNode.prev;
		    	DLLNode nextNode = tempNode.next;
		    	prevNode.next = nextNode;
		    	nextNode.prev = prevNode;
		    	tempNode.next = null;
		    	tempNode.prev = null;
		    	lengthOfList--;
		        return true;		
	        }
	    	else
	    		return false;
    	}
        else
        	return false;
    }


    /*----------------------- STACK */
    /**
     * This method should behave like the usual push method of a Stack ADT.
     * If only the push and pop methods are called the data structure should behave like a stack.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to push on the stack
     *
     * Worst-case asymptotic runtime cost: This will have constant runtime, theta(1)
     *
     * Justification: This algorithm is based on a simple if/else statement and thus has constant runtime
     */
    public void push(T item)   
    {
	   	DLLNode pushItem = new DLLNode(item, null, null);
	    if(isEmpty())
	    {
	    	head = pushItem;
	    	tail = pushItem;
	    }
	    else
	    {
	    	pushItem.prev = null;
	    	head.prev = pushItem;
	    	pushItem.next = head;
	    	head = pushItem;
	    }
	   	lengthOfList++;
    }

    /**
     * This method should behave like the usual pop method of a Stack ADT.
     * If only the push and pop methods are called the data structure should behave like a stack.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @return the last item inserted with a push; or null when the list is empty.
     *
     * Worst-case asymptotic runtime cost: Theta(1), constant
     *
     * Justification: As above, this is a simple if/else algorithm and thus has constant runtime
     */
    public T pop() 
    {
    	if (!isEmpty())				
        {
    		if(lengthOfList == 1)
    		{
    			T result = head.data;
    			head = null;
    			tail = null;
    			lengthOfList--;
    			return result;
    		}
    		else
    		{
    			T result = head.data;
    			DLLNode newHead = head.next;
    	      	newHead.prev = null;				
    	      	head = newHead;
    	  	    lengthOfList--;
    	  	    return result;
    		}
        }
        else
        	return null;
    }

    /*----------------------- QUEUE */
 
    /**
     * This method should behave like the usual enqueue method of a Queue ADT.
     * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to be enqueued to the stack
     *
     * Worst-case asymptotic runtime cost: As above, this has constant runtime theta(1)
     *
     * Justification: This algorithm is similar to push. It is a basic if/else statement and thus has constant
     * runtime
     */
    public void enqueue(T item) 
    {
    	DLLNode queueItem = new DLLNode(item, null, null);
	    if (isEmpty())
	    {
	    	head = queueItem;
	    	tail = queueItem;
	    }
	    else
	    {
	    	queueItem.prev = tail;
	    	tail.next = queueItem;
	    	queueItem.next = null;
	    	tail = queueItem;
	    }
	   	lengthOfList++;
    }

     /**
     * This method should behave like the usual dequeue method of a Queue ADT.
     * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @return the earliest item inserted with a push; or null when the list is empty.
     *
     * Worst-case asymptotic runtime cost: As above, theta(1)
     *
     * Justification: Similar to pop. This is another if/else statement and will have constant runtime.
     */
    public T dequeue() 
    {
    	if (!isEmpty())				
        {
    		if(lengthOfList == 1)
    		{
    			T result = head.data;
    			head = null;
    			tail = null;
    			lengthOfList--;
    			return result;
    		}
    		else
    		{
    			T result = head.data;
    			DLLNode newHead = head.next;
    	      	newHead.prev = null;				
    	      	head = newHead;
    	  	    lengthOfList--;
    	  	    return result;
    		}
        }
        else
        	return null;
    }
 

    /**
     * @return a string with the elements of the list as a comma-separated
     * list, from beginning to end
     *
     * Worst-case asymptotic runtime cost:   Theta(n)
     *
     * Justification:
     *  We know from the Java documentation that StringBuilder's append() method runs in Theta(1) asymptotic time.
     *  We assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
     *  Thus, every one iteration of the for-loop will have cost Theta(1).
     *  Suppose the doubly-linked list has 'n' elements.
     *  The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
     */
    public String toString() 
    {
      StringBuilder s = new StringBuilder();
      boolean isFirst = true; 

      // iterate over the list, starting from the head
      for (DLLNode iter = head; iter != null; iter = iter.next)
      {
        if (!isFirst)
        {
          s.append(",");
        } else {
          isFirst = false;
        }
        s.append(iter.data.toString());
      }

      return s.toString();
    }


}


