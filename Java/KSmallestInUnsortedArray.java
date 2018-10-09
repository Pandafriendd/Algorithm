public class Solution {
  public int[] kSmallest(int[] array, int k) {
    // Write your solution here
    if(k == 0) {
      return new int[] {};
    }
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    for(int x : array) {
      if(maxHeap.size() < k) {
        maxHeap.offer(x);
      }
      else {   // size == k
        if(x < maxHeap.peek()) {
          maxHeap.poll();
          maxHeap.offer(x);
        }
        // else x >= peerk(), ignore
      }
    }
    
    int[] res = new int[k];
    for(int i = k - 1; i >= 0; i--) {
      res[i] = maxHeap.poll();
    }
    
    return res;
    
  }
}
