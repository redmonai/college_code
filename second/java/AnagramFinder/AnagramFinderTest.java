import org.junit.Before;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class AnagramFinderTest {

    AnagramFinder finderBST,finderHT;
    String[] wordsSmall, wordsSmallDict, wordsMedium, wordsLarge;

    @Before
    public void setUp() {
        wordsSmall = loadWords();							//list of 6 words
        wordsSmallDict = loadWords("small_dictionary.txt");	//small dictionary of ~110 words
        wordsMedium = loadWords("medium_dictionary.txt");  	//medium dictionary of 10K words
        wordsLarge = loadWords("Dictionary_25K.txt");      	//large dictionary of 25K
        finderHT = new HTAnagram();
        finderBST = new BSTAnagram();
    }

    @Test
    public void BSTanagramTestSmallClass()
    {
    	long start = System.nanoTime();
        finderBST.createDictionary(wordsSmall);
        assertEquals("Test non-existing word", 0, finderBST.search("exception").length);
        assertEquals("should find anagrams of whereat", "[weather]", Arrays.toString(finderBST.search("whereat")));
        System.out.println("Small BST total runtime: "+(System.nanoTime() - start));
    }

    @Test
    public void HTanagramTestSmallClass()
    {
    	long start = System.nanoTime();
        finderHT.createDictionary(wordsSmall);
        assertEquals("Test non-existing word", 0, finderHT.search("exception").length);
        assertEquals("should find anagrams of whereat", "[weather]", Arrays.toString(finderHT.search("whereat")));
        System.out.println("Small HT total runtime: "+(System.nanoTime() - start));
    }
    
    @Test
    public void BSTanagramTestSmallDictClass()
    {
    	long start = System.nanoTime();
    	finderBST.createDictionary(wordsSmallDict);
        assertEquals("Test non-existing word", 0, finderBST.search("exception").length);
        assertEquals("should find anagrams of coatings", "[agnostic, coasting, coatings]", Arrays.toString(finderBST.search("coatings")));
        System.out.println("SmallDict BST total runtime: "+(System.nanoTime() - start));
    }
    
    @Test
    public void HTanagramTestSmallDictClass()
    {
    	long start = System.nanoTime();
    	finderHT.createDictionary(wordsSmallDict);
    	assertEquals("Test non-existing word", 0, finderHT.search("exception").length);
        assertEquals("should find anagrams of coatings", "[agnostic, coasting, coatings]", Arrays.toString(finderHT.search("coatings")));
        System.out.println("SmallDict HT total runtime: "+(System.nanoTime() - start));
    }
    
    @Test
    public void BSTanagramTestMedClass()
    {
        long start = System.nanoTime();
        finderBST.createDictionary(wordsMedium);
        assertEquals("Test non-existing word (trampolinemayor)", 0, finderBST.search("trampolinemayor").length);
        assertEquals("should find anagrams of (adaptive)", "[adaptive]", Arrays.toString(finderBST.search("tiveadap")));
        assertEquals("Test non-existing word (whattodo)", 0, finderBST.search("whattodo").length);
        assertEquals("should find anagrams of (cabinet)", "[cabinet]", Arrays.toString(finderBST.search("tenibac")));
        assertEquals("Test non-existing word (thisisboring)", 0, finderBST.search("thisisboring").length);
        assertEquals("should find anagrams of (martin)", "[martin]", Arrays.toString(finderBST.search("tinmar")));
        System.out.println("Med BST total runtime: "+(System.nanoTime() - start));
    }

    @Test
    public void HTanagramTestMedClass()
    {
        long start = System.nanoTime();
        finderHT.createDictionary(wordsMedium);
        assertEquals("Test non-existing word (trampolinemayor)", 0, finderHT.search("trampolinemayor").length);
        assertEquals("should find anagrams of (adaptive)", "[adaptive]", Arrays.toString(finderHT.search("tiveadap")));
        assertEquals("Test non-existing word (whattodo)", 0, finderHT.search("whattodo").length);
        assertEquals("should find anagrams of (cabinet)", "[cabinet]", Arrays.toString(finderHT.search("tenibac")));
        assertEquals("Test non-existing word (thisisboring)", 0, finderHT.search("thisisboring").length);
        assertEquals("should find anagrams of (martin)", "[martin]", Arrays.toString(finderHT.search("tinmar")));
        System.out.println("Med HT total runtime: "+(System.nanoTime() - start));
    }

    @Test
    public void BSTanagramTestLargeClass()
    {
        long start = System.nanoTime();
        finderBST.createDictionary(wordsLarge);
        assertEquals("Test non-existing word (trampolinemayor)", 0, finderBST.search("trampolinemayor").length);
        assertEquals("should find anagrams of (adaptive)", "[adaptive]", Arrays.toString(finderBST.search("tiveadap")));
        assertEquals("Test non-existing word (helloworld)", 0, finderBST.search("helloworld").length);
        assertEquals("should find anagrams of (retrograde)", "[retrograde]", Arrays.toString(finderBST.search("retrograde")));
        assertEquals("Test non-existing word (whattodo)", 0, finderBST.search("whattodo").length);
        assertEquals("should find anagrams of (cabinet)", "[cabinet]", Arrays.toString(finderBST.search("tenibac")));
        assertEquals("Test non-existing word (thisisboring)", 0, finderBST.search("thisisboring").length);
        assertEquals("should find anagrams of (martin)", "[martin]", Arrays.toString(finderBST.search("tinmar")));
        assertEquals("Test non-existing word (barmilabjad)", 0, finderBST.search("barmilabjad").length);
        assertEquals("should find anagrams of (weather)", "[weather, wreathe]", Arrays.toString(finderBST.search("weather")));
        System.out.println("Large BST total runtime: "+(System.nanoTime() - start));
    }

    @Test
    public void HTanagramTestLargeClass()
    {
        long start = System.nanoTime();
        finderHT.createDictionary(wordsLarge);
        assertEquals("Test non-existing word (trampolinemayor)", 0, finderHT.search("trampolinemayor").length);
        assertEquals("should find anagrams of (adaptive)", "[adaptive]", Arrays.toString(finderHT.search("tiveadap")));
        assertEquals("Test non-existing word (helloworld)", 0, finderHT.search("helloworld").length);
        assertEquals("should find anagrams of (retrograde)", "[retrograde]", Arrays.toString(finderHT.search("retrograde")));
        assertEquals("Test non-existing word (whattodo)", 0, finderHT.search("whattodo").length);
        assertEquals("should find anagrams of (cabinet)", "[cabinet]", Arrays.toString(finderHT.search("tenibac")));
        assertEquals("Test non-existing word (thisisboring)", 0, finderHT.search("thisisboring").length);
        assertEquals("should find anagrams of (martin)", "[martin]", Arrays.toString(finderHT.search("tinmar")));
        assertEquals("Test non-existing word (barmilabjad)", 0, finderHT.search("barmilabjad").length);
        assertEquals("should find anagrams of (weather)", "[weather, wreathe]", Arrays.toString(finderHT.search("weather")));
        System.out.println("Large HT total runtime: "+(System.nanoTime() - start));
    }

    private String[] loadWords(){
        return new String[]{ "test","word","hello","weather","bottle","parallel" };
    }

    private String[] loadWords(String fileName){
        //354937
        String[] words = new String[Short.MAX_VALUE];

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

            int i =0;
            String str;
            while((str = bufferedReader.readLine()) !=null){
                words[i] = str;
                i++;
            }

            bufferedReader.close();
            words =Arrays.copyOf(words,i);
        }
        catch(NullPointerException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
        return words;
    }
}