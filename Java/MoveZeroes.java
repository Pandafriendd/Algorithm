/*
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
Note:

You must do this in-place without making a copy of the array.
Minimize the total number of operations.
 */

/*
The 2 requirements of the question are:
1. Move all the 0's to the end of array.
2. All the non-zero elements must retain their original order.
It's good to realize here that both the requirements are mutually exclusive, i.e., you can solve the individual sub-problems and then combine them for the final solution.
 */
import java.util.*;
public class MoveZeroes {
	
	// approach 1: make a copy of the nums to a list
	// this solution would be a good start in an interview
	// time: O(n) space: O(n)
	public void moveZeroes(int[] nums) {
        int n = nums.length;
        
        // count the zero
        int zerocount = 0;
        for(int i = 0; i < n; i++) {
        	if(nums[i] == 0)
        		zerocount++;
        }
        
        // make all the non-zero elements retain their original order
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < n; i++) {
        	if(nums[i] != 0)
        		res.add(nums[i]);
        }
        
        while(zerocount > 0) {
        	res.add(0);
        	zerocount--;
        }
        
        for(int i = 0; i < res.size(); i++) {
        	nums[i] = res.get(i);
        }
    }
	
	// some optimal solution:
	// two pointers, faster pointer traverses the array, slower moves only nums[i] != 0
	// time: O(n)  space: O(1)
	// not most optimal, for example, the array which has all (except last) leading zeroes: [0, 0, 0, ..., 0, 1], there are too many unnecessary writes
	public void moveZeros2(int[] nums) {
		int nextNonZeroAt = 0;
		
		// If the current element is not 0, then we need to place it at the slower index (nextNonZeroAt). 
		for(int i = 0; i < nums.length; i++) {
			if(nums[i] != 0)
				nums[nextNonZeroAt++] = nums[i];
		}
		
		// After we have finished processing new elements, all the non-zero elements are already at beginning of array. We just need to fill remaining array with 0's.
		for(int i = nextNonZeroAt; i < nums.length; i++) {
			nums[i] = 0;
		}
	}
	
	// most optimal
	public void moveZeros3(int[] nums) {
		for(int nextNonZeroAt = 0, i = 0; i < nums.length; i++) {
			if(nums[i] != 0) { // swap(nums[i], nums[nextNonZeroAt++])
				int temp = nums[nextNonZeroAt];
				nums[nextNonZeroAt] = nums[i];
				nums[i] = temp;
				nextNonZeroAt++;
			}
		}
	}
	
	
	
	
}
