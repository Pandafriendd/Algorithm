/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Example 1:
Input: [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

Example 2:
Input: [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum
             jump length is 0, which makes it impossible to reach the last index.
 */


public class JumpGame {
	
	
	// Greedy O(n)
	public boolean canJump(int[] nums) {
        if (nums.length == 0) {
            return true;
        }
        
        int furthest = 0;  // the maximum index can reach from previous jump (not including current nums[i])
        for (int i = 0; i < nums.length; i++) {
            if (furthest >= i) {  // if be able to reach i
            	furthest = Math.max(i + nums[i], furthest);
                if (furthest >=  nums.length - 1) 
                    return true;
            }
        }
        
        return false;
    }
	
	// also greedy
	public boolean canJump66(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }
	
	
	/*
Approach 1: Backtracking
This is the inefficient solution where we try every single jump pattern that takes us from the first position to the last. 
We start from the first position and jump to every index that is reachable. We repeat the process until last index is reached. When stuck, backtrack.
Time: O(2^n). There are 2^n​ (upper bound) ways of jumping from the first position to the last, where n is the length of array nums
Space complexity: O(n). Recursion requires additional memory for the stack frames.
	 */
	public boolean canJumpFromPosition(int position, int[] nums) {
		if(position == nums.length - 1) {
			return true;
		}
		
		int furthest = Math.min(position + nums[position], nums.length - 1);
		for(int nextp = position + 1; nextp <= furthest; nextp++) {
			if(canJumpFromPosition(nextp, nums)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean canJump2(int[] nums) {
		return canJumpFromPosition(0, nums);
	}
	
	/*
Approach 2: Dynamic Programming Top-down
	 */
	enum Index {
	    GOOD, BAD, UNKNOWN
	}
	
	Index[] memo;

    public boolean canJumpFromPosition3(int position, int[] nums) {
        if (memo[position] != Index.UNKNOWN) {
            return memo[position] == Index.GOOD ? true : false;
        }

        int furthestJump = Math.min(position + nums[position], nums.length - 1);
        for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++) {
            if (canJumpFromPosition(nextPosition, nums)) {
                memo[position] = Index.GOOD;
                return true;
            }
        }

        memo[position] = Index.BAD;
        return false;
    }

    public boolean canJump3(int[] nums) {
        memo = new Index[nums.length];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = Index.UNKNOWN;
        }
        memo[memo.length - 1] = Index.GOOD;
        return canJumpFromPosition3(0, nums);
    }
    
    /*
Dynamic Programming Bottom-up
     */
    public boolean canJump4(int[] nums) {
        Index[] memo = new Index[nums.length];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = Index.UNKNOWN;
        }
        memo[memo.length - 1] = Index.GOOD;

        for (int i = nums.length - 2; i >= 0; i--) {
            int furthestJump = Math.min(i + nums[i], nums.length - 1);
            for (int j = i + 1; j <= furthestJump; j++) {
                if (memo[j] == Index.GOOD) {
                    memo[i] = Index.GOOD;
                    break;
                }
            }
        }

        return memo[0] == Index.GOOD;
    }
}
