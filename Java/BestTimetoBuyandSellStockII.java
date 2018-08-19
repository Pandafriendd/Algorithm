/*
 Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).

Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

Example 1:

Input: [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
             Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
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
public class BestTimetoBuyandSellStockII {
	
	// approach 1 brute force
	// time: O(n^n), recursive function is called n^n times
	// space: O(n), depth of recursion is n
	public int maxProfit(int[] prices) {
		return helper(prices, 0);
	}
	public int helper(int[] prices, int pos) {
		if(pos >= prices.length)
			return 0 ;
		int max = 0;
		for(int start = pos; start < prices.length; start++) {
			int maxprofit = 0;
			for(int i = start + 1; i < prices.length; i++) {
				if(prices[start] < prices[i]) {
					int curprofit = prices[i] - prices[start] + helper(prices, i + 1);
					maxprofit = Math.max(maxprofit, curprofit);
				}
			}
			max = Math.max(max, maxprofit);
		}
		return max;
	}
	
	// approach 2: peak valley approach
	// we need to consider every peak immediately following a valley to maximize the profit
	// time: O(n) simple pass
	// space: O(1)
	public int maxProfit2(int[] prices) {
		if(prices == null || prices.length < 2)
			return 0;
		
		int i = 0;
		int valley = prices[0];
		int peak = prices[0];
		int maxprofit = 0;
		
		while(i < prices.length - 1) {   // !
			while(i < prices.length - 1 && prices[i] >= prices[i + 1]) { // find valley
				i++;
			}
			valley = prices[i];
			while(i < prices.length - 1 && prices[i] <= prices[i + 1]) { // find peak
				i++;
			}
			peak = prices[i];
			maxprofit += peak - valley;
		}
		
		return maxprofit;
	}
	
	
	/*
	 * Greedy approach
	 * In this case, instead of looking for every peak following a valley, 
	 * we can simply go on crawling over the slope and keep on adding the profit obtained from every consecutive transaction.
	 * we can directly keep on adding the difference between the consecutive numbers of the array if the second number is larger than the first one, 
	 * and at the total sum we obtain will be the maximum profit.
	 */
	  /**
     * Sell whenever there is profit.
     * If next value is bigger, add the difference up to the profit
     */
    public static int maxProfit3(int[] prices) {
        int max = 0;
        for (int i = 0; i < prices.length - 1; i++)
            if (prices[i + 1] > prices[i]) 
            	max += prices[i + 1] - prices[i];
        return max;
    }
}
