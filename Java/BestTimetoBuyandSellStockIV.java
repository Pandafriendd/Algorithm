import java.util.Arrays;

// k transactions
public class BestTimetoBuyandSellStockIV {
	public int maxProfit(int k, int[] prices) {
        if(prices.length == 0 || prices == null || k == 0)
            return 0;
        // !!!
        if(k > prices.length / 2) //The maximum transactions can make is len /2. this condition should be included to avoid  Memory Limit Exceeded error. 
            k = prices.length / 2;
        
        int[] dp = new int[k + 1]; // maximum profit for i transactions. i: 1 -> k
        int[] min = new int[k + 1]; 
        Arrays.fill(min, prices[0]);
        
        for(int i = 0; i < prices.length; i++){
            for(int j = 1; j <= k; j++){
                min[j] = Math.min(min[j], prices[i] - dp[j - 1]);
                dp[j] = Math.max(dp[j], prices[i] - min[j]);
            }
        }
        
        return dp[k];
    }
}
