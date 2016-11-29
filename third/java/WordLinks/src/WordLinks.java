/* Word Chain connects strings together
 * Strings are chained if it starts with the trailing sub-string of its predecessor with a minimum of 3 overlapping characters
 * The first word in the input is the start of the chain
 * If no chain is possible, the program outputs IMPOSSIBLE
 * If chaining is possible, there will be a unique chain
 * 
 * Sample input:
 * 8 			//the number of words being input
 * whisper 
 * format 
 * perform 
 * sonnet 
 * person
 * shopper 
 * workshop 
 * network
 * 
 * Output:
 * whisper 
 * person 
 * sonnet 
 * network 
 * workshop 
 * shopper 
 * perform 
 * format
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class WordLinks {

	public static final int MIN_CHARS = 3;		//minimum number a string can match by
	public static final int MAX_CHARS = 30;		//maximum number of letters per string
	
	public static ArrayList<String> chain;
	
	public static void main(String[] args) 
	{
		int size;
		String[] input;
		Node[] nodes;
		
		//input
		Scanner in = new Scanner(System.in);
		size = in.nextInt();
		if (size < 0 || size > 20)
		{
			System.out.print("Size of list must be greater than 0 and less than 20.");
			System.exit(0);
		}
		input = new String[size];
		
		for (int i = 0; i < size; i++)
		{
			String temp = in.next();
			if (temp.length() < MIN_CHARS)
			{
				System.out.print("IMPOSSIBLE\n");
				System.exit(0);
			}
			else if (temp.length() > MAX_CHARS)
			{
				System.out.print("IMPOSSIBLE\n");
				System.exit(0);
			}
			input[i] = temp;
		}
		in.close();
		
		nodes = new Node[size];
		for (int makeNode = 0; makeNode < size; makeNode++)
		{
			Node newNode = new Node(input[makeNode], new ArrayList<Node>());
			nodes[makeNode] = newNode;
		}
		
		//build neighbours list and create the chain
		neighbours(size, nodes);		
		ArrayList<String> chain = find(nodes[0], size);
		
		//output
		if (chain != null)
		{
			Collections.reverse(chain);
			for (int i = 0; i < chain.size(); i++)
			{
				System.out.print(chain.get(i)+"\n");
			}
		}
		else
		{
			System.out.print("IMPOSSIBLE\n");
		}
	}
	
	public static void neighbours(int size, Node[] nodes)
	{
		for (int i = 0; i < size; i++)
		{
			Node temp = nodes[i];
			for (int j = 0; j < size; j++)
			{
				if(match(temp.word, nodes[j].word) && !temp.word.equals(nodes[j].word))
				{
					temp.neighbours.add(nodes[j]);
				}
			}
		}	
	}
	
	public static boolean match(String first, String second)
	{
		int maxLength = Integer.min(first.length(), second.length());
		for (int charCompare = 3; charCompare < maxLength; charCompare++)
		{
			if (first.regionMatches((first.length()-charCompare), second, 0, charCompare))
				return true;
		}
		return false;		
	}
	
	public static ArrayList<String> find (Node node, int count)
	{
		if (count == 1)													//base case
		{
			chain = new ArrayList<String>();
			chain.add(node.word);
			return chain;
		}
		node.visited = true;											//recursion
		for (int i = 0; i < node.neighbours.size(); i++)
		{
			if (!(node.neighbours.get(i).visited))
			{
				chain = find((node.neighbours.get(i)), (count-1));
				if (chain != null)
				{
					//valid link
					chain.add(node.word);
					return chain;
				}
			}
		}
		node.visited = false;		
		return chain;
	}
}
