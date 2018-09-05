/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

Example:
Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
 */
public class TrappingRainWater {
	
	/*
	 * brute force
	 * find the maximum level of water it can trap after the rain, which is equal to the minimum of maximum height of bars on both the sides minus its own height.
	 * 
	 * Time complexity: O(n^2) For each element of array, we iterate the left and right parts. 
	 * Space complexity: O(1)
	 */
	public int trap(int[] height) {
		int res = 0;
		int len = height.length;
		for(int i = 1; i < len; i++) {
			int maxLeft = 0;
			int maxRight = 0;
			for(int j = i; j >= 0; j--) { //Search the left part for max bar size
				maxLeft = Math.max(maxLeft, height[j]);
			}
			for(int j = i; j < len; j++) { //Search the right part for max bar size
				maxRight = Math.max(maxRight, height[j]);
			}
			
			res += Math.min(maxLeft, maxRight) - height[i];
		}
		
		return res;
	}
	
	/*
DP:
Algorithm 
Find maximum height of bar from the left end upto an index i in the array left_max. 
Find maximum height of bar from the right end upto an index i in the array right_max. 
Iterate over the height array and update ans: Add min(max_left[i],max_right[i])−height[i] to ans
	 */
	
}
