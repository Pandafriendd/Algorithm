/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.
Each element in the array represents your maximum jump length at that position.
Your goal is to reach the last index in the minimum number of jumps.

Example:
Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.
    
Note:
You can assume that you can always reach the last index.
 */


public class JumpGameII {
	
	/*
BFS
change this problem to a BFS problem, where nodes in level i are all the nodes that can be reached in i-1th jump. for example. 2 3 1 1 4 , is
2||
3 1||
1 4 ||
clearly, the minimum jump of 4 is 2 since 4 is in level 3.

O(n)
	 */
	int jump(int[] nums) {
		if (nums.length <= 1) 
			return 0;
        int curMax = 0; // max index can reach in current level
        int level = 0, i = 0;
        while (i <= curMax) {  // traverse current level , and update the max reach of next level
            int furthest = curMax; // max index can reach in the next level
            
            for (; i <= curMax; i++) {
                furthest = Math.max(furthest, nums[i] + i);
                if (furthest >= nums.length - 1) 
                	return level + 1;
            }
            
            level++;
            curMax = furthest;
        }
        
        return -1; // if i > curMax, i can't move forward anymore (the last element in the array can't be reached)
	}
	
	/*
Greedy O(n)
Let's say the range of the current jump is [curBegin, curEnd], curFarthest is the farthest point that all points in [curBegin, curEnd] can reach. 
Once the current point reaches curEnd, then trigger another jump, and set the new curEnd with curFarthest, then keep the above steps
	 */
	public int jump2(int[] nums) {
		int jumpcount = 0, curEnd = 0, curFarthest = 0;
		for (int i = 0; i < nums.length - 1; i++) {
			curFarthest = Math.max(curFarthest, i + nums[i]);
			if (i == curEnd) {
				jumpcount++;
				curEnd = curFarthest;
			}
		}
		return jumpcount;
	}
}
