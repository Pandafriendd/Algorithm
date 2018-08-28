/*
Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
 */

import java.util.*;

public class threeSum {
	
	/*
	two pointers
	sort given array first
	traverse the array with 1 pointer, and then use 2 more pointers from start (i + 1) and end (nums.len - 1) to find target
	 */
	 public List<List<Integer>> threeSum(int[] nums) {
	        List<List<Integer>> res = new ArrayList<>();
	        if(nums == null || nums.length == 0)
	                return res;
	        Arrays.sort(nums);
	        
	        for(int i = 0; i < nums.length - 2; i++){
	        	if(nums[i] > 0) break;  //stop at positive number
	            if (i - 1 >= 0 && nums[i] == nums[i - 1]) continue; // skip duplicates  // !!!!! i -1 >= 0 goes first!!!!!
	            
	            int low = i + 1, high = nums.length - 1;    
	            while(low < high){
	            	if(low - 1 > i && nums[low] == nums[low - 1]) { // skip duplicates
	            		low++;
	            		continue;
	            	}
	            	if(nums[i] + nums[low] > 0) break; // sum of first two already more than 0
	            		
	                int sum = nums[i] + nums[low] + nums[high];
	                if(sum == 0){
	                    res.add(Arrays.asList(nums[i], nums[low], nums[high]));
	                    low++;
	                    high--;
	                }
	                
	                else if(sum < 0)
	                    low++;
	                else  // sum > 0
	                    high--;     
	            }
	        }
	        
	        return res;
	    }
	
	public static void main(String[] args){
		
		int[] nums = {-1, 0, 1, 2, -1, -4};
		int a = 5/2;
		threeSum t = new threeSum();
		System.out.print(t.threeSum(nums));
		System.out.print(a);
	}

}
