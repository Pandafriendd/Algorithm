/*
In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.
Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.
Return the result as a list of indices representing the starting position of each interval (0-indexed). 
If there are multiple answers, return the lexicographically smallest one.

Example:
Input: [1,2,1,2,6,7,5,1], 2
Output: [0, 3, 5]
Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.

Note:
nums.length will be between 1 and 20000.
nums[i] will be between 1 and 65535.
k will be between 1 and floor(nums.length / 3).
 */



public class MaximumSumof3NonOverlappingSubarrays {
	
	
	/*
DP
we approach the reduced problem: 
Given some array W and an integer K, what is the lexicographically smallest tuple of indices (i, j, k) with i + K <= j and j + K <= z that maximizes W[i] + W[j] + W[k]?

Time: O(n)
Space: O(n), W, left, right all take O(n) memory
	 */
	public int[] maxSumOfThreeSubarrays(int[] nums, int K) {
		int[] W = new int[nums.length - K + 1]; // W is an array of sums of windows
		int sum = 0;
		for(int i = 0; i < nums.length; i++) {
			sum += nums[i];
			if(i >= K) 
				sum -= nums[i - K];			
			if( i >= K - 1) 
				W[i - K + 1] = sum;			
		}
		
		int[] left = new int[W.length];
		int best = 0;
		for(int i = 0; i < W.length; i++) {
			if(W[i] > W[best])
				best = i;
			left[i] = best;
		}
		
		int[] right = new int[W.length];
		best = W.length - 1;
		for(int i = W.length - 1; i >= 0; i--) {
			if(W[i] >= W[best]) 
				best = i;			
			right[i] = best;
		}
		
		int[] res = new int[] {-1, -1, -1};
		for(int j = K; j < W.length - K; j++) {
			int i = left[j - K];
			int k = right[j + K];
			if(res[0] == -1 ||
					W[i] + W[j] + W[k] > W[res[0]] + W[res[1]] + W[res[2]]) {
				res[0] = i;
				res[1] = j;
				res[2] = k;
			}
		}
		
		return res;
	}
	
	/*
The question asks for three non-overlapping intervals with maximum sum of all 3 intervals. 
If the middle interval is [i, i+k-1], where k <= i <= n-2k, the left interval has to be in subrange [0, i-1], and the right interval is from subrange [i+k, n-1].

So the following solution is based on DP.
posLeft[i] is the starting index for the left interval in range [0, i];
posRight[i] is the starting index for the right interval in range [i, n-1]; 

Then we test every possible starting index of middle interval, i.e. k <= i <= n-2k, and we can get the corresponding left and right max sum intervals easily from DP. 
And run time is O(n).
Caution. In order to get lexicographical smallest order, when there are two intervals with equal max sum, always select the left most one. 
So in the code, the if condition is ">= tot" for right interval due to backward searching, and "> tot" for left interval. 
	 */
	public int[] maxSumOfThreeSubarrays2(int[] nums, int k) {
        int n = nums.length, maxsum = 0;
        
        /*
         * sum[i] means nums[0] + nums[1] + ... + nums[i-1]
         * posLeft[i] means if the middle interval start index is i, what's the index for left interval such that left interval has the maximum sum. 
         * initial value is 0 because if middle interval start index is k, then left interval start index is 0.
         * posRight[i] means if the middle interval start index is i, what's the index for right interval such that right interval has the maximum sum. 
         * initial value is n-k because if middle interval start index is n - 2*k, then right interval start index is n-k.
         */
        int[] sum = new int[n+1];
        int[] posLeft = new int[n], posRight = new int[n],ans = new int[3];
        for (int i = 0; i < n; i++) 
        	sum[i+1] = sum[i]+nums[i];
        
        // DP for starting index of the left max sum interval
        for (int i = k, tot = sum[k]-sum[0]; i < n; i++) {
            if (sum[i+1]-sum[i+1-k] > tot) {
                posLeft[i] = i+1-k;
                tot = sum[i+1]-sum[i+1-k];
            }
            else
                posLeft[i] = posLeft[i-1];
        }
        
        // DP for starting index of the right max sum interval
       // caution: the condition is ">= tot" for right interval, and "> tot" for left interval
        posRight[n-k] = n-k;
        for (int i = n-k-1, tot = sum[n]-sum[n-k]; i >= 0; i--) {
            if (sum[i+k]-sum[i] >= tot) {
                posRight[i] = i;
                tot = sum[i+k]-sum[i];
            }
            else
                posRight[i] = posRight[i+1];
        }
        
        // test all possible middle interval
        for (int i = k; i <= n-2*k; i++) {
            int l = posLeft[i-1], r = posRight[i+k];
            int tot = (sum[i+k]-sum[i]) + (sum[l+k]-sum[l]) + (sum[r+k]-sum[r]);
            if (tot > maxsum) {
                maxsum = tot;
                ans[0] = l; ans[1] = i; ans[2] = r;
            }
        }
        return ans;
    }
	
}
