/*
Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:
Input:  [1,2,3,4]
Output: [24,12,8,6]
Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? 
(The output array does not count as extra space for the purpose of space complexity analysis.)
 */
public class ProductofArrayExceptSelf {
	
	/*
O(n) time and O(n) space
The idea is to store product of numbers at the left side to A and product of numbers at right side to B.
i.e. left[i] = nums[0] * nums[1] ... * nums[i-1]; right[i] = nums[N] * nums[N-1] ... * nums[i+1];
As you can plainly see, Result[i] = left[i] * right[i]
	 */
	public int[] productExceptSelf(int[] nums) {
		int len = nums.length;
        int[] left = new int[len];  // a[i] = nums[0] * ... * nums[i - 1]
        int[] right = new int[len]; // b[i] = nums[len - 1] * ... * nums[i + 1]
        int[] res = new int[len];
        left[0] = 1;
        right[len - 1] = 1;
        
        for(int i = 1; i < len; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }
        
        for(int i = len - 1; i >= 1; i--) {
            right[i - 1] = right[i] * nums[i];
        }
        
        for(int i = 0; i < len; i++) {
            res[i] = left[i] * right[i];
        }
        
        return res;
	}
	
	
	/*
constant space
!!! amazing !!!
	 */
	public int[] productExceptSelf2(int[] nums) {
	    int len = nums.length;
	    int[] res = new int[len];
	    
	    res[0] = 1;
	    for (int i = 1; i < len; i++) {
	        res[i] = res[i - 1] * nums[i - 1];
	    }
	    
	    int right = 1;
	    for (int i = len - 1; i >= 0; i--) {
	        res[i] *= right;
	        right *= nums[i];
	    }
	    
	    return res;
	}
}
