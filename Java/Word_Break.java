
import java.util.*;

public class Word_Break {
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
        	// startIndex + word.length() <= s.length()   // !!!!
        	if (startIndex + word.length() <= s.length() && s.substring(startIndex, startIndex + word.length()).equals(word)) {
        		if (wordBreakHelper(s, wordDict, startIndex + word.length()) == true) {  // !!!
        			return true;
        		}
        	}
        }
        
        return false;
    }
    
    
    public static boolean wordBreak2(String s, List<String> wordDict) {
        return wordBreakHelperNew(s, wordDict, 0, new Boolean[s.length()]);
    }
    
    
    // try to do memoization in Boolean array M, since Boolean initialize all elements to null
    // so time: O(m * n), since each char in s need to do only once calculation, each calculation iterate all words in wordDict
    // space: O(m + m)
    private static boolean wordBreakHelperNew(String s, List<String> wordDict, int startIndex, Boolean[] M) {
        // base case
        if (startIndex >= s.length()) {
            return true;
        }
        
        if (M[startIndex] != null) {
        	return M[startIndex];
        }
        
        for (String word : wordDict) {
        	if (startIndex + word.length() <= s.length() && s.substring(startIndex, startIndex + word.length()).equals(word)) {
        		if (wordBreakHelperNew(s, wordDict, startIndex + word.length(), M) == true) {  // !!!
        			return M[startIndex] = true;
        		}
        	}
        }
        return M[startIndex] = false;
    }
    
    /*
    bottom-up DP:
    M[i] represents whether first i elements in s (from index 0 to i - 1) can be segmented into a sequence of one or more dictionary words.
    base case:
    M[0] = true?
    induction rule:
    M[i] = true if there exist at least one j (0 <= j < i) so that M[j] is true && substring(j, i) is in the wordDict (j = 0 means this substring doesn't need to cut)  // !!!!!!!!!!!!!
         = false, otherwise
     */
    public static boolean wordBreak3(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] M = new boolean[s.length() + 1];
        M[0] = true;
        for (int i = 1; i <= s.length(); i++) { // !!! <=
        	for (int j = 0; j < i; j++) {
        		if (M[j] && wordDictSet.contains(s.substring(j, i))) {
        			M[i] = true;
        			break;
        		}
        	}
        }
        
        return M[s.length()];
    }
    
    
    public static void main(String[] args) {
    	List<String> list = new ArrayList<>();
    	list.add("car");
    	list.add("ca");
    	list.add("rs");
    	System.out.println(wordBreak("cars", list));
    }
}
