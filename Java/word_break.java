
import java.util.*;

public class word_break {
	/*
	 time: O(n ^ m), m is len of s, n is size of wordDict
	 */
	public static boolean wordBreak(String s, List<String> wordDict) {
        return wordBreakHelper(s, wordDict, 0);
    }
    
    private static boolean wordBreakHelper(String s, List<String> wordDict, int startIndex) {
        // base case
        if (startIndex >= s.length()) {
            return true;
        }
        
        for (String word : wordDict) {
        	if (s.length() - startIndex >= word.length()) {  // !!!!
        		if (s.substring(startIndex, startIndex + word.length()).equals(word)) {
        			if (wordBreakHelper(s, wordDict, startIndex + word.length()) == true) {  // !!!
        				return true;
        			}
        		}
            }
        }
        
        return false;
    }
    
    public static void main(String[] args) {
    	List<String> list = new ArrayList<>();
    	list.add("car");
    	list.add("ca");
    	list.add("rs");
    	System.out.println(wordBreak("cars", list));
    }
}
