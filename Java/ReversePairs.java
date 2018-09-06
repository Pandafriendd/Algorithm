/*
Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].
You need to return the number of important reverse pairs in the given array.

Example1:
Input: [1,3,2,3,1]
Output: 2

Example2:
Input: [2,4,3,5,1]
Output: 3

Note:
The length of the given array will not exceed 50,000.
All the numbers in the input array are in the range of 32-bit integer.
 */

import java.util.*;
public class ReversePairs {
	
	/*
MergeSort

Explanation: In each round, we divide our array into two parts and sort them. 
So after "int cnt = mergeSort(nums, s, mid) + mergeSort(nums, mid+1, e); ", 
the left part and the right part are sorted and now our only job is to count how many pairs of number (leftPart[i], rightPart[j]) satisfies leftPart[i] <= 2*rightPart[j].
For example,
left: 4 6 8 right: 1 2 3
so we use two pointers to travel left and right parts. 
For each leftPart[i], if j<=e && nums[i]/2.0 > nums[j], we just continue to move j to the end, to increase rightPart[j], until it is valid. 
Like in our example, left's 4 can match 1 and 2; left's 6 can match 1, 2, 3, and left's 8 can match 1, 2, 3. 
So in this particular round, there are 8 pairs found, so we increases our total by 8.
	 */
	public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length-1);
    }
	
    private int mergeSort(int[] nums, int s, int e) {
        if(s>=e) 
        	return 0; 
        int mid = s + (e-s)/2; 
        int cnt = mergeSort(nums, s, mid) + mergeSort(nums, mid+1, e); 
        
        for(int i = s, j = mid + 1; i <= mid; i++) { // i traverse left part, j traverse right part
            while(j <= e && nums[i] / 2.0 > nums[j]) 
            	j++; 
            cnt += j - (mid + 1); 
        }
        
        Arrays.sort(nums, s, e+1); 
        return cnt; 
    }
}
