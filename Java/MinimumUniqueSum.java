/*
 * 1 2 2 3 >> 1 2 3 4  sum: 10
 */

import java.util.*;
public class MinimumUniqueSum {
	static int getMinimumUniqueSum(int[] arr) {
        if(arr == null || arr.length == 0) {
            return 0;
        }
        
        Arrays.sort(arr);
        int pre = 0, sum = 0;
        for (int x : arr) {
            pre = Math.max(pre + 1, x);
            sum += pre;
        }
        return sum;
    }
}
