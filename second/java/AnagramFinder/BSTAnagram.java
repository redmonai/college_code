import java.util.*;

public class BSTAnagram extends AnagramFinder
{
	TreeMap<String, String[]> tree;
	
	public BSTAnagram()
	{
		tree = new TreeMap<String, String[]>();
	}
	
	/*
	 * add method takes a String and adds it to the binary search tree
	 * The passed String is converted to its base form
	 * If that base form already exists as a key of the tree, the word is added to the String array of values at that key
	 * If the base form does not already exist, the word is added to an array and then added to the table at that key
	 */
	@Override
	public void add(String word) 
	{
		if (word != null)
		{
			String base = getBaseFormOf(word);
			if (tree.containsKey(base))
			{
				String[] currentArray = (String[]) tree.get(base);
				String[] newArray = new String[currentArray.length + 1];
				System.arraycopy(currentArray, 0, newArray, 0, currentArray.length);
				newArray[newArray.length-1] = word;
				tree.put(base, newArray);
			}
			else
			{
				String[] temp = {word};
				tree.put(base, temp);
			}
		}
	}

	/*
	 * search method takes a String and searches for its value in the binary search tree
	 * The String is converted to its base form
	 * If the tree contains that base form as a key, its value array is returned
	 * If not, it returns an empty array
	 */
	@Override
	public String[] search(String word) 
	{
		String[] ret = {};
		String base = getBaseFormOf(word);
		if (tree.containsKey(base))
		{
			ret = (String[])tree.get(base);
			return ret;
		}
		else
		{
			return ret;
		}
	}
}
