/*
Given an unsorted integer array, remove adjacent duplicate elements repeatedly, from left to right. For each group of elements with the same value do not keep any of them.

Do this in-place, using the left side of the original array. Return the array after deduplication.

Assumptions

The given array is not null
Examples

{1, 2, 3, 3, 3, 2, 2} → {1, 2, 2, 2} → {1}, return {1}
*/

public class Solution {
  public int[] dedup(int[] input) {
    if (input.length <= 1 || input == null) {
			return input;
		}
		
		int slow = 0;  // elements to the left of slow are processed (excluding) AND input[slow - 1] is the top element of the stack. If stack is empty, slow = 0
		int fast = 0;  // current index
		
		while (fast < input.length) {
			if (slow == 0 || input[slow - 1] != input[fast]) {
				input[slow++] = input[fast++];  // !!!
			} else { // ignore all consecutive duls and remove the top ele of stack
				while (fast < input.length && input[fast] == input[slow - 1]) {
					fast++;
				}
				// now fast points to the first element that doesn't equal to top of stack
				slow--;
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
