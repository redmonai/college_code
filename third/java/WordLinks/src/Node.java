import java.util.ArrayList;

public class Node
{
	String word;
	boolean visited;
	ArrayList<Node> neighbours;
	
	public Node(String value, ArrayList<Node> close)
	{
		word = value;
		visited = false;
		neighbours = close;
	}
}
