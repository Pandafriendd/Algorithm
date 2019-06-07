/*
Given a sorted integer array, remove duplicate elements. 
For each group of elements with the same value keep only one of them. 
Do this in-place, using the left side of the original array and maintain the relative order of the elements of the array. 
Return the array after deduplication.

Assumptions

The array is not null
Examples

{1, 2, 2, 3, 3, 3} → {1, 2, 3}
*/
public class Solution {
  public static int[] dedup(int[] input) {
   if (input.length <= 1 || input == null) {
			return input;
   }
		
		int slow = 1;
		int fast = 1;
		
		while (fast < input.length) {
			if (input[fast] != input[slow - 1]) {  // keep it (copy)
				input[slow++] = input[fast++];
			} else {  // ignore it
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
