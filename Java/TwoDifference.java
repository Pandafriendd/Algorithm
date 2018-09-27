
import java.util.*;
public class TwoDifference {
	/*
     * Complete the 'kDifference' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY a
     *  2. INTEGER k
     */

    public static int kDifference(List<Integer> a, int k) {
    // Write your code here
        if(a == null || a.size() == 0)
            return 0;
        
        HashSet<Integer> set = new HashSet<>();
        for(int num : a) {
            set.add(num);
        }
        
        int count = 0;
        for(int num : set) {
            int target = num + k;
            if(set.contains(target)) {
                count++;
            }
        }
        
        return count;
    }
}
