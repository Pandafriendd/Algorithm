

/*
 Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

1. You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
2. After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)

Example:

Input: [1,2,3,0,2]
Output: 3 
Explanation: transactions = [buy, sell, cooldown, buy, sell]

 */
public class BestTimetoBuyandSellStockwithCooldown {
	
	/*
For problem 309, the recursive formula is

dp[i] = max(dp[i-1], prices[i] - prices[j] + dp[j-2]), j=[0..i-1]

If we sell the shares on i-th day bought on j-th day, we couldn't trade on (j-1)-th day because of cooldown. So the last one is dp[j-2].
	 */
	
	//straight implementation
	public int maxProfit(int[] prices) {
		if(prices == null || prices.length < 2)
			return 0;
		
		int[] dp = new int[prices.length + 1]; // use one more to guard
		int min = prices[0];
		
		for(int i = 1; i < prices.length; i++) {
			min = Math.min(min, prices[i] - dp[i - 1]);
			dp[i + 1] = Math.max(dp[i], prices[i] - min);  // ?
		}
		
		return dp[prices.length];  // since dp[i + 1] is the maximum profit,so it will be at dp[prices.length]
		// return dp[dp.length - 1];
	}
	
	// compact space
	// Here dp[i] only depends on dp[i-1] and dp[i-2], so dp can be compacted to two variables. The compacted space is O(1)
	
	public int maxProfit2(int[] prices) {
		// dp[i] = max(dp[i-1], prices[i] - prices[j] + dp[j-2]), j=[0..i-1]
		if(prices == null || prices.length < 2)
			return 0;
		// dp can be compacted to two variables
		int prev = 0;
		int res = 0;
		int min = prices[0];
		
		for(int i = 1; i < prices.length; i++) {
			min = Math.min(min, prices[i] - prev);
			prev = res; // !!
			res = Math.max(res, prices[i] - min);
		}
		
		return res;
	}
	
	//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/discuss/135704/Detail-explanation-of-DP-solution
}
