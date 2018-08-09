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

time: O(2n). In the worst case each character will be visited twice by i and j.  space: O(n)
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
    
    // approach 3: optimized sliding window
    /*
     * Instead of using a set to tell if a character exists or not, 
     * we could define a mapping of the characters to its index. 
     * Then we can skip the characters immediately when we found a repeated character.
     * 
     * The reason is that if s[j] have a duplicate in the range [i, j)[i,j) with index j', 
     * we don't need to increase i little by little. We can skip all the elements in the range [i, j'] and let i to be j' + 1 directly.
     */
    /**
     * Traverse the string
     * Get current character
     * Update start point
     * Update max
     * Put current char in map
     * time: O(n) space:O(n)
     */
    public static int lengthOfLongestSubstring222(String s) {
        if (s == null || s.length() == 0) return 0;
        
        Map<Character, Integer> map = new HashMap<Character, Integer>(); // current index of character
        int start = 0;
        int max = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            start = Math.max(start, (map.containsKey(c)) ? map.get(c) + 1 : 0); /*start point can be recent dup's next char or last start*/
            max = Math.max(max, i - start + 1); /*if current str len is bigger then update*/
            map.put(c, i); /*add char to map with index*/
        }
        return max;
    }
}
