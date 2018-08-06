/*
 * cannot rob two adjacent houses

 Example 2:

Input: [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
             Total amount you can rob = 2 + 9 + 1 = 12.

 */
public class HouseRobber {
	
	/*
	 a natural way to approach this problem is to work on the simplest case first:
	 
	 f(k) = largest amount that you can rob from the first k houses
	 Ai = amount of money at the ith house
	 
	 case n = 1: f(1) = A1
	 case n = 2: f(2) = max(A1, A2)
	 case n = 3: 1. rob third house, add A3 to A1
	 			 2. don't rob third, and stick with f(2)
	 we want to choose the larger of two options at each step
	 
	 Therefore we have:
	 f(k) = max(f(k - 2) + Ak, f(k - 1))
	 
	 We choose the base case as f(¨C1) = f(0) = 0, which will greatly simplify our code!!!
	 
	 The answer will be calculated as f(n). We could use an array to store and calculate the result, 
	 but since at each step you only need the previous two maximum values, two variables are suffice.
	 
	 time: O(n)   space: O(1)
	 */
	public int rob(int[] nums) {
		if(nums == null || nums.length == 0)
			return 0;
		// if nums is all positive
		int preMax = 0;
		int curMax = 0;
		
		// if nums has negative num
		// int preMax = curMax = Integer.MIN_VALUE
		
		for(int x : nums) {
			int temp = curMax;
			curMax = Math.max(curMax, preMax + x);
			preMax = temp;
		}
		
		return curMax;
	}
}
