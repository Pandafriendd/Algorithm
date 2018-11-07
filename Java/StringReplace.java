/*
Given an original string input, and two strings S and T, replace all occurrences of S in input with T.

Assumptions

input, S and T are not null, S is not empty string
Examples

input = "appledogapple", S = "apple", T = "cat", input becomes "catdogcat"
input = "dodododo", S = "dod", T = "a", input becomes "aoao"
 */

import java.util.*;

public class StringReplace {	
	// method 1, not using any String and StringBuilder utility
	public String replace(String input, String source, String target) {
		char[] array = input.toCharArray();
		
		if (source.length() >= target.length()) {
			return replaceWithShorter(array, source, target);  // length decrease, replace from start to end
		}
		else {
			return replaceWithLonger(array, source, target);   // length increase, replace from end to start
		}
	}
	
	// copy from beginning
	public String replaceWithShorter(char[] array, String s, String t) {
		int slow = 0;
		int fast = 0;
		
		while (fast < array.length) {
			if (fast <= array.length - s.length() && equalSubstring(array, fast, s)) {
				copySubstring(array, slow, t);
				slow += t.length();
				fast += s.length();
			} else {
				array[slow++] = array[fast++];
			}
		}
		
		return new String(array, 0, slow);
	}
	
	// copy from end
	// first determine the increasing slots for the result string
	public String replaceWithLonger(char[] array, String s, String t) {
		// return all the end index of matching substring in array
		List<Integer> matches = getAllMatches(array, s);
		char[] res = new char[array.length + matches.size() * (t.length() - s.length())];
		int fast = array.length - 1;
		int slow = res.length - 1;
		int lastIndex = matches.size() - 1;
		
		while (fast >= 0) {
			if (lastIndex >= 0 && fast == matches.get(lastIndex)) {
				copySubstring(res, slow - t.length() + 1, t);
				slow -= t.length();
				fast -= s.length();
				lastIndex--;
			} else {
				res[slow--] = array[fast--];
			}
		}
		
		return new String(res);
	}
	
	private boolean equalSubstring(char[] array, int fromIndex, String s) {
		for (int i = 0; i < s.length(); i++) {
			if (array[fromIndex + i] != s.charAt(i)) {
				return false;
			}
		}
		
		return true;
	}
	
	private void copySubstring(char[] array, int fromIndex, String t) {
		for (int i = 0; i < t.length(); i++) {
			array[fromIndex + i] = t.charAt(i);
		}
	}
	
	private List<Integer> getAllMatches(char[] array, String s) {
		List<Integer> matches = new ArrayList<>();
		int i = 0;
		while (i <= array.length - s.length()) {
			if (equalSubstring(array, i, s)) {
				matches.add(i + s.length() - 1);
				i += s.length();
			} else {
				i++;
			}
		}
		
		return matches;
	}
	
	
	// method 2, using StringBuilder utility and String indexOf(), not using String replace()
	public String replace2(String input, String source, String target) {
		StringBuilder sb = new StringBuilder();
		int fromIndex = 0;
		int matchIndex = input.indexOf(source, fromIndex);
		while (matchIndex != -1) {
			sb.append(input, fromIndex, matchIndex).append(target);
			fromIndex = matchIndex + source.length();
			matchIndex = input.indexOf(source, fromIndex);
		}
		
		sb.append(input, fromIndex, input.length());
		return sb.toString();
	}
}
