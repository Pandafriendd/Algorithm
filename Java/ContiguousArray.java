/*
 Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

Example 1:
Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
Example 2:
Input: [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
Note: The length of the given binary array will not exceed 50,000.
 */

import java.util.*;
public class ContiguousArray {
	
	/*
	 app1: brute force
	 */
	public int findMaxLength(int[] nums) {
		int maxlen = 0;
		for(int start = 0; start < nums.length; start++) {
			int zeros = 0, ones = 0;
			for(int end = start; end < nums.length; end++) {
				if(nums[end] == 0)
					zeros++;
				else
					ones++;
				if(zeros == ones)
					maxlen = Math.max(end - start + 1, maxlen); 
			}
		}
		
		return maxlen;
	}
}
