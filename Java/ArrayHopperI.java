/*
Given an array A of non-negative integers, you are initially positioned at index 0 of the array. 
A[i] means the maximum jump distance from that position (you can only jump towards the end of the array). 
Determine if you are able to reach the last index.

Assumptions

The given array is not null and has length of at least 1.
Examples

{1, 3, 2, 0, 3}, we are able to reach the end of array(jump to index 1 then reach the end of the array)

{2, 1, 1, 0, 2}, we are not able to reach the end of array
 */


public class ArrayHopperI {
	// DP from the last position
	// M[i] represents if we are able to jump to the last index from index i
	// base case: M[array,length - 1] = true;
	// induction rules: M[i] = true if any reachable M[i + j] is true, 1 <= j <= i + array[i], otherwise, M[i] = false
	// time: O(n^2)
	public boolean canJump(int[] array) {
		boolean[] M = new boolean[array.length];
		M[array.length - 1] = true;
		for (int i = array.length - 2; i >= 0; i--) {
			for(int j = 1; j <= array[i]; j++) {
				if (M[i + j] == true) {  // !!!
					M[i] = true;
					break;
				}
			}
		}
		
		return M[0];
	}
	
	// Greedy O(n)
	public boolean canJump2(int[] array) {
		int maxCanReach = array[0];
		for (int i = 1; i < array.length; i++) {
			if (maxCanReach >= i) {
				maxCanReach = Math.max(maxCanReach, i + array[i]); 
			}
		}
		
		return (maxCanReach >= array.length - 1) ? true : false;
	}
	
}
