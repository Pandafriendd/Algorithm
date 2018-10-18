/*
 * Input: nums = [1,2,3]
 * Output:
 *[
 * [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 *]
 */
import java.util.*;

public class Subsets {
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();  // !!
		Arrays.sort(nums);
		helper(res, new ArrayList<>(), nums, 0);
		return res;
	}
	
	private void helper(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
		list.add(new ArrayList<Integer>(tempList));
		for(int i = start; i < nums.length; i++) {
			tempList.add(nums[i]);
			helper(list,tempList, nums, i + 1);
			tempList.remove(tempList.size() - 1);
		}
	}
	
	
	// with no recursion 
	// time: O(2^n), n = nums.length  The inner loop will add one and only one new list to result every time, and in the end you have 2^n of subsets, 
	// meaning you would execute the inner loop only 2^n times.  Focus on this line: result.add(), it was called 2^n times. That's the real time complexity.
	/*
	 * While iterating through all numbers, for each new number, we can either pick it or not pick it
	1, if pick, just add current number to every existing subset.
	2, if not pick, just leave all existing subsets as they are.
	We just combine both into our result.

	For example, {1,2,3} initially we have an empty set as result [ [ ] ]
	Considering 1, if not use it, still [ ], if use 1, add it to [ ], so we have [1] now
	Combine them, now we have [ [ ], [1] ] as all possible subset

	Next considering 2, if not use it, we still have [ [ ], [1] ], if use 2, just add 2 to each previous subset, we have [2], [1,2]
	Combine them, now we have [ [ ], [1], [2], [1,2] ]

	Next considering 3, if not use it, we still have [ [ ], [1], [2], [1,2] ], if use 3, just add 3 to each previous subset, we have [ [3], [1,3], [2,3], [1,2,3] ]
	Combine them, now we have [ [ ], [1], [2], [1,2], [3], [1,3], [2,3], [1,2,3] ]
	 */
	public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        
        result.add(new ArrayList<>()); // start with an empty set
        for (int i = 0; i < nums.length; i++) { // copy every subsets and add nums[i] to each of them
            int size = result.size();
            for (int j = 0; j < size; j++) {
                List<Integer> subset = new ArrayList<>(result.get(j));
                subset.add(nums[i]);
                // System.out.println("add: " + nums[i]);
                result.add(subset);
            }
        }
        return result;
    }
	
	
	
}
