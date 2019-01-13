import java.util.*;

public class Anagrams {
	public static List<Integer> allAnagrams(String s, String l) {
		List<Integer> res = new ArrayList<>();
		if (s.length() > l.length()) {
			return res;
		}
		// the countMap represents how many chars are still needed in order to match s's anagram
		Map<Character, Integer> countMap = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);	
			countMap.put(c, countMap.getOrDefault(c, 0) + 1);
		}
		int numberOfMatch = 0;
		int numberOfNeeded = 0;
		int start = 0;
		int end = 0;

		while (end <= l.length()) {
			if (numberOfMatch == countMap.size()) { // !!!
				res.add(start);
			}
			if (end == l.length()) {  // !!!
				break;
			}

			// move the window foward (remove the leftmost char and add the right most char)
			// needed to move start pointer forward
			if (end >= s.length()) {  // !
				if (countMap.get(l.charAt(start)) != null) { 
					numberOfNeeded = countMap.get(l.charAt(start));
					numberOfNeeded++;
					countMap.put(l.charAt(start), numberOfNeeded);
					if (numberOfNeeded == 1) {
						numberOfMatch--;
					}
				}
				start++;
			}
			if (countMap.get(l.charAt(end)) != null) {
				numberOfNeeded = countMap.get(l.charAt(end));
				numberOfNeeded--;
				countMap.put(l.charAt(end), numberOfNeeded);
				if (numberOfNeeded == 0) {
					numberOfMatch++;
				}
			}
			end++;
		}

		return res;
	}
	
	public static void main(String[] args) {
		System.out.println(allAnagrams("ab", "abba"));
	}
}
