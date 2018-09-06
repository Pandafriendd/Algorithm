/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

Example:
Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
 */
import java.util.*;
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
In brute force, we iterate over the left and right parts again and again just to find the highest bar size upto that index. 
But, this could be stored. Voila, dynamic programming.
Algorithm 
Find maximum height of bar from the left end upto an index i in the array left_max. 
Find maximum height of bar from the right end upto an index i in the array right_max. 
Iterate over the height array and update ans: Add min(max_left[i],max_right[i])−height[i] to ans

time: O(n)  space: O(n)
	 */
	public int trap2(int[] height) {
		if(height == null || height.length == 0)
			return 0;
		
		int ans = 0;
		int len = height.length;
		int[] maxLeft = new int[len];
		int[] maxRight = new int[len];
		
		maxLeft[0] = height[0];
		for(int i = 1; i < len; i++) {
			maxLeft[i] = Math.max(maxLeft[i - 1], height[i]);
		}
		
		maxRight[len - 1] = height[len - 1];
		for(int i = len - 2; i >= 0; i--) {
			maxRight[i] = Math.max(maxRight[i + 1], height[i]);
		}
		
		for(int i = 1; i <= len - 2; i++) {
			ans += Math.min(maxLeft[i], maxRight[i]) - height[i];
		}
		
		return ans;
	}
	
	
	/*
using stack:
Use a stack to store the indices of the bars.
We add the index of the bar to the stack if bar is smaller than or equal to the bar at top of stack, 
which means that the current bar is bounded by the previous bar in the stack. 
If we found a bar longer than that at the top, we are sure that the bar at the top of the stack is bounded by the current bar and a previous bar in the stack, 
hence, we can pop it and add resulting trapped water to ans.
	 */
	public int trap3(int[] height) {
		int ans = 0;
		int cur = 0;
		
		Stack<Integer> stack = new Stack<>();
		while(cur < height.length) {
			while(!stack.isEmpty() && height[cur] > height[stack.peek()]) {
				int top = stack.pop();
				if(stack.isEmpty())
					break;
				int distance = cur - stack.peek() - 1;
				int bounded_height = Math.min(height[cur], height[stack.peek()]) - height[top];
				ans += distance * bounded_height;
			}
			
			stack.push(cur++);
		}
		return ans;
	}
}
