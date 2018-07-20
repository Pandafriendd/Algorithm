/*
 * Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.
 * Input: "aab" 
 * Output: 
 * [ 
 * 	["aa","b"], 
 * 	["a","a","b"] 
 * ]
 */

import java.util.*;
public class PalindromePartitioning {
	 public List<List<String>> partition(String s) {
	        List<List<String>> res = new ArrayList<>();
	        helper(res, new ArrayList<>(), s, 0);
	        return res;
	    }
	    
	    
	    private void helper(List<List<String>> res, ArrayList<String> tempList, String s, int start){
	        if(start == s.length())
	            res.add(new ArrayList<>(tempList));
	        else{
	            for(int i = start; i < s.length(); i++){
	                if(isPalindrome(s, start, i)){
	                    tempList.add(s.substring(start, i + 1)); // !!
	                    helper(res, tempList, s, i + 1);
	                    tempList.remove(tempList.size() - 1);
	                }
	            }
	        }
	    }
	    
	    private boolean isPalindrome(String s, int start, int end){
	        while(start < end){
	            if(s.charAt(start++) != s.charAt(end--))
	                return false;
	        }
	        return true;
	    }
}
