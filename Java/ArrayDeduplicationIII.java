/*
Given a sorted integer array, remove duplicate elements. For each group of elements with the same value do not keep any of them. Do this in-place, using the left side of the original array and and maintain the relative order of the elements of the array. Return the array after deduplication.

Assumptions

The given array is not null
Examples

{1, 2, 2, 3, 3, 3} → {1}
*/

public class Solution {
  public int[] dedup(int[] input) {
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
  
  private static int[] reSize(int[] input, int slow) {
		int[] output = new int[slow];
		for (int i = 0; i < slow; i++) {
			output[i] = input[i];
		}
		
		return output;
	}
}
