/*
Given a non-empty array of integers, return the k most frequent elements.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]
Note:

You may assume k is always valid, 1 ¡Ü k ¡Ü number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
import java.util.*;
public class TopKFrequentElements {
	
	// my solution time: O(n) + O(k * m)  space: O(n)
	public List<Integer> topKFrequent(int[] nums, int k){
		HashMap<Integer, Integer> map = new HashMap<>();  // nums[i] ==> count 
		List<Integer> res = new ArrayList<>();
		
		for(int i = 0; i < nums.length; i++) {
			if(!map.containsKey(nums[i])) {
				map.put(nums[i], 1);
			}
			else
				map.put(nums[i], map.get(nums[i]) + 1);
		}
		
		while(k > 0) {
			int maxcount = Integer.MIN_VALUE;
			int num = 0;
			for(int key : map.keySet()) {  
				if(map.get(key) > maxcount) {
					maxcount = map.get(key);
					num = key;
				}
			}
			res.add(num);
			map.remove(num);
			k--;
		}
		
		return res;
	}
	
	
	/*
	 app:  Bucket Sort time: O(n)  space: O(n)
	 Build a array of list to be buckets with length 1 to sort.
	 
	 !!!!!! bug: This won't work for: [1,1,1,2,2,2,3,3,4,4,5] k = 3. The expected ans would be: [1,2,3] or [1,2,4] but the logic would return: [1,2,3,4]
	 same scenarios: when there's a frequency tie happens, we either take the first occurring one in the original array or the one with bigger value.
	 */
	public List<Integer> topKFrequent2(int[] nums, int k) {
		List<Integer>[] bucket = new List[nums.length + 1];   // freq ==> nums[i]
		Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();  // nums[i] ==> freq

		for (int n : nums) {
			frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
		}

		for (int key : frequencyMap.keySet()) {
			int frequency = frequencyMap.get(key);
			if (bucket[frequency] == null) {
				bucket[frequency] = new ArrayList<>();
			}
			bucket[frequency].add(key);
		}

		List<Integer> res = new ArrayList<>();

		for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
			if (bucket[pos] != null) {
				res.addAll(bucket[pos]);
			}
		}
		return res;
	}
	
	/*
	 * app: use maxHeap. Put entry into maxHeap so we can always poll a number with largest frequency
	 * O(nlog(n-k))
	 */
	public List<Integer> topKFrequent3(int[] nums, int k){
		List<Integer> res = new ArrayList<>();
		// frequency map
		Map<Integer, Integer> map = new HashMap<>();
		for(int n : nums) {
			map.put(n, map.getOrDefault(n, 0) + 1);
		}
		
		//maxHeap, Comparator: map.value;
		PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> map.get(b) - map.get(a));
		for(int key : map.keySet()) {
			pq.offer(key);
			if(pq.size() > map.size() - k)
				res.add(pq.poll()); // k in res, (n-k) in pq 
		}
		
		return res;
	}
	
	
	// use maxHeap. Put entry into maxHeap so we can always poll a number with largest frequency
	public List<Integer> topKFrequent33(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int n: nums){
            map.put(n, map.getOrDefault(n,0)+1);
        }
           
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>((a,b)->(b.getValue()-a.getValue()));
        for(Map.Entry<Integer,Integer> entry: map.entrySet()){
            maxHeap.add(entry);
        }
        
        List<Integer> res = new ArrayList<>();
        while(res.size()<k){
            Map.Entry<Integer, Integer> entry = maxHeap.poll();
            res.add(entry.getKey());
        }
        return res;
    }
	
}
