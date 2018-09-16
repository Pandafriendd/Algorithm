/*
You are given coins of different denominations and a total amount of money amount. 
Write a function to compute the fewest number of coins that you need to make up that amount. 
If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:
Input: coins = [1, 2, 5], amount = 11
Output: 3 
Explanation: 11 = 5 + 5 + 1

Example 2:
Input: coins = [2], amount = 3
Output: -1

Note:
You may assume that you have an infinite number of each kind of coin.
 */


public class CoinChange {
	
	
	// dp bottom-up solution
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for(int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
            int j = 0;
            while(j < coins.length) {
                if(i - coins[j] >= 0 && dp[i - coins[j]] != Integer.MAX_VALUE)
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);  
                j++;
            }      
        }
        
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount]; 
    }
    
    
    /*
	brute force, backtracking
	the algorithm uses backtracking technique to generate all combinations of coin frequencies in the range which satisfy the constraints
     */
    public int coinChange2(int[] coins, int amount) {
        return coinChange(0, coins, amount);
    }

    private int coinChange(int idxCoin, int[] coins, int amount) {
        if (amount == 0)
            return 0;
        
        if (idxCoin < coins.length && amount > 0) {
            int maxVal = amount/coins[idxCoin];
            int minCost = Integer.MAX_VALUE;
            for (int x = 0; x <= maxVal; x++) {
                if (amount >= x * coins[idxCoin]) {
                    int res = coinChange(idxCoin + 1, coins, amount - x * coins[idxCoin]);
                    if (res != -1)
                        minCost = Math.min(minCost, res + x);
                }                    
            }           
            return (minCost == Integer.MAX_VALUE)? -1: minCost;
        }
        
        return -1;
    } 
    
    
    // dp top-down
    // since recursion leads to the fact that a lot of subproblems were calcaulated multiple times,
    // so we should cache the solutions to the subproblems in a table and access them in constant time when necessary
    // Time: O(S∗n). where S is the amount, n is denomination count.
    // Space complexity : O(S), where S is the amount to change We use extra space for the memoization table.
    public int coinChange3(int[] coins, int amount) {
    	if(amount < 1) 
    		return 0;
    	
    	return helper(coins, amount, new int[amount + 1]);
    }
    
    private int helper(int[] coins, int remain, int[] cache) {
        if(remain  < 0)
            return -1;
        if(remain == 0)
            return 0;
        if(cache[remain] != 0)
            return cache[remain];
        
        int min = Integer.MAX_VALUE;
        for(int coin : coins) {
            int res = helper(coins, remain - coin, cache);
            if(res >= 0 && res < min) {
                min = res + 1;
            }
        }
        cache[remain] = (min == Integer.MAX_VALUE) ? -1 : min;
        
        return cache[remain];
    }
}
