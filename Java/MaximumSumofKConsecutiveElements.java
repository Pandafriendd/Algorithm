/*
Given an array of integers of size ¡®n¡¯. Our aim is to calculate the maximum sum of ¡®k¡¯ consecutive elements in the array
 */

public class MaximumSumofKConsecutiveElements {
	
	// brute force. time: O(k*n)
	static int maxSum(int arr[], int n, int k)
    {
        // Initialize result
        int max_sum = Integer.MIN_VALUE ;
     
        // Consider all blocks starting with i.
        for (int i = 0; i <= n - k; i++)
        {
            int current_sum = 0;
            for (int j = 0; j < k; j++)
                current_sum = current_sum + arr[i + j];
     
            // Update result if required.
            max_sum = Math.max(current_sum , max_sum );
        }
     
        return max_sum;
    }
	
	// Window Sliding Technique O(n)
    static int maxSum2(int arr[], int n, int k)
    {
        // k must be greater
        if (n < k)
        {
        System.out.println("Invalid");
        return -1;
        }
     
        // Compute sum of first window of size k
        int max_sum = 0;
        for (int i = 0; i < k; i++)
        	max_sum += arr[i];
     
        // Compute sums of remaining windows by removing first element of previous window and adding last element of current window.
        int window_sum = max_sum;
        for (int i = k; i < n; i++)
        {
        	window_sum += arr[i] - arr[i - k];
        	max_sum = Math.max(max_sum, window_sum);
        }
     
        return max_sum;
    }
}
