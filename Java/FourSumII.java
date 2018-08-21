/*
Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.

To make problem a bit easier, all A, B, C, D have same length of N where 0 ¡Ü N ¡Ü 500. All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.

Example:

Input:
A = [ 1, 2]
B = [-2,-1]
C = [-1, 2]
D = [ 0, 2]

Output:
2

Explanation:
The two tuples are:
1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 */

import java.util.*;
public class FourSumII {
	
	/*
	 Take the arrays A and B, and compute all the possible sums of two elements. 
	 Put the sum in the Hash map, and increase the hash map value if more than 1 pair sums to the same value.

	Compute all the possible sums of the arrays C and D. 
	If the hash map contains the opposite value of the current sum, increase the count of four elements sum to 0 by the counter in the map.
	
	time: O(n^2) space; O(n^2) in worse case
	 */
	public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
		int res = 0;
		if(A == null || A.length == 0)
			return res;
		
		HashMap<Integer, Integer> map = new HashMap<>();   // key is sum, value is count
		for(int i = 0; i < A.length; i++) {
			for(int j = 0; j < B.length; j++) {
				int sum = A[i] + B[j];
				if(map.containsKey(sum))
					map.put(sum, map.get(sum) + 1);
				else
					map.put(sum, 1);
			}
		}
		for(int i = 0; i < C.length; i++) {
			for(int j = 0; j < D.length; j++) {
				int sum = -(C[i] + D[j]);
				if(map.containsKey(sum))
					res += map.get(sum);
			}
		}
		
		return res;
	}
}
