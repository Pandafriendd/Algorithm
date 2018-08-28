/*
Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
Example 1:
Input: "aba"
Output: True

Example 2:
Input: "abca"
Output: True
Explanation: You could delete the character 'c'.

Note:
The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 */

import java.util.*;
public class ValidPalindromeII {
	/*
	 * brute force
	 * For each index i in the given string, let's remove that character, then check if the resulting string is a palindrome. 
	 * If it is, then we'll return true
	 * 
	 * time: O(n^2)
	 */
	public boolean validPalindrome(String s) {
		if(isPalindrome(s))
			return true;
        StringBuilder sb = new StringBuilder(s);
        for(int i = 0; i < s.length(); i++) {
        	Character c = s.charAt(i);
        	sb.deleteCharAt(i);
        	if(isPalindrome(sb.toString()))
        		return true;
        	sb.insert(i, c);
        }
        return false;
    }
	
	private boolean isPalindrome(String s) {
		int start = 0, end = s.length() - 1;
		while(start < end) {
			if(s.charAt(start++) != s.charAt(end--))
				return false;
		}
		return true;
	}
	
	/*
	 * greedy
	 * 
	 * Intuition:
	 *  If the beginning and end characters of a string are the same (ie. s[0] == s[s.length - 1]), 
	 *  then whether the inner characters are a palindrome (s[1], s[2], ..., s[s.length - 2]) uniquely determines whether the entire string is a palindrome.
	 * 
	 * Algorithm:
	 *  Suppose we want to know whether s[i], s[i+1], ..., s[j] form a palindrome. 
	 *  If i >= j then we are done. If s[i] == s[j] then we may take i++; j--. 
	 *  Otherwise (s[i] != s[j]), the palindrome must be either s[i+1], s[i+2], ..., s[j] or s[i], s[i+1], ..., s[j-1], and we should check both cases.
	 *  
	 *  testcases: abcde, abbae, abbaa, bbbbb, bbbbbb
	 *  
	 *  time: O(n) space: O(1)
	 */ 
	public boolean validPalindrome2(String s) {
		for(int start = 0; start < s.length() / 2; start++) {
			int end = s.length() - 1 - start; // !!!
			if(s.charAt(start) != s.charAt(end)) {
				return isPalindromeRange(s, start + 1, end) || isPalindromeRange(s, start, end - 1);  // implicitly complete delete one char (from start or end)
			}
		}
		return true;
	}
	
	private boolean isPalindromeRange(String s, int start, int end) {
		while(start < end) {
			if(s.charAt(start++) != s.charAt(end--))
				return false;
		}
		return true;
	}
}
