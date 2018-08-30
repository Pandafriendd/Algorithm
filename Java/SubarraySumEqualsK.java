/*
Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2
Note:
The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 */

import java.util.*;
public class SubarraySumEqualsK {
	
	// app1: brute force
	// time: O(n^3), considering every possible subarray takes O(n^2) time. For each of the subarray, we calculate the sum taking O(n) time in the worst case
	// space: O(1)
	public int subarraySum(int[] nums, int k) {
		int count = 0;
        for (int start = 0; start < nums.length; start++) {
            for (int end = start + 1; end <= nums.length; end++) {  // end <= nums.length  !!!=
                int sum = 0;
                for (int i = start; i < end; i++)
                    sum += nums[i];
                if (sum == k)
                    count++;
            }
        }
        return count;
    }
	
	/*
	app 2 Using Cummulative sum
	In this implementation, we make use of a cumulative sum array, sum, 
	such that sum[i] is used to store the cumulative sum of nums array upto the element corresponding to the (i−1)th index. 
	Thus, to determine the sum of elements for the subarray nums[i:j], we can directly use sum[j+1]−sum[i].
	
	time: O(n^2) space: O(n) Cumulative sum array sum of size n+1 is used.
	 */
	public int subarraySum2(int[] nums, int k) {
		int count = 0;
		int[] sum = new int[nums.length + 1];  // sum[i]: sum of index from 0 to i-1
		sum[0] = 0;
		for(int i = 1; i <= nums.length; i++) {
			sum[i] = sum[i - 1] + nums[i - 1];
		}
		
		for(int start = 0; start < nums.length; start++) {
			for(int end = start + 1; end <= nums.length; end++) {
				if(sum[end] - sum[start] == k)  //subarray nums[start : (end - 1)]
					count++;
			}
		}
		return count;
	}
	
	/*
	app 3: Without space
	time: O(n^2)  space: O(1)
	 */
	public int subarraySum3(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            int sum=0;
            for (int end = start; end < nums.length; end++) {
                sum+=nums[end];
                if (sum == k)
                    count++;
            }
        }
        return count;
    }
	
	/*
	app 4: using hashmap
	if the cumulative sum upto two indices, say i and j is at a difference of k i.e. if sum[i]−sum[j]=k, the sum of elements lying between indices i and j is k.
	HashMap used to store the cumulative sum upto all the indices possible along with the number of times the same sum occurs: sumi ==> no. of occurrences of sumi
	
	We traverse over the array nums and keep on finding the cumulative sum. 
	Every time we encounter a new sum, we make a new entry in the hashmap corresponding to that sum. 
	If the same sum occurs again, we increment the count corresponding to that sum in the hashmap. 
	Further, for every sum encountered, we also determine the number of times the sum sum−k has occured already, 
	since it will determine the number of times a subarray with sum k has occured upto the current index. We increment the count by the same amount.
	
	time: O(n)   space: O(n)
	 */
	public int subarraySum4(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap < Integer, Integer> map = new HashMap < > ();
        map.put(0, 1); // !!!!!!! means that if no elements are included the sum is 0. It is necessary when the sum of contiguous elements from beginning till some index i is exactly equal to k
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k))
                count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
