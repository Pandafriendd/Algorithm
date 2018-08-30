/*
Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:
Input:
s: "cbaebabacd" p: "abc"
Output:
[0, 6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".

Example 2:
Input:
s: "abab" p: "ab"
Output:
[0, 1, 2]
Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
 */

import java.util.*;
public class FindAllAnagramsinaString {
	 
	 	// Find All Anagrams in a String
	 	public List<Integer> findAnagrams(String s, String t) {
	        List<Integer> result = new LinkedList<>();
	        if(t.length()> s.length()) return result;
	        Map<Character, Integer> map = new HashMap<>();
	        
	        /*
	        Consider the map as a counter of each type of characters and the counter represents the the number of the type of characters which are needed. 
	        Say n = map.get(c), n is the number of c which is required to constitute the string p. If n > 0, means that the number of character c is insufficient.
	         */
	        for(char c : t.toCharArray()){
	            map.put(c, map.getOrDefault(c, 0) + 1);
	        }
	        int counter = map.size();
	        
	        int begin = 0, end = 0;
	                
	        while(end < s.length()){
	            char c = s.charAt(end);
	            if( map.containsKey(c) ){
	                map.put(c, map.get(c)-1);
	                if(map.get(c) == 0) 
	                	counter--;
	            }
	            end++;
	            
	            while(counter == 0){
	                char tempc = s.charAt(begin);
	                if(map.containsKey(tempc)){
	                    map.put(tempc, map.get(tempc) + 1);
	                    if(map.get(tempc) > 0){
	                        counter++;
	                    }
	                }
	                if(end-begin == t.length()){
	                    result.add(begin);
	                }
	                begin++;
	            }
	            
	        }
	        return result;
	    }
	 	
	 	
	 	
	 	public List<Integer> slidingWindowTemplate(String s, String t) {
	        //init a collection or int value to save the result according the question.
	        List<Integer> result = new LinkedList<>();
	        if(t.length()> s.length()) return result;
	        
	        //create a hashmap to save the Characters of the target substring.
	        //(K, V) = (Character, Frequence of the Characters)
	        Map<Character, Integer> map = new HashMap<>();
	        for(char c : t.toCharArray()){
	            map.put(c, map.getOrDefault(c, 0) + 1);
	        }
	        //maintain a counter to check whether match the target string.
	        int counter = map.size();//must be the map size, NOT the string size because the char may be duplicate.
	        
	        //Two Pointers: begin - left pointer of the window; end - right pointer of the window
	        int begin = 0, end = 0;
	        
	        //the length of the substring which match the target string.
	        int len = Integer.MAX_VALUE; 
	        
	        //loop at the begining of the source string
	        while(end < s.length()){
	            
	            char c = s.charAt(end);//get a character
	            
	            if(map.containsKey(c)){
	                map.put(c, map.get(c)-1);// plus or minus one
	                if(map.get(c) == 0) 
	                	counter--;//modify the counter according the requirement(different condition).
	            }
	            end++;
	            
	            //increase begin pointer to make it invalid/valid again
	            while(counter == 0){ /* counter condition. different question may have different condition */	                
	                char tempc = s.charAt(begin);//***be careful here: choose the char at begin pointer, NOT the end pointer
	                if(map.containsKey(tempc)){
	                    map.put(tempc, map.get(tempc) + 1);//plus or minus one
	                    if(map.get(tempc) > 0) 
	                    	counter++;//modify the counter according the requirement(different condition).
	                }
	                
	                /* save / update(min/max) the result if find a target*/
	                // result collections or result int value
	                
	                begin++;
	            }
	        }
	        return result;
	 	}
}
