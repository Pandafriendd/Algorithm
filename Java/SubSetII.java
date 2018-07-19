/*
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
 * Input: [1,2,2] 
 * Output: 
 * [ 
 * [2], 
 * [1], 
 * [1,2,2], 
 * [2,2], 
 * [1,2], 
 * [] 
 * ]
 */


import java.util.*;

public class SubSetII {
	
	// contain duplicates  backtracking
	public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        helper(res, new ArrayList<>(), nums, 0);
        return res;
    }
	
	
	private void helper(List<List<Integer>> res, List<Integer> tempList, int[] nums, int start) {
		res.add(new ArrayList<>(tempList));
		for(int i = start; i < nums.length; i++) {
			if(i > start && nums[i] == nums[i - 1])  // skip duplicates
				continue;
			tempList.add(nums[i]);
			helper(res, tempList, nums, start + 1);
			tempList.remove(tempList.size() - 1);
		}
	}
	
	// without duplicate
	public List<List<Integer>> subsets(int[] nums) {
	    List<List<Integer>> list = new ArrayList<>();
	    Arrays.sort(nums);
	    backtrack(list, new ArrayList<>(), nums, 0);
	    return list;
	}

	private void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
	    list.add(new ArrayList<>(tempList));
	    for(int i = start; i < nums.length; i++){
	        tempList.add(nums[i]);
	        backtrack(list, tempList, nums, i + 1);
	        tempList.remove(tempList.size() - 1);
	    }
	}
}
