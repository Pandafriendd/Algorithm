/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

You may assume no duplicate exists in the array.

Example 1:

Input: [3,4,5,1,2] 
Output: 1
Example 2:

Input: [4,5,6,7,0,1,2]
Output: 0
 */
public class FindMinimuminRotatedSortedArray {
	
	/*
	 * 
	 * eg: 3 4 5 1 2 and 5 6 0 1 2 3 4
	 * 
*/
	public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        if(nums.length == 1 || nums[left] < nums[right])
            return nums[left];
        
        while(left < right){  // !!!
            int mid = left + (right - left) / 2;
            if(nums[mid] > nums[left])
                left = mid; // the mininum is in the left part
            else
                right = mid; // the mininum is in the left part
        }
        return nums[left + 1];  // !!!
    }
}
