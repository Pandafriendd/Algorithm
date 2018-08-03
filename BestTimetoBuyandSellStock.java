

/*
 Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Note that you cannot sell a stock before you buy one.

Example 1:

Input: [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
             Not 7-1 = 6, as selling price needs to be larger than buying price.
Example 2:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */


public class BestTimetoBuyandSellStock {
	
	
	// Approach 1: brute force
	// we need to find max(prices[j] - prices[i]), for every i and j such that j > i.
	// time: O(n^2). space: O(1)
	public int maxProfit(int[] prices) {
		int maxprofit = 0;
		if(prices == null || prices.length < 2)
			return maxprofit;
		
		for(int i = 0; i < prices.length; i++) {
			int curprofit = 0;
			for(int j = i + 1; j < prices.length; j++) {
				curprofit = prices[j] - prices[i];
				if(curprofit > maxprofit) {
					maxprofit = curprofit;
				}
			}
		}
		return maxprofit;
	}
	
	//Approach 2: one pass
	// we need to find the largest peak following the smallest valley. We can maintain two variables, 
	// minprice and maxprofit, corresponding with the smallest valley and maximum profit 
	// (maximum difference between selling price and minprice) obtained so far respectively.
	
	public int maxProfit2(int[] prices) {
		int minprice = Integer.MAX_VALUE;
		int maxprofit = 0;
		
		for(int i = 0; i < prices.length; i++) {
			// minprice = Math.min(minprice, price[i]);   eequal to following if{}
			if(prices[i] < minprice) {
				minprice = prices[i];
			}
			else if(prices[i] - minprice > maxprofit) {
				maxprofit = prices[i] - minprice;
			}
		}	
		return maxprofit;
	}
	
	  /**
     * Optimized bottom-up approach
     * O(n) Time, O(1) Space
     * Just record yesterday's profit
     * Update min, max and profit
     * If next price is bigger, it's only possible to update the profit
     * If next price is smaller or equal, it's only possible to update min
     */
    public static int maxProfit3(int[] prices) {
        if (prices == null || prices.length < 2) return 0; // need at least 2
        int max = 0;
        int min = Integer.MAX_VALUE; // track the minimum of profit array before cur element
        for (int i = 1; i < prices.length; i++) { // note that i starts from 1
            min = Math.min(min, prices[i]); // update min
            if (prices[i] > prices[i - 1]) max = Math.max(max, prices[i] - min);
        }
        return max;
    }
    
    /**
     * Bottom-up approach
     * O(n) Time, O(n) Space
     * Store max profit of each day using an integer array
     * Profit of next day can only be same as last day or update because of higher price today
     */
    public static int maxProfitB(int[] prices) {
        if (prices == null || prices.length < 2) return 0; // need at least 2 days
        int min = Integer.MAX_VALUE;
        int max = 0;
        int len = prices.length;
        int[] history = new int[len];
        for (int i = 0; i < len - 1; i++) {
            min = Math.min(min, prices[i]);
            if (i > 0) { // skip first day
                history[i] = Math.max(history[i - 1], prices[i] - min);
                max = Math.max(history[i], max);
            }
        }
        return max;
    }	
}
