/*
Given a list of non-negative numbers and a target integer k, 
write a function to check if the array has a continuous subarray of size at least 2 that sums up to the multiple of k, that is, sums up to n*k where n is also an integer.

Example 1:
Input: [23, 2, 4, 6, 7],  k=6
Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.

Example 2:
Input: [23, 2, 6, 4, 7],  k=6
Output: True
Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.

Note:
The length of the array won't exceed 10,000.
You may assume the sum of all the numbers is in the range of a signed 32-bit integer.


Q:
1. whether k can be zero?

Some damn it! test cases:
[0], 0 -> false;
[5, 2, 4], 5 -> false;
[0, 0], 100 -> true;
[1,5], -6 -> true;
etc...

 */

import java.util.*;
public class ContinuousSubarraySum {
	
	// brute force
	//consider every possible subarray of size greater than or equal to 2, find out its sum by iterating over the elements of the subarray, 
	// and then we check if the sum obtained is an integer multiple of the given k.
	//time: O(n^3) space: O(1)
	public boolean checkSubarraySum(int[] nums, int k) {
		for (int start = 0; start < nums.length - 1; start++) {
            for (int end = start + 1; end < nums.length; end++) {
                int sum = 0;
                for (int i = start; i <= end; i++)
                    sum += nums[i];
                if (sum == k || (k != 0 && sum % k == 0))
                    return true;
            }
        }
        return false;
    }
	
	
	/*
	Better Brute Force
	if we make use of an array sum that stores the cumulative sum of the elements of the array, 
	such that sum[i] stores the sum of the elements upto the ith ​​element of the array.
	To determine the sum of elements from the i ​th ​​ index to the j ​th ​​ index, including both the corners, 
	we can use: sum[j] - sum[i] + nums[i]
	
	Time: O(n^2) Space: O(n)
	 */
	public boolean checkSubarraySum2(int[] nums, int k) {
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++)
            sum[i] = sum[i - 1] + nums[i];
        for (int start = 0; start < nums.length - 1; start++) {
            for (int end = start + 1; end < nums.length; end++) {
                int summ = sum[end] - sum[start] + nums[start];
                if (summ == k || (k != 0 && summ % k == 0))
                    return true;
            }
        }
        return false;
    }
	
	/*
	Using HashMap
	Map:  cumulative sums upto the ith ==> index i 
	Time: O(n) Space: O(min(n, k))​​
	 */
	public boolean checkSubarraySum3(int[] nums, int k) {
        int sum = 0;
        HashMap <Integer, Integer> map = new HashMap < > ();
        map.put(0, -1);  // deal with sum of first 2 or >= 2 elements is the result
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0)
                sum = sum % k;
            if (map.containsKey(sum)) {
                if (i - map.get(sum) > 1) // should have at least 2 elements
                    return true;
            } 
            else // must have else!!!
                map.put(sum, i);
        }
        return false;
    }
}
