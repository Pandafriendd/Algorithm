

import java.util.*;
public class SubarraySum {
	/*
     * Complete the 'subarraySum' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static long subarraySum(List<Integer> arr) {
        long sum = 0;
        for (int i = 0; i < arr.size(); i++) {
            long x = arr.get(i), y = (i + 1), z = arr.size() - i;
            sum += x*y*z;
        }
        return sum;

    }
}
