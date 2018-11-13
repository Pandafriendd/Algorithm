/*
Given a rope with positive integer-length n, how to cut the rope into m integer-length parts with length p[0], p[1], ...,p[m-1], 
in order to get the maximal product of p[0]p[1] ... p[m-1]? m is determined by you and must be greater than 0 (at least one cut must be made). 
Return the max product you can have.

Assumptions

n >= 2
Examples

n = 12, the max product is 3 3 3 3 = 81(cut the rope into 4 pieces with length of each is 3).
 */


public class MaxProductOfCuttingRop {
	// method 1: dfs
	public int maxProduct(int length) {
	    // base case
	    if (length == 1) { 
	      return 1; // ??? == 1 or == 0 ???
	    }
	    
	    // recursive rule
	    int maxProduct = Integer.MIN_VALUE;
	    for (int i = 1; i <= length - 1; i++) {
	      maxProduct = Math.max(maxProduct, Math.max(i, maxProduct(i)) * (length - i));
	    }
	    
	    return maxProduct;
	}
	
	// method 2: DP
	// f[i] represents maximal product of cutting rop given the rop's length is i
	// f[i] = max(max(j, f[j]) * max(i - j, f[i - j])) where 1 <= j <= i - 1
	// O(n^2) time
	public int maxProduct2(int length) {
		int[] f = new int[length + 1];
		// base case
		f[1] = 1;
		// induction rules
		for (int i = 2; i <= length; i++) {
			int curMax = Integer.MIN_VALUE;
			for (int j = 1; j <= i - 1; j++) {  // can be improved to j <= i / 2
				curMax = Math.max(curMax, Math.max(j, f[j]) * Math.max(i - j, f[i - j]));   // !!
			}
			f[i] = curMax;
		}
		
		
		return f[length];
	}
	
	// another DP
	public int maxProduct2222(int length) {
		int[] f = new int[length + 1];
		// base case
		f[1] = 1;
		// induction rules
		for (int i = 2; i <= length; i++) {
			int curMax = Integer.MIN_VALUE;
			for (int j = 1; j <= i - 1; j++) {  // can be improved to j <= i / 2
				curMax = Math.max(curMax, Math.max(i - j, f[i - j]) * j);   // Math.max(j, f[j]) * (i - j) also correct
			}
			f[i] = curMax;
		}
		
		
		return f[length];
	}
	
	
	// method 3: Greedy
	// O(1) time
	public int maxProduct0000(int length) {
	    if (length == 2) {
	      return 1;
	    } else if (length == 3) {
	      return 2;
	    }
	    
	    int count = length / 3;
	    int remain = length % 3;
	    if (remain == 1) {
	      return (int) Math.pow(3, count - 1) * 4;  // since 3 * 1 < 4
	    } else if (remain == 2) {
	      return (int) Math.pow(3, count) * 2;  // since 3 * 2 > 5
	    }
	    return (int) Math.pow(3, count);   
	}
}
