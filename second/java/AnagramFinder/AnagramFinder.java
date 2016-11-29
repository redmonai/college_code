import java.util.*;


public abstract class AnagramFinder 
{
    public abstract void add(String word);

    public abstract String[] search(String word);

    public void createDictionary(String[] words) 
    {
        for(String s : words)
        {
            add(s);
        }
    }

    /*
     * getBaseFormOf method takes a String and returns a String
     * The passed String is converted to lowercase then sorted into alphabetical order and returned 
     */    
    protected String getBaseFormOf(String word)
    {
    	String lowercase = word.toLowerCase();
    	char[] letters = lowercase.toCharArray();
        Arrays.sort(letters);
        String result = String.valueOf(letters);
        return result;
    }
}