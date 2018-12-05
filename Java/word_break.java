
import java.util.*;

public class word_break {
	/*
	 time: O(n ^ m), m is len of s, n is size of wordDict
	 space: O(m), since there is m layers in the recursion tree
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
    
    
    public static boolean wordBreak2(String s, List<String> wordDict) {
    	int[] M = new int[s.length()];
        return wordBreakHelperNew(s, wordDict, 0, M);
    }
    
    
    // try to do memoization, 
    // so time: O(m * n), since each char in s need to do only once calculation, each calculation iterate all words in wordDict
    // space: O(m + m)
    private static boolean wordBreakHelperNew(String s, List<String> wordDict, int startIndex, int[] M) {
        // base case
        if (startIndex >= s.length()) {
            return true;
        }
        
        if (M[startIndex] == 1) {
        	return true;
        } else if (M[startIndex] == -1) {
        	return false;
        }
        
        for (String word : wordDict) {
        	if (s.length() - startIndex >= word.length()) {  // !!!!
        		if (s.substring(startIndex, startIndex + word.length()).equals(word)) {
        			if (wordBreakHelperNew(s, wordDict, startIndex + word.length(), M) == true) {  // !!!
        				M[startIndex] = 1;
        				return true;
        			}
        		}
            }
        }
        
        M[startIndex] = -1;
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
