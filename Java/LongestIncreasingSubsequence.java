import java.util.Arrays;

/*
 * Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:

Input: [10,9,2,5,3,7,101,18]
Output: 4 
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. 
Note:

There may be more than one LIS combination, it is only necessary for you to return the length.
Your algorithm should run in O(n2) complexity.
Follow up: Could you improve it to O(n log n) time complexity?
 */

public class LongestIncreasingSubsequence {
	
	/*
Approach #1 Brute Force [Time Limit Exceeded]
Algorithm

The simplest approach is to try to find all increasing subsequences and then returning the maximum length of longest increasing subsequence. 
In order to do this, we make use of a recursive function lenOfLIS 
which returns the length of the LIS possible from the current element(corresponding to curpos) onwards(including the current element). 
Inside each function call, we consider two cases:

1.The current element is larger than the previous element included in the LIS. 
In this case, we can include the current element in the LIS. 
Thus, we find out the length of the LIS obtained by including it. 
Further, we also find out the length of LIS possible by not including the current element in the LIS. 
The value returned by the current function call is, thus, the maximum out of the two lengths.

2.The current element is smaller than the previous element included in the LIS. 
In this case, we can't include the current element in the LIS. 
Thus, we find out only the length of the LIS possible by not including the current element in the LIS, which is returned by the current function call.

Complexity Analysis

Time complexity : O(2^n). Size of recursion tree will be 2^n
​Space complexity : O(n^2). memo array of size n * n is used.

	 */
	
	public int lengthOfLIS1(int[] nums) {
        return lenOfLIS(nums, Integer.MIN_VALUE, 0);
    }
	
	public int lenOfLIS(int[] nums, int prev, int curpos) {
		if(curpos == nums.length)
			return 0;
		int taken = 0;
		
		if(nums[curpos] > prev) {
			taken = 1 + lenOfLIS(nums, nums[curpos], curpos + 1);
		}
		int nottaken =  lenOfLIS(nums, prev, curpos + 1);
		
		return Math.max(taken, nottaken);
	}
	
	
	
	/*
Approach #2 Recursion with memorization [Memory Limit Exceeded]
Algorithm

In the previous approach, many recursive calls had to made again and again with the same parameters. 
This redundancy can be eliminated by storing the results obtained for a particular call in a 2-d memorization array memo.
 
memo[i][j] represents the length of the LIS possible using nums[i] as the previous element considered to be included/not included in the LIS, 
with nums[j] as the current element considered to be included/not included in the LIS. Here, nums represents the given array.

Time: O(n^2) Size of recursion tree can go up to n^2
Space: O(n^2) memo array n*n is used
	 */
	
	public int lengthOfLIS2(int[] nums) {
		int memo[][] = new int[nums.length + 1][nums.length];  // ????
		
		for(int[] i : memo) {
			Arrays.fill(i, -1);
		}
		
		return lenOfLIS2(nums, -1, 0, memo);  // !! previndex begins from -1
	}
	
	public int lenOfLIS2(int[] nums, int previndex, int curpos, int[][] memo) {
		if(curpos == nums.length)
			return 0;
		
		if(memo[previndex + 1][curpos] >= 0) {  // if memo[previn+1][cp] already has element
			return memo[previndex + 1][curpos];
		}
		
		int taken = 0;
		if(previndex < 0 || nums[curpos] > nums[previndex]) {  // ?? previndex < 0
			taken = 1 + lenOfLIS2(nums, curpos, curpos + 1, memo);
		}
		
		int nottaken = lenOfLIS2(nums, previndex, curpos + 1, memo);
		
		memo[previndex + 1][curpos] = Math.max(taken, nottaken);
		return memo[previndex + 1][curpos];
	}
	
	//moved memo outside instead of as part of stack
	//'Memory Limit Exceeded' issue is gone.
    int[][] mm = null;  //!!
    
    public int lengthOfLIS555(int[] nums) {
        mm = new int[nums.length + 1][nums.length];
        for (int[] l : mm) {
            Arrays.fill(l, -1);
        }
        return lengthofLIS(nums, -1, 0);
    }
    
