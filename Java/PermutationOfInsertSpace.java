/*
given a string, we can insert at most one empty space between each pair of adjacent letters
Input str = "ABC"
Output:
"ABC"
"A BC"
"AB C"
"A B C"
 
 */

import java.util.*;
public class PermutationOfInsertSpace {
	/*
	e.g.: str = "ABC"
	                 A
	            /        \
	           A_B        AB
	          /   \      /   \
	       A_B_C  A_BC  AB_C  ABC
	
	time complexity: O(2^n), n is the length of input str
	space complexity: O(n)
	 */
	
	public static List<String> spacePermutation(String str) {
		StringBuilder sb = new StringBuilder();
		List<String> res = new ArrayList<>();
		sb.append(str.charAt(0));
		helper(str, sb, res, 1);
		return res;
	}
	
	private static void helper(String str, StringBuilder sb, List<String> res, int index) {
		// base case
		if (index == str.length()) {
			res.add(sb.toString());
			return;
		}
		
		// recursion rules
		// pick " "
		sb.append(" ");
		sb.append(str.charAt(index));
		helper(str, sb, res, index + 1);
		sb.deleteCharAt(sb.length() - 1);
		sb.deleteCharAt(sb.length() - 1);
		
		// not pick " "
		sb.append(str.charAt(index));
		helper(str, sb, res, index + 1);
		sb.deleteCharAt(sb.length() - 1);
	}
	
	public static void main(String[] args) {
		System.out.println(spacePermutation("ABC"));
	}
	
	
}
