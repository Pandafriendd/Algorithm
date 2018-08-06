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

For k transactions, max profit up until prices[i]: dp[k, i] = max(dp[k, i-1], prices[i] - prices[j] + dp[k-1, j-1]), j=[0..i-1]

if we don't trade then the profit is same as previous day dp[k, i-1];
and if we bought the share on prices[j] day where j=[0..i-1], 
then sell the share on prices[i] day then the profit is prices[i] - prices[j] + dp[k-1, j-1].
Actually j can be i as well. When j is i, the one more extra item prices[i] - prices[j] + dp[k-1, j] = dp[k-1, i] looks like we just lose one chance of transaction.

I see someone else use the formula dp[k, i] = max(dp[k, i-1], prices[i] - prices[j] + dp[k-1, j]), 
where the last one is dp[k-1, j] instead of dp[k-1, j-1]. 
It's not the direct sense, as if the share was bought on prices[j] day, 
then the total profit of previous transactions should be done on prices[j - 1] day. 
However, the result based on that formula is also correct, 
because if the share was sold on prices[j] day and then bought again, it is the same if we didn't trade on that day.
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
	
	// in above code, min is repeatedly calculated, it can be improved as:
	// time: O(kn) space: O(kn)
	public int MaxProfitCompact1(int[] prices) {
		if(prices.length == 0)
			return 0;
		
		int[][] dp = new int[3][prices.length];
		for(int k = 1; k < 3; k++) {
			int min = prices[0];
			for(int i = 1; i < prices.length; i++) {
				min = Math.min(min, prices[i] - dp[k - 1][i - 1]); // min could be negative, meaning it could have more profit
				dp[k][i] = Math.max(dp[k][i - 1], prices[i] - min); // could have more profit if min is negative
			}
		}
		
		return dp[2][prices.length - 1];
	}
	
	// the second dimension (variable i) is only dependent on the previous one (i-1), so we can compact this dimension.
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
	
	
	
	//expand the array to all named variables
	public int MaxProfitDpCompactFinal(int[] prices)  {
        int buy1 = Integer.MAX_VALUE;
        int buy2 = Integer.MAX_VALUE;
        int sell1 = 0;
        int sell2 = 0;
        
        for(int i = 0; i < prices.length; i++) {
        	buy1 = Math.min(buy1, prices[i]);
        	sell1 = Math.max(sell1, prices[i] - buy1);
        	buy2 = Math.min(buy2, prices[i] - sell1);
        	sell2 = Math.max(sell2, prices[i] - buy2);
        }
        
        return sell2;
    }
	//We can also explain the above codes in other words. 
	// On every day, we buy the share with the price as low as we can, and sell the share with price as high as we can. 
	//For the second transaction, we integrate the profit of first transaction into the cost of the second buy, 
	//then the profit of the second sell will be the total profit of two transactions.
	
	
	
	/*
First assume that we have no money, so buy1 means that we have to borrow money from others, we want to borrow less so that we have to make our balance as max as we can(because this is negative).

sell1 means we decide to sell the stock, after selling it we have price[i] money and we have to give back the money we owed, so we have price[i] - |buy1| = prices[i ] + buy1, we want to make this max.

buy2 means we want to buy another stock, we already have sell1 money, so after buying stock2 we have buy2 = sell1 - price[i] money left, we want more money left, so we make it max

sell2 means we want to sell stock2, we can have price[i] money after selling it, and we have buy2 money left before, so sell2 = buy2 + prices[i], we make this max.

So sell2 is the most money we can have.
	 */
	public int maxProfit222(int[] prices) {
		int sell1 = 0, sell2 = 0, buy1 = Integer.MIN_VALUE, buy2 = Integer.MIN_VALUE;
		for (int i = 0; i < prices.length; i++) {
			buy1 = Math.max(buy1, -prices[i]);
			sell1 = Math.max(sell1, buy1 + prices[i]);
			buy2 = Math.max(buy2, sell1 - prices[i]);
			sell2 = Math.max(sell2, buy2 + prices[i]);
		}
		return sell2;
	}
	
	
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
