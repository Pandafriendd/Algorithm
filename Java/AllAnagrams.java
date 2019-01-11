/*
Find all occurrence of anagrams of a given string s in a given string l. Return the list of starting indices.

Assumptions

s is not null or empty.
l is not null.
Examples

l = "abcbac", s = "ab", return [0, 3] since the substring with length 2 starting from index 0/3 are all anagrams of "ab" ("ab", "ba").
*/

import java.util.*;

public class AllAnagrams {
	
	/*
	 * sh length is m, lo length is n
	 * the # of n-length substring in a m-length longer string is: n - m + 1
	 * using two hashmaps, one for storing the frequency of shorter string, and one for the sliding window of longer string
	 * time: O(m + m + (n - m + 1) * m)  ==> O(m * n)
	 * space: O(2m)
	 */
	public static List<Integer> allAnagrams(String sh, String lo) {
	    List<Integer> res = new ArrayList<>();
	    if (sh.length() > lo.length()) {
	      return res;
	    }
	    
	    Map<Character, Integer> map = new HashMap<>();
	    for (int i = 0; i < sh.length(); i++) {
	      char c = sh.charAt(i);
	      map.put(c, map.getOrDefault(c, 0) + 1);
	    }
	    
	    Map<Character, Integer> window = new HashMap<>();
	    int start = 0;
	    int end = sh.length() - 1;
	    for (int i = 0; i <= sh.length() - 1; i++) {
	      char c = lo.charAt(i);
	      window.put(c, window.getOrDefault(c, 0) + 1);
	    }
	    
	    while (end < lo.length()) {
	      boolean isMatch = true;
	      for (Map.Entry<Character, Integer> entry : map.entrySet()) {
	        if (window.get(entry.getKey()) == null || window.get(entry.getKey()) != entry.getValue()) {
	          isMatch = false;
	          break;
	        }
	      }
	      if (isMatch) {
	        res.add(start);
	      }
	     
	      window.put(lo.charAt(start), window.get(lo.charAt(start)) - 1);
	      start++;
	      end++;
	      if (end != lo.length()) {
	    	  window.put(lo.charAt(end), window.getOrDefault(lo.charAt(end), 0) + 1);
	      }
	      
	    }
	    
	    return res;
	}
	
	
	/*
	 * sh length is m, lo length is n
	 * time: O(3m + (n - m + 1))  ==> O(m + n)
	 * space: O(2m)
	 */
	public static List<Integer> allAnagrams2(String sh, String lo) {
		List<Integer> res = new ArrayList<>();
	    if (sh.length() > lo.length()) {
	      return res;
	    }
	    
	    Map<Character, Integer> map = new HashMap<>();
	    for (int i = 0; i < sh.length(); i++) {
	      char c = sh.charAt(i);
	      map.put(c, map.getOrDefault(c, 0) + 1);
	    }
	    
	    Map<Character, Integer> window = new HashMap<>();
	    int start = 0;
	    int end = sh.length() - 1;
	    int numberOfMatch = 0;
	    for (int i = 0; i <= sh.length() - 1; i++) {
		      char c = lo.charAt(i);
		      window.put(c, window.getOrDefault(c, 0) + 1);
		}
	    for (Map.Entry<Character, Integer> entry : map.entrySet()) {
	        if (window.get(entry.getKey()) != null && window.get(entry.getKey()) == entry.getValue()) {
	        	numberOfMatch++;
	        }
	    }
	    while (end < lo.length()) {
	      if (numberOfMatch == map.size()) {
	    	  res.add(start);
	      }
	      window.put(lo.charAt(start), window.get(lo.charAt(start)) - 1);
	      if (map.get(lo.charAt(start)) != null && window.get(lo.charAt(start)) + 1 == map.get(lo.charAt(start))) {
	    	  numberOfMatch--;
	      }
	      if (window.get(lo.charAt(start)) == map.get(lo.charAt(start))) {
	    	  numberOfMatch++;
	      }
	      start++;
	      end++;
	      if (end != lo.length()) {
	    	  window.put(lo.charAt(end), window.getOrDefault(lo.charAt(end), 0) + 1);
	    	  if (map.get(lo.charAt(end)) != null && window.get(lo.charAt(end)) - 1 == map.get(lo.charAt(end))) {
		    	  numberOfMatch--;
		      }
	    	  if (window.get(lo.charAt(end)) == map.get(lo.charAt(end))) {
		    	  numberOfMatch++;
		      }
	      }
	    }
	    
	    return res;
	}
	
	public static void main(String[] args) {
		System.out.println(allAnagrams2("aab", "ababacbbaac"));
	}
	
}
