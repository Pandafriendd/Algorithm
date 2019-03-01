/*
Numbers can be regarded as product of its factors. For example,

8 = 2 x 2 x 2;
  = 2 x 4.
Write a function that takes an integer n and return all possible combinations of its factors.

Note:

You may assume that n is always positive.
Factors should be greater than 1 and less than n.
Example 1:

Input: 1
Output: []
Example 2:

Input: 37
Output:[]
Example 3:

Input: 12
Output:
[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]
Example 4:

Input: 32
Output:
[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]
 */
import java.util.*;

public class FactorCombinations {
	public static List<List<Integer>> getFactors(int n) {
        List<Integer> factors = new ArrayList<>();
        
        for (int i = n / 2; i >= 2; i--) {
            if (n % i == 0) {
                factors.add(i);
            }
        }
        
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        helper(res, temp, factors, n, 0);
        
        return res;
    }
    
    private static void helper(List<List<Integer>> res, List<Integer> temp, List<Integer> factors, int remain, int index) {
        // base case
        if (index == factors.size()) {
        	if (remain == 1) {
        		res.add(new ArrayList<>(temp));
        	}
        	return;
        }
        
        // recursive rule
        int factor = factors.get(index);
        int maxCanUse = 0;
        int r = remain;
        while(r % factor == 0) {
        	r = r / factor;
        	maxCanUse++;
        }
        for (int i = 0; i <= maxCanUse; i++) {
        	int count = i;
        	while (count > 0) {
        		temp.add(factor);
        		count--;
        	}
        	remain = (int) (remain / Math.pow(factor, i));
        	helper(res, temp, factors, remain, index + 1);
        	count = i;
        	while (count > 0) {
        		temp.remove(temp.size() - 1);
        		count--;
        	}
        	remain = remain * ((int) Math.pow(factor, i));  // !!!!!!!!!!!
        }     
    }
    
    public static void main(String[] args) {
    	System.out.println(getFactors(32));
    }
}
