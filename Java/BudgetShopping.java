
import java.util.*;
public class BudgetShopping {
	/*
     * Complete the 'budgetShopping' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER_ARRAY bundleQuantities
     *  3. INTEGER_ARRAY bundleCosts
     */

    public static int budgetShopping(int n, List<Integer> bundleQuantities, List<Integer> bundleCosts)
    {
    // Write your code here
        // dynamic programming technique
        int[] f = new int[n + 1];
        for(int i = 0; i < bundleCosts.size(); i++) {
            for(int j = bundleCosts.get(i); j <= n; j++) {
                f[j] = Math.max(f[j - bundleCosts.get(i)] + bundleQuantities.get(i), f[j]);
            }
        }
        return f[n];
    }

}
