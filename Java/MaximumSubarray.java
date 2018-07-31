/*
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */


public class MaximumSubarray {
	/*
	 * 
Analysis of this problem:
Apparently, this is a optimization problem, which can be usually solved by DP. 
So when it comes to DP, the first thing for us to figure out is the format of the sub problem(or the state of each sub problem). 
The format of the sub problem can be helpful when we are trying to come up with the recursive relation.

At first, I think the sub problem should look like: 
maxSubArray(int A[], int i, int j), which means the maxSubArray for A[i: j]. 
In this way, our goal is to figure out what maxSubArray(A, 0, A.length - 1) is. 
However, if we define the format of the sub problem in this way, it's hard to find the connection from the sub problem to the original problem(at least for me). 
In other words, I can't find a way to divided the original problem into the sub problems and use the solutions of the sub problems to somehow create the solution of the original one.

So I change the format of the sub problem into something like: 
maxSubArray(int A[], int i), which means the maxSubArray for A[0:i ] which must has A[i] as the end element. 
Note that now the sub problem's format is less flexible and less powerful than the previous one, 
because there's a limitation that A[i] should be contained in that sequence and we have to keep track of each solution of the sub problem to update the global optimal value. 
However, now the connect between the sub problem & the original one becomes clearer:

maxSubArray(A, i) = [ maxSubArray(A, i - 1) > 0 ? maxSubArray(A, i - 1) : 0 ] + A[i]; 
	 */
	
	
	/**
	    * DP, O(n) Time, O(n) Space
	    */
	   int solution2(int[] nums) {
	       if (nums == null || nums.length == 0) return 0;
	       int[] dp = new int[nums.length]; // save max sum so far in an array
	      dp[0] = nums[0];
	       int max = nums[0]; 
	       for (int i = 1; i < nums.length; i++) {
	          dp[i] = dp[i - 1] > 0 ? (nums[i] + dp[i - 1]) : nums[i];
	          max = Math.max(max, dp[i]); 
	       }
	       return max;
	   }
	
	
	/**
	 * beats 100% !!!!
	 * O(n) time, O(1) space !!! constant space!!
	 * if nums[i] < 0 && curMax + nums[i] < 0, should recalculate the max, exclude nums[i] and keep finding
	 * if nums[i] < 0 && curMax + nums[i] > 0, should continue, include nums[i] and keep finding
	 */
	public int solution(int[] nums) {
		int res = 0;
		if(nums == null || nums.length == 0)
			return res;
		
		res = nums[0];
		int curMax = nums[0]; // curMax is current iterative node's max value, consider it as an intend of increase, if it > 0, it can keep in the searching
		for(int i = 1; i < nums.length; i++) { //!!! i starts from 1
			curMax = Math.max(curMax + nums[i], nums[i]); //if curMax < 0, recalculate, curMax should be changed: curMax = nums[i]
			res = Math.max(curMax, res); // keep recording max for each round
			System.out.println("curMax=" + curMax + "Max=" + res);
		}
		
		return res;
	}
	
	
   
	
	/*
	 * Divide and Conquer
	 
The naive method is to run two loops. 
The outer loop picks the beginning element, 
the inner loop finds the maximum possible sum with first element picked by outer loop and compares this maximum with the overall maximum. 
Finally return the overall maximum. The time complexity of the Naive method is O(n^2).

Using Divide and Conquer approach, we can find the maximum subarray sum in O(nLogn) time. Following is the Divide and Conquer algorithm.

1) Divide the given array in two halves
2) Return the maximum of following three
бн.a) Maximum subarray sum in left half (Make a recursive call)
бн.b) Maximum subarray sum in right half (Make a recursive call)
бн.c) Maximum subarray sum such that the subarray crosses the midpoint

The lines 2.a and 2.b are simple recursive calls. 
How to find maximum subarray sum such that the subarray crosses the midpoint? 
We can easily find the crossing sum in linear time. 
The idea is simple, find the maximum sum starting from mid point and ending at some point on left of mid, 
then find the maximum sum starting from mid + 1 and ending with sum point on right of mid + 1. Finally, combine the two and return.
	 
	 */
	// Find the maximum possible sum in arr[] 
    // such that arr[m] is part of it
    static int maxCrossingSum(int arr[], int l, int m, int h)
    {
        // Include elements on left of mid.
        int sum = 0;
        int left_sum = Integer.MIN_VALUE;
        for (int i = m; i >= l; i--)
        {
            sum = sum + arr[i];
            if (sum > left_sum)
            left_sum = sum;
        }
 
        // Include elements on right of mid
        sum = 0;
        int right_sum = Integer.MIN_VALUE;
        for (int i = m + 1; i <= h; i++)
        {
            sum = sum + arr[i];
            if (sum > right_sum)
            right_sum = sum;
        }
 
        // Return sum of elements on left
        // and right of mid
        return left_sum + right_sum;
    }
 
    // Returns sum of maxium sum subarray 
    // in aa[l..h]
    static int maxSubArraySum(int arr[], int l, int h)
    {
    // Base Case: Only one element
    if (l == h)
        return arr[l];
 
    // Find middle point
    int m = (l + h)/2;
 
    /* Return maximum of following three 
    possible cases:
    a) Maximum subarray sum in left half
    b) Maximum subarray sum in right half
    c) Maximum subarray sum such that the 
    subarray crosses the midpoint */
    return Math.max(Math.max(maxSubArraySum(arr, l, m),
                    maxSubArraySum(arr, m+1, h)),
                    maxCrossingSum(arr, l, m, h));
    }
   
   /**
    * Not asking sum, but the range
    * If nums[i] < 0, current sum + nums[i] >= 0, we can continue addition because 
    * the positive sum would still contribute to positiveness of the subarray. 
    * If nums[i] < 0, current sum + nums[i] < 0, the current subarray has to end.
    */
	public int[] solution3(int[] nums) {   // !!! return val is a array!!
		
		int beginTemp = 0; // save the temporary begining index
        int begin = 0; // begining index
        int end = 0; // ending index
        int maxSoFar = nums[0]; // max sum of previous sequence
        int maxEndingHere = nums[0]; // max sum of this group
        
        for (int i = 1; i < nums.length; i++) {
            if (maxEndingHere < 0) {  // last A[i] is too small
                maxEndingHere = nums[i];
                beginTemp = i; // update begin temp
            } else {
                maxEndingHere += nums[i];
            }
            
            if (maxEndingHere >= maxSoFar) { // update max so far
                maxSoFar = maxEndingHere;
                begin = beginTemp; // save index range
                end = i;
            }
        }
        System.out.println("begin=" + begin + "end=" + end + "maxsofar=" + maxSoFar);
        return new int[] {begin, end, maxSoFar};
	}
	
	public static void main(String[] args) {
		int[] nums = { -2, 1, -3, -3, 4, -1, 2, 1, -5, 4 };
		MaximumSubarray m = new MaximumSubarray();
//		int[] a = m.solution3(nums);
//		for(int i = 0; i < a.length; i++)
//			System.out.println(a[i]);
		System.out.print(m.solution(nums));
	}
}
