/*
Given a sorted integer array, remove duplicate elements. For each group of elements with the same value keep at most two of them. Do this in-place, using the left side of the original array and maintain the relative order of the elements of the array. Return the array after deduplication.

Assumptions

The given array is not null
Examples

{1, 2, 2, 3, 3, 3} → {1, 2, 2, 3, 3}
*/

public class Solution {
  public int[] dedup(int[] input) {
    if (input.length <= 2 || input == null) {
			return input;
		}
		
		int slow = 2;
		int fast = 2;
		
		while (fast < input.length) {
			if (input[fast] != input[slow - 2]) {
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
}
