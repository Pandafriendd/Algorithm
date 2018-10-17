public class Solution {
  public String[] topKFrequent(String[] combo, int k) {
    // Write your solution here
    
    HashMap<String, Integer> freqMap = new HashMap<>();
    for (String s : combo) {
      freqMap.put(s, freqMap.getOrDefault(s, 0) + 1);
    }
    
    // pass k in constructor would be a good practice
    PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(k, new Comparator<Map.Entry<String, Integer>>() {
      @Override
    public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {  // !!! public is important?
      // return entry1.getValue() - entry2.getValue();
      return entry1.getValue().compareTo(entry2.getValue()); // since value is Integer
    }
    });
    for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
      if (minHeap.size() < k) {
        minHeap.offer(entry);
      }
      else {   // minHeap.size() == k
        if (minHeap.peek().getValue() < entry.getValue()) {
          minHeap.poll();
          minHeap.offer(entry);
        }
      }
    }
    
    int size = minHeap.size();
    String[] res = new String[size];
    for (int i = res.length - 1; i >= 0; i--) {
      res[i] = minHeap.poll().getKey();
    }
    
    return res;
  }
}
