import java.util.*;
public class DeleteProducts {
	/*
     * Complete the 'deleteProducts' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY ids
     *  2. INTEGER m
     */

    public static int deleteProducts(List<Integer> ids, int m) {
    // Write your code here
        if(ids == null || ids.size() == 0) {
            return 0;
        }
        
        HashMap<Integer, Integer> map = new HashMap<>();  // freq map
        for(Integer id : ids) {
            map.put(id, map.getOrDefault(id, 0) + 1);
        }
        
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(Map.Entry<Integer,Integer> entry : map.entrySet()) {
            queue.add(entry.getValue());
        }
        
        int res = queue.size();
        while(m > 0 && !queue.isEmpty()) {
            int cur = queue.remove();
            if(m >= cur) {
                res = res - 1;
            }
            m = m - cur;
        }
        
        return res;
    }
}
