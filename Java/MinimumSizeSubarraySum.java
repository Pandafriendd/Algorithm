/*
Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. 
If there isn't one, return 0 instead.

Example: 

Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.
Follow up:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n). 
*/

class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums.length == 0 || nums == null) {
            return 0;
        }
        
        int minLen = Integer.MAX_VALUE;
        for (int start = 0; start < nums.length; start++) {
            int cur = 0;
            for (int end = start; end < nums.length; end++) {
                cur += nums[end];
                if (cur >= s) {
                    minLen = Math.min(minLen, end - start + 1);
                    break;
                }
            }
        }
        
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
