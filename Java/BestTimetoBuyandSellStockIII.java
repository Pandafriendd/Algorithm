import java.util.Arrays;

/*
 Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

Example 1:

Input: [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
             Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
Example 2:

Input: [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
             Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
             engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */
public class BestTimetoBuyandSellStockIII {
	/*
	 It's not difficult to get the DP recursive formula:

dp[k, i] = max(dp[k, i-1], prices[i] - prices[j] + dp[k-1, j-1]), j=[0..i-1]

For k transactions, on i-th day,
if we don't trade then the profit is same as previous day dp[k, i-1];
and if we bought the share on j-th day where j=[0..i-1], 
then sell the share on i-th day then the profit is prices[i] - prices[j] + dp[k-1, j-1].
Actually j can be i as well. When j is i, the one more extra item prices[i] - prices[j] + dp[k-1, j] = dp[k-1, i] looks like we just lose one chance of transaction.

I see someone else use the formula dp[k, i] = max(dp[k, i-1], prices[i] - prices[j] + dp[k-1, j]), 
where the last one is dp[k-1, j] instead of dp[k-1, j-1]. 
It's not the direct sense, as if the share was bought on j-th day, 
then the total profit of previous transactions should be done on (j-1)th day. 
However, the result based on that formula is also correct, 
because if the share was sold on j-th day and then bought again, it is the same if we didn't trade on that day.
	 */
	// time: O(kn^2) space: O(kn)
	public int MaxProfitDP(int[] prices) {
		if(prices.length == 0)
			return 0;
		int[][] dp = new int[3][prices.length];
		for(int k = 1; k < 3; k++) { // !!
			for(int i = 1; i < prices.length; i++) {  //
				int min = prices[0];
				for(int j = 1; j <= i; j++) {
					min = Math.min(min, prices[j] - dp[k - 1][j - 1]);
				}
				dp[k][i] = Math.max(dp[k][i - 1], prices[i] - min);
			}
		}
		
		return dp[2][prices.length - 1];
	}
	
	// time: O(kn) space: O(kn)
	public int MaxProfitCompact1(int[] prices) {
		if(prices.length == 0)
			return 0;
		
		int[][] dp = new int[3][prices.length];
		for(int k = 1; k < 3; k++) {
			int min = prices[0];
			for(int i = 1; i < prices.length; i++) {
				min = Math.min(min, prices[i] - dp[k - 1][i - 1]);
				dp[k][i] = Math.max(dp[k][i - 1], prices[i] - min);
			}
		}
		
		return dp[2][prices.length - 1];
	}
	
	//time: O(kn) space: O(k)
	public int MaxProfitCompact2(int[] prices) {
		if(prices.length == 0)
			return 0;
		
		int[] dp = new int[3];
		int[] min = new int[3];
		Arrays.fill(min, prices[0]);
		
		for(int i = 0; i < prices.length; i++) {
			for(int k = 1; k < 3; k++) {
				min[k] = Math.min(min[k], prices[i] - dp[k - 1]);
				dp[k] = Math.max(dp[k], prices[i] - min[k]);
			}
		}
		
		return dp[2];
	}
	
	
	
	
	public int maxProfit33(int[] prices) {
        int hold1 = Integer.MIN_VALUE, hold2 = Integer.MIN_VALUE;
        int release1 = 0, release2 = 0;
        for(int i:prices){                              // Assume we only have 0 money at first
            release2 = Math.max(release2, hold2+i);     // The maximum if we've just sold 2nd stock so far.
            hold2    = Math.max(hold2,    release1-i);  // The maximum if we've just buy  2nd stock so far.
            release1 = Math.max(release1, hold1+i);     // The maximum if we've just sold 1nd stock so far.
            hold1    = Math.max(hold1,    -i);          // The maximum if we've just buy  1st stock so far. 
        }
        return release2; ///Since release1 is initiated as 0, so release2 will always higher than release1.
    }
	
	
	
	
	// DP
    /**
     * Goes forward to build single transaction max profit
     * Then goes backward to build max since day i profit
     * Find the max of the sum of these two
     */
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        if (prices == null || prices.length < 2) 
        	return maxProfit;
        int len = prices.length;
        int[] maxBy = new int[len]; // sell in ith day 
        int[] maxSince = new int[len]; // buy in ith day
        int valley = prices[0];
        int peak = prices[len - 1];
        
        for (int i = 1; i < len; i++) {
            valley = Math.min(valley, prices[i]);
            maxBy[i] = Math.max(maxBy[i - 1], prices[i] - valley);
        }
        /*update maxProfit while build maxSince*/
        for (int i = len - 2; i >= 0; i--) {
            peak = Math.max(peak, prices[i]);
            maxSince[i] = Math.max(maxSince[i + 1], peak - prices[i]);
            // find i such that maxBy[i]+maxSince[i+1] is the max two-transaction profit, no overlap
            maxProfit = Math.max(maxProfit, maxBy[i] + maxSince[i]); 
        }
        return maxProfit;
    }
}
