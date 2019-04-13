
/*
Given a sorted array, remove all duplicated elements
eg. 1,1,2,2,3 -> 1,2,3
 */

import java.util.*;


public class RemoveDuplicate {
	/*
	slow: all elements to the left side of the slow (excluding slow) are the results of the elements have been processed
	fast: the current index being processed. All elements to the right side of the fast (including fast) are the elements have not been processed
	
	initialize:
	slow = 1, fast = 1
	
	case 1: input[fast] != input[slow - 1]: keep the current: input[slow] = input[fast]; slow++; fast++;
	case 2: input[fast] == input[slow - 1]: ignore the current: fast++;
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
		System.out.println(Arrays.toString(removeAllDup(new int[] {1,1,1,2,2,3})));
	}
	
	/*
	Follow-up no.1
	
	Given a sorted array, remove all more than two duplicated elements so that only keep at most two duplicates
	
	slow: all elements to the left side of the slow (excluding slow) are the results of the elements have been processed
	fast: the current index being processed. All elements to the right side of the fast (including fast) are the elements have not been processed
	
	initialize:
	slow = 1, fast = 1
	
	case 1: input[fast] != input[slow - 2]: (either the first unique element or the second duplicate), so keep the current: input[slow] = input[fast]; slow++; fast++;
	case 2: input[fast] == input[slow - 2]: (guarantee input[fast] == input[slow - 1]), so ignore the current: fast++;
	 */
	
	/*
	Follow-up no.2
	
	Given a sorted array, remove all duplicated elements so that only non-duplicated elements left
	eg. 1,1,1,2,2,3 -> 3
	
	slow: all elements to the left side of the slow (excluding slow) are the results of the elements have been processed
	fast: the current index being processed. All elements to the right side of the fast (including fast) are the elements have not been processed
	beginFlag: a helper pointer to record the potential duplicate element
	
	initialize:
	slow = 0, fast = 0
	 */
	public static int[] removeAllDup(int[] input) {
		if (input.length <= 1 || input == null) {
			return input;
		}
		
		int slow = 0;
		int fast = 0;
		int beginFlag;
		
		while (fast < input.length) {
			beginFlag = fast; // !!!
			while (fast < input.length && input[fast] == input[beginFlag]) {
				fast++;
			}
			// now fast points to the first element after beginFlag that doesn't equal to input[beginFlag]
			if (fast - beginFlag == 1) {
				input[slow++] = input[beginFlag];
			}
		}
		
		return reSize(input, slow);
	}
}
