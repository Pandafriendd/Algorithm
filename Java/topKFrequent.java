import java.util.*;


public class topKFrequent {
	public List<Integer> topKFrequent1(int[] nums, int k) {
		if(nums.length == 0)
			return null;
		List<Integer> res = new ArrayList<Integer>();
		Map<Integer, Integer> m = new HashMap<Integer, Integer>();
		for(int i = 0; i < nums.length; i++){
			if(!m.containsKey(nums[i])){
				m.put(nums[i], 1);
			}
			else{
				int a = m.get(nums[i]);
				a++;
				m.put(nums[i], a);
			}
		}
		
		System.out.println(m);
		//System.out.println(m.keySet());

		
		for(int i = 0; i < k; i++){
			int max = 0;
			int kk = 0;
			for(int key : m.keySet()){   //!!!!!!!!!!!!!!!!!!!!!!
				if(m.get(key) >= max){
					max = m.get(key);
					kk = key;
				}
				else
					continue;
			}
			res.add(kk);
			m.remove(kk);
		}
		return res;
    }
	
	public List<Integer> topKFrequent2(int[] nums, int k){
		Map<Integer, Integer> map = new HashMap<>();
		for(int n : nums) //key -> nums, value -> frequency
		{
			if(!map.containsKey(n))
				map.put(n, 1);
			else
				map.put(n, map.get(n) + 1);
		}
		
		// corner case: if there is only one number in nums, we need the bucket has index 1
		List<Integer>[] bucket = new List[nums.length + 1];
		for(int key : map.keySet()){
			int freq = map.get(key);
			if(bucket[freq] == null)
				bucket[freq] = new LinkedList<>();
			bucket[freq].add(key);
		}
		
		List<Integer> res = new LinkedList<>();
		for(int i = bucket.length - 1; i > 0 && k > 0; i--){
			if(bucket[i] != null){
				List<Integer> list = bucket[i];
				res.addAll(list);
				k -= list.size();
			}
		}
		
		return res;
	}
	
//	// use maxHeap. Put entry into maxHeap so we can always poll a number with largest frequency
//	//java8???
//	public List<Integer> topKFrequent3(int[] nums, int k) {
//        Map<Integer, Integer> map = new HashMap<>();
//        for(int n : nums) //key -> nums, value -> frequency
//		{
//			if(!map.containsKey(n))
//				map.put(n, 1);
//			else
//				map.put(n, map.get(n) + 1);
//		}
//           
//        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = 
//                         new PriorityQueue<>((a,b)->(b.getValue()-a.getValue()));
//        for(Map.Entry<Integer,Integer> entry: map.entrySet()){
//            maxHeap.add(entry);
//        }
//        
//        
//        List<Integer> res = new ArrayList<>();
//        while(res.size()<k){
//            Map.Entry<Integer, Integer> entry = maxHeap.poll();
//            res.add(entry.getKey());
//        }
//        return res;
//    }
	
	public List<Integer> topKFrequent3(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int n : nums) //key -> nums, value -> frequency
		{
			if(!map.containsKey(n))
				map.put(n, 1);
			else
				map.put(n, map.get(n) + 1);
		}
        
        TreeMap<Integer, List<Integer>> freqMap = new TreeMap<>();
        for(int num : map.keySet()){
           int freq = map.get(num);
           if(!freqMap.containsKey(freq)){
               freqMap.put(freq, new LinkedList<Integer>());
           }
           freqMap.get(freq).add(num);
        }
        
        List<Integer> res = new ArrayList<>();
        while(res.size()<k){
            Map.Entry<Integer, List<Integer>> entry = freqMap.pollLastEntry();
            res.addAll(entry.getValue());
        }
        return res;
    }

	public static void main(String args[]){
		topKFrequent t = new topKFrequent();
		int[] a = {1,1,2,2,2,2,3,3,3,4,4,4,4};

		System.out.println(t.topKFrequent3(a, 3));
	}
}
