
/*
Given an unsorted array, find the length of the longest subarray in which the numbers are in ascending order.

Assumptions

The given array is not null
Examples

{7, 2, 3, 1, 5, 8, 9, 6}, longest ascending subarray is {1, 5, 8, 9}, length is 4.

{1, 2, 3, 3, 4, 4, 5}, longest ascending subarray is {1, 2, 3}, length is 3.
 */

public class LongestAscendingSubArray {
	public int longest(int[] array) {
	    if (array == null || array.length == 0) {
	      return 0;
	    }
	    
	    // f[i] is the max length of subarray from 0-th element to i-th element, including i
	    int[] f = new int[array.length];
	    f[0] = 1;
	    int maxLen = f[0];
	    for (int i = 1; i < array.length; i++) {
	      if (array[i] > array[i - 1]) {
	        f[i] = f[i - 1] + 1;
	      } else {
	        f[i] = 1;
	      }
	      maxLen = Math.max(maxLen, f[i]);
	    }
	    
	    return maxLen;
	}
}
