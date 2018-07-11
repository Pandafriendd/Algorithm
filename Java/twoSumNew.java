import java.util.HashMap;
import java.util.Map;

public class twoSum {
	//hashmap with two iterations
	//time: O(n), space: O(n)
	//What is the best way to maintain a mapping of each element in the array to its index? A hash table.
	public int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();  //key--value, value--index
        for(int i = 0; i < nums.length; i++)
            map.put(nums[i],i);
        for(int i = 0; i < nums.length; i++){
            int newTarget = target - nums[i];
            if(map.containsKey(newTarget) && i != map.get(newTarget)) // should not be itself
                return new int[] {i, map.get(newTarget)};
        }
        throw new IllegalArgumentException("No two sum solution");
    }
	
	
	
	//hashmap with one iteration
	//time: O(n)   space: O(1)
	public int[] twoSum2(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = 0; i < nums.length; i++) {
			int newTarget = target - nums[i];
			if(map.containsKey(newTarget))
				return new int[] {map.get(newTarget), i};
			map.put(nums[i], i);
		}
		throw new IllegalArgumentException("No two sum solution");
	}
	
	
	
	//brute force time: O(n^2)   space: O(1)
	public int[] twoSum3(int[] nums, int target) {
		for(int i = 0; i < nums.length; i++) {
			for(int j = i; j < nums.length; j++) {
				if(nums[j] == target - nums[i])
					return new int[] {i, j};
			}
		}
		throw new IllegalArgumentException("No two sum solution");
	}
	
	
	
	
}
