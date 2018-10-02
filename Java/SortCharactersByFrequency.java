/*
Given a string, sort it in decreasing order based on the frequency of characters.

Example 1:
Input:
"tree"
Output:
"eert"
Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.

Example 2:
Input:
"cccaaa"
Output:
"cccaaa"
Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.

Example 3:
Input:
"Aabb"
Output:
"bbAa"
Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.
 */

import java.util.*;
import java.util.Map.Entry;
public class SortCharactersByFrequency {
	
	// using bucket sort
	public String frequencySort(String s) {
        if(s == null || s.length() == 0) {
            return "";
        }
        
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        // Character[] is not enough since there may be mutiple chars have same freq
        List<Character>[] bucket = new ArrayList[s.length() + 1]; // freq possible from one to s.length
        for(Map.Entry<Character, Integer> entry : map.entrySet()) {
            if(bucket[entry.getValue()] != null) {
                List<Character> list = bucket[entry.getValue()];  // !! should be List<> not ArrayList<>
                list.add(entry.getKey());
            }
            else {
                bucket[entry.getValue()] = new ArrayList<>(); // new ArrayList<>(entry.getKey()); is not right
                bucket[entry.getValue()].add(entry.getKey());
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = bucket.length - 1; i >= 0; i--) {
            if(bucket[i] != null) {
                List<Character> list = bucket[i];
                for(char c : list) {
                    /*int count = i;
                    while(count > 0) {
                        sb.append(c);
                        count--;
                    }*/
                    for (int j = 0; j < map.get(c); j++)
                        sb.append(c);
                }
            }
        }
        
        return sb.toString();
        
    }
	
	
	// using priority queue
	public String frequencySort2(String s) {
        if(s == null || s.length() == 0) {
            return "";
        }
        
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        pq.addAll(map.entrySet());
				
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            Map.Entry<Character, Integer> entry = pq.poll();
            for (int i = 0; i < (int)entry.getValue(); i++) 
                sb.append(entry.getKey());
        }
        return sb.toString();
	}
}
