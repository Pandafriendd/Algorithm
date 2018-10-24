/*
Given an integer array, your task is to find all the different possible increasing subsequences of the given array, 
and the length of an increasing subsequence should be at least 2 .

Example:
Input: [4, 6, 7, 7]
Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
Note:
The length of the given array will not exceed 15.
The range of integer in the given array is [-100,100].
The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.
 */

import java.util.*;

public class IncreasingSubsequences {
	
	public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res =new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        
        helper(nums, 0, new ArrayList<>(), res);
        return res;
    }
    
    private void helper(int[] nums, int index, List<Integer> tempList, List<List<Integer>> res) {
        // base case
        if (tempList.size() >= 2) {
            res.add(new ArrayList<>(tempList));
        } 
        
        // recurisve rule
        Set<Integer> used = new HashSet<>();
        for (int i = index; i < nums.length; i++) {       
            if ((tempList.size() == 0 || nums[i] >= tempList.get(tempList.size() - 1)) && used.add(nums[i])) {
                tempList.add(nums[i]);
                helper(nums, i + 1, tempList, res);  // !!!!!!!!!!!!!! i + 1, not index + 1!!!!!!!!!!!!
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}
