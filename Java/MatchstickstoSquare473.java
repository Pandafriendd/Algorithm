
/*
 * the partition problem (or number partitioning):
 *  is the task of deciding whether a given multiset S of positive integers can be partitioned into two subsets S1 and S2 such that the sum of the numbers in S1 equals the sum of the numbers in S2. 
 *  The partition problem is NP-complete.
 *  
 *  Input: [1,1,2,2,2] 
 *  Output: true 
 *  Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
 *  
 *  This problem can be thinking to divid S into 4 subset.
 *  since The length sum of the given matchsticks is in the range of 0 to 10^9. The length of the given matchstick array will not exceed 15.
 *   the input will not be very large... we can do DFS !!
 */

public class MatchstickstoSquare473 {
	public boolean makesquare(int[] nums) {
        if(nums == null || nums.length < 4)
        	return false;
        int sum = 0;
        for(int num : nums)
        	sum += num;
        if(sum % 4 != 0)
        	return false;
        
        return dfs(nums, new int[4], 0, sum/4);
    }
	
	private boolean dfs(int[] nums, int[] sums, int index, int target) {
		if(index == nums.length) {
			if(sums[0] == target && sums[1] == target && sums[2] == target)
				return true;
			else
				return false;
		}
		
		
		for(int i = 0; i < 4; i++) {
			if(sums[i] + nums[index] > target)
				continue;
			sums[i] += nums[index];
			if(dfs(nums, sums, index + 1, target))
				return true;
			sums[i] -= nums[index];
		}
		
		return false;
	}
}
