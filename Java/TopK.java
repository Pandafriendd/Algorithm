

import java.util.*;
public class TopK {
	public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();  // store the largest k elements processed so far
        for(int x : nums) {
            if(minHeap.size() < k) {
                minHeap.offer(x);
            }
            else {   // size = k
                if(x > minHeap.peek()) {
                    minHeap.poll();
                    minHeap.offer(x);
                }
                
                // x <= minHeap.peek(), ignore
            }
        }
        
        int res = 0;
        for(int i = 0; i < k; i++) {
            res = minHeap.poll();
        }
        
        return res;
    }
	
	public static void main(String[] args) {
		System.out.println(findKthLargest(new int[] {3,2,1,5,6,4}, 2));
	}
}
