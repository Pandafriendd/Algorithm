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
	
	/*
	 app2: Using extra array: count = 0; encounter 1, then count++; encounter 0, then count--
	 If at any moment, the count becomes zero, it implies that we've encountered equal number of zeros and ones from the beginning till the current index of the array
	 another point to be noted is that if we encounter the same count twice while traversing the array, 
	 it means that the number of zeros and ones are equal between the indices corresponding to the equal count values
	 Another point to be noted is that if we keep a track of the indices corresponding to the same count values that lie farthest apart, 
	 we can determine the size of the largest subarray with equal no. of zeros and ones easily.
	 
	 time:O(n)  space:O(2n)
	 */
	public int findMaxLength2(int[] nums) {
		int[] arr = new int[2 * nums.length + 1];
		Arrays.fill(arr, -2);
		arr[nums.length] = -1;
		int maxlen = 0, count = 0;
		for(int i = 0; i < nums.length; i++) {
			count = count + (nums[i] == 1 ? 1 : -1);
			if(arr[count + nums.length] >= -1) {
				maxlen = Math.max(maxlen, i - arr[count + nums.length]);
			}
			else {
				arr[count + nums.length] = i;
			}
		}
		return maxlen;
	}
	
	/*
	 app3: hashmap
	 we make use of a HashMap to store count ==> index 
	 We make an entry for a count in the map whenever the count is encountered first, 
	 and later on use the corresponding index to find the length of the largest subarray with equal no. of zeros and ones when the same count is encountered again.
	 
	 time: O(n)   space: O(n)
	 */
	public int findMaxlength3(int[] nums) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int count = 0;
		int maxlen = 0;
		map.put(0, -1);  //!!!!!! dealing with the case that when there are only two elements and count = 0. also means if we haven't included any numbers in the given array, the number of 1s = the number of 0s, and the index we can get this key is -1.
		for(int i = 0; i < nums.length; i++) {
			count = count + (nums[i] == 1 ? 1 : -1);
			if(map.containsKey(count)) {
				maxlen = Math.max(maxlen, i - map.get(count));
			}
			else
				map.put(count, i);
		}
		
		return maxlen;
	}
}
