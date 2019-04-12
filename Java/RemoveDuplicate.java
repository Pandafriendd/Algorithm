
/*
Given a sorted array, remove all duplicated elements
eg. 11223 -> 123
 */

import java.util.*;


public class RemoveDuplicate {
	/*
	slow: all elements to the left side of the slow (excluding slow) are the results of the elements have been processed
	fast: the current index being processed. All elements to the right side of the fast (including fast) are the elements have not been processed
	 */
	public static int[] removeDup(int[] input) {
		if (input.length <= 1 || input == null) {
			return input;
		}
		
		int slow = 1;
		int fast = 1;
		
		while (fast < input.length) {
			if (input[fast] != input[slow - 1]) {
				input[slow++] = input[fast++];
			} else {
				fast++;
			}
		}
		
		return reSize(input, slow);
	}
	
	private static int[] reSize(int[] input, int slow) {
		int[] output = new int[slow];
		for (int i = 0; i < slow; i++) {
			output[i] = input[i];
		}
		
		return output;
	}
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(removeDup(new int[] {1,2,3,3})));
	}
}
