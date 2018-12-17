/*
Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

Example 1:
Input: n = 12
Output: 3 
Explanation: 12 = 4 + 4 + 4.

Example 2:
Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.
 */

import java.util.*;
public class PerfectSquares {
	class Solution {
    /*
    DP:
    M[i] represents the least numsber of perfect square numbers which sum to i
    base case: M[0] = 0, M[1] = 1
    M[i] = min(M[i - j ^ 2] + 1), for all 1 <= j *j <= i
    
    time: O(n * n^1/2 = n^3/2) space: O(n)
    */
    public int numSquares(int n) {
        int[] M = new int[n + 2];
        // base case
        M[0] = 0;
        M[1] = 1;
        for (int i = 2; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                min = Math.min(min, M[i - j * j]);
            }
            M[i] = min + 1;
        }
        
        return M[n];
    }
}
	
	/*
DP Solution
dp[n] indicates that the perfect squares count of the given n, and we have:

dp[0] = 0 
dp[1] = dp[0]+1 = 1
dp[2] = dp[1]+1 = 2
dp[3] = dp[2]+1 = 3
dp[4] = Min{ dp[4-1*1]+1, dp[4-2*2]+1 } 
      = Min{ dp[3]+1, dp[0]+1 } 
      = 1				
dp[5] = Min{ dp[5-1*1]+1, dp[5-2*2]+1 } 
      = Min{ dp[4]+1, dp[1]+1 } 
      = 2
						.
						.
						.
dp[13] = Min{ dp[13-1*1]+1, dp[13-2*2]+1, dp[13-3*3]+1 } 
       = Min{ dp[12]+1, dp[9]+1, dp[4]+1 } 
       = 2
						.
						.
						.
dp[n] = Min{ dp[n - i*i] + 1 },  n - i*i >=0 && i >= 1
dp[n - i*i] + 1 means that we use up 1 square number i*i, and then go to find the left number of squares.

time: sqrt(1) + sqrt(2)+..+sqrt(n), integrate sqrt(n) ~> (n^3/2), so O(n^3/2)
space: O(n)
	 */
	public int numSquares(int n) {
		int[] dp = new int[n + 1];
		dp[0] = 0;
		for(int i = 1; i <= n; i++) {  // ! i starts from 1
			int min = Integer.MAX_VALUE;
			int j = 1;
			while(i - j*j >= 0) {
				min = Math.min(min, dp[i - j * j] + 1);
				j++;
			}
			dp[i] = min;
		}		
		return dp[n];
	}
	
	
	/*
Java BFS implementation: Start from node 0 in queue, and keep pushing in perfect square number + curr value, once we reach number n, we found the solution.
level (depth) 1: using 1 square number
level 2: using 2 square numbers,
level 3: using 3 square numbers
	 */
	public int numSquares2(int n) {
	    Queue<Integer> q = new LinkedList<>();
	    Set<Integer> visited = new HashSet<>();
	    q.offer(0);
	    visited.add(0);
	    int depth = 0;
	    while(!q.isEmpty()) {
	        int size = q.size();
	        depth++;
	        
	        while(size-- > 0) {
	            int u = q.poll();
	            for(int i = 1; i * i <= n; i++) {
	                int v = u + i * i;
	                if(v == n) {
	                	System.out.println("depth " + depth + " u " + u + " i " + i + " v " + v + " q " + q + " visited " + visited);
	                    return depth;
	                }
	                if(v > n) {	                	
	                    break;
	                }
	                if(!visited.contains(v)) {
	                    q.offer(v);
	                    visited.add(v);
	                    System.out.println("depth " + depth + " u " + u + " i " + i + " v " + v + " q " + q + " visited " + visited);
	                }
	            }
	        }
	    }
	    return depth;
	}
	
	public static void main(String[] args) {
		PerfectSquares p = new PerfectSquares();
		p.numSquares2(11);
	}
	
}
