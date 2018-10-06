/*
Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note: You are not suppose to use the library's sort function for this problem.

Example:

Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Follow up:

A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
Could you come up with a one-pass algorithm using only constant space?
 */


public class SortColors {
	public void sortColors(int[] nums) {
        if(nums == null || nums.length == 0) {
            return;
        }
        
        // (0, i) is 0; [i,j) is 1, [j, k] is unkown, (k, last] is 2  
        int i = 0;
        int j = 0;
        int k = nums.length - 1;
        
        while(j <= k) {
            if(nums[j] == 0) {
                swap(nums, i, j);
                i++;
                j++;
            }
            else if(nums[j] == 1) {  // !!!! else if
                j++;
            }
            else if(nums[j] == 2) {
                swap(nums, j, k);
                k--;
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}	
