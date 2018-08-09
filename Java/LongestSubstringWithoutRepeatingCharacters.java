/*
 Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */

import java.util.*;
public class LongestSubstringWithoutRepeatingCharacters {
	
	// approach 1: brute force time: O(n^3)  space: O(min(n, m))
	public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        int ans = 0;
        for (int i = 0; i < len; i++)
            for (int j = i + 1; j <= len; j++) // !!
                if (allUnique(s, i, j)) 
                	ans = Math.max(ans, j - i);
        return ans;
    }

    public boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch)) 
            	return false;
            set.add(ch);
        }
        return true;
    }
    
    // approach 2: Sliding window
    /*
    If a substring sij from index i to j−1 is already checked to have no duplicate characters. 
    We only need to check if s[j] is already in the substring sij.

To check if a character is already in the substring, we can scan the substring, which leads to an O(n^2) algorithm. But we can do better.

By using HashSet as a sliding window, checking if a character in the current can be done in O(1).

A sliding window is an abstract concept commonly used in array/string problems. 
A window is a range of elements in the array/string which usually defined by the start and end indices

We use HashSet to store the characters in current window [i, j)(j = i initially). 
Then we slide the index j to the right. If it is not in the HashSet, we slide j further. 
Doing so until s[j] is already in the HashSet. 
At this point, we found the maximum size of substrings without duplicate characters start with index i. 
If we do this for all i, we get our answer.

time: O(n)  
     */
    
    public int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++)); //!!!!
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++)); //!!!!
            }
        }
        return ans;
    }
}
