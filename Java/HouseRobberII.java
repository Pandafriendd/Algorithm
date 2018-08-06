

/*
 All houses at this place are arranged in a circle.

Input: [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
 */

public class HouseRobberII {
	// to rob a given range of houses
	public int robHelper(int[] nums, int low, int high) {
		int preMax = 0;
		int curMax = 0;
		
		for(int i = low; i <= high; i++) {
			int temp = curMax;
			curMax = Math.max(curMax, preMax + nums[i]);
			preMax = temp;
		}
		
		return curMax;
	}
	
	/*
	 Now the question is how to rob a circular row of houses. 
	 It is a bit complicated to solve like the simpler question. 
	 It is because in the simpler question whether to rob nums[low] is entirely our choice. 
	 But, it is now constrained by whether nums[high] is robbed.
	 Actually, extending from the logic that if house i is not robbed, 
	 then you are free to choose whether to rob house i + 1, you can break the circle by assuming a house is not robbed
	 For example, 1 -> 2 -> 3 -> 1 becomes 2 -> 3 if 1 is not robbed
	 Since every house is either robbed or not robbed and at least half of the houses are not robbed, 
	 the solution is simply the larger of two cases with consecutive houses, 
	 i.e. house i not robbed, break the circle, solve it, or house i + 1 not robbed. 
	 Hence, the following solution, I chose i = n and i + 1 = 0 for simpler coding. But, you can choose whichever two consecutive ones.
	 
	 !!! the key observation is that if we rob the last house in the row, we shouldn't rob the first one. 
	 !!! Therefore to impose this constraint, we need to discuss whether to rob the first house or not, and thus we have two arrays
	 */
	public int rob(int[] nums) {
		if(nums.length == 1)
			return nums[0];
		
		return Math.max(robHelper(nums, 0, nums.length - 2), robHelper(nums, 1, nums.length - 1));
	}
}
