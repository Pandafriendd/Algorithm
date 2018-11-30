/*
Given an unsorted integer array, find the subarray that has the greatest sum. Return the sum.

Assumptions

The given array is not null and has length of at least 1.
Examples

{2, -1, 4, -2, 1}, the largest subarray sum is 2 + (-1) + 4 = 5

{-2, -1, -3}, the largest subarray sum is -1
 */
public class LargestSubArraySum {
	
	/*
	  DP 1
	  M[i] represents the greatest sum (from 0-th index to the i-th index), which include i-th index (the current index)
	  base case: M[0] = array[0]
	  induction rule: M[i] = M[i - 1] + array[i], if M[i - 1] >= 0
	  				       = array[i], otherwise
	  time: O(n), space: O(n)
	 */
	public int largestSum(int[] array) { 
		int[] M = new int[array.length];
		int max = array[0];
		M[0] = array[0];
		for (int i = 1; i < array.length; i++) {
			if (M[i - 1] >= 0) {
				M[i] = M[i - 1] + array[i];
			} else {
				M[i] = array[i];
			}
			max = Math.max(max, M[i]);
		}
		
		return max;
	}
	
	// optimized space, lastSum is M[i - 1]
	public int largestSum0(int[] array) { 
		int lastSum = array[0];
		int max = array[0];
		for (int i = 1; i < array.length; i++) {
			if (lastSum >= 0) {
				lastSum = lastSum + array[i];
			} else {
				lastSum = array[i];
			}
			max = Math.max(max, lastSum);
		}
		
		return max;
	}
	
	// return the start index and the end index of the largest sum subarray
	// need several variables: localStart, localEnd, globalStart, globalEnd
	// localStart = i when last < 0
	// localEnd = current index i
	// globalStart = localStart when max updates
	// globalEnd = localEnd when max updates
	
	// Return the result in a array as [sum, left, right]
	public int[] largestSum00(int[] array) { 
		int last = array[0];
		int cur = 0;
		int max = array[0];
		int localStart = 0;
		int globalStart = 0;
		int globalEnd = 0;
		for (int i = 1; i < array.length; i++) {  // i is localEnd
			if (last >= 0) {
				cur = last + array[i];
			} else {
				cur = array[i];
				localStart = i;
			}
			if (max < cur) {
				max = cur;
				globalStart = localStart;
				globalEnd = i;
			}
			last = cur;
		}
		
		return new int[] {max, globalStart, globalEnd};
	}
	
	// using prefix sum
	// sum[i..j] = prefix[j] - prefix[i] + array[i] 
	//  Wrong!!!!! since when min change, array[minIndex] also change. We should case sum[i..j] = prefix[j] - prefix[i - 1] and handle the edge case
	public int largestSum000(int[] array) { 
		int[] prefix = new int[array.length];
		prefix[0] = array[0];
		for (int i = 1; i < array.length; i++) {
			prefix[i] = prefix[i - 1] + array[i];
		}
		
		int max = prefix[0];
		int min = prefix[0];
		int minIndex = 0;
		for (int i = 1; i < prefix.length; i++) {
			if (min > prefix[i]) {
				min = prefix[i];
				minIndex = i;
			}
			max = Math.max(max, prefix[i] - min + array[minIndex]);  // Wrong!!!
		}
		return max;
	}
	
	
}