    public int lengthofLIS(int[] nums, int previndex, int curpos) {
        if (curpos == nums.length) {
            return 0;
        }
        if (mm[previndex + 1][curpos] >= 0) {
            return mm[previndex + 1][curpos];
        }
        int taken = 0;
        if (previndex < 0 || nums[curpos] > nums[previndex]) {
            taken = 1 + lengthofLIS(nums, curpos, curpos + 1);
        }

        int nottaken = lengthofLIS(nums, previndex, curpos + 1);
        mm[previndex + 1][curpos] = Math.max(taken, nottaken);
        return mm[previndex + 1][curpos];
    }
	
	
	
	/*
Approach #3 Dynamic Programming [Accepted]
Algorithm

This method relies on the fact that the longest increasing subsequence possible upto the ith index in a given array 
is independent of the elements coming later on in the array. Thus, if we know the length of the LIS upto ith index, 
we can figure out the length of the LIS possible by including the ith element based on the elements with indices j such that 0≤j≤(i+1).

We make use of a dp array to store the required data. 
dp[i] represents the longest subsequence ending exactly at i
For example, if the inputs nums is [4, 10, 4], the solution builds the following dp: [1, 2, 1] 
In order to find out dp[i], we need to try to append the current element(nums[i]nums[i]) in every possible increasing subsequences upto the (i−1)th index(including the (i−1)th index), 
such that the new sequence formed by adding the current element is also an increasing subsequence. 
Thus, we can easily determine dp[i] using:

dp[i]=max(dp[j])+1,∀0≤j<i
At the end, the maximum out of all the dp[i]'s to determine the final result.
LIS ​length ​​ =max(dp[i]),∀0≤i<n

Time: O(n^2). Two loop of n are there
Space O(n)  dp array of size n is used
	 */
	
	public int lengthOfLIS3(int[] nums) {
		if(nums == null || nums.length == 0)
			return 0;
		
		int[] dp = new int[nums.length];
		dp[0] = 1;
		int maxans = 1;
		
		for(int i = 0; i < dp.length; i++) {
			int maxval = 0;
			for(int j = 0; j < i; j++) {
				if(nums[i] > nums[j]) {
					maxval = Math.max(maxval, dp[j]);
				}
			}
			dp[i] = maxval + 1;
			maxans = Math.max(maxans, dp[i]);
		}
		
		return maxans;
	}
	
	
	/*
Approach #4 Dynamic Programming with Binary Search

In this approach, we scan the array from left to right. 
We also make use of a dp array initialized with all 0's. 
This dp array is meant to store the increasing subsequence formed by including the currently encountered element. 
While traversing the nums array, we keep on filling the dp array with the elements encountered so far. 
For the element corresponding to the j ​th ​​ index (nums[j]), 
we determine its correct position in the dp array(say i ​th ​​ index) by making use of Binary Search
(which can be used since the dp array is storing increasing subsequence) and also insert it at the correct position. 
An important point to be noted is that for Binary Search, 
we consider only that portion of the dp array in which we have made the updates by inserting some elements at their correct positions(which remains always sorted). 
Thus, only the elements upto the i ​th ​​ index in the dp array can determine the position of the current element in it. 
Since, the element enters its correct position(i) in an ascending order in the dp array, the subsequence formed so far in it is surely an increasing subsequence. 
Whenever this position index ii becomes equal to the length of the LIS formed so far(len), 
it means, we need to update the len as len=len+1. 

Note: dp array does not result in longest increasing subsequence, but length of dp array will give you length of LIS.

Consider the example:

input: [0, 8, 4, 12, 2]

dp: [0]

dp: [0, 8]

dp: [0, 4]

dp: [0, 4, 12]

dp: [0 , 2, 12] which is not the longest increasing subsequence, but length of dp array results in length of Longest Increasing Subsequence.

so it's basically incrementally building the longest incrementing subsequence array.
	 
time: O(nlogn) Binary search takes logn time and it is called n times
space: O(n) dp array of size n is used


	 *
	 */
	
	public int lengthOfLIS4(int[] nums) {
		int[] dp = new int[nums.length];
		int len = 0;
		
		for(int num : nums) {
			int i = Arrays.binarySearch(dp, 0, len, num);
			if(i < 0) {
				i = -(i + 1);
			}
			dp[i] = num;
			if(i == len) {
				len++;
			}
		}
		
		return len;
	}
	
	
}
