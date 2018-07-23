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
	
	 /**
     * Add list to res
     * Backtrack from current position to the end of array
     * Skip duplicates first
     * Add number to tempList and pass tempList and i+1 to next backtrack
     * Reset tempList after that
     */
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
			helper(res, tempList, nums, i + 1);   // i + 1 !!!!!!!!!
			tempList.remove(tempList.size() - 1);
		}
	}
	
	
	//Approach 2 without backtracking
	 /**
     * if a number from S is the first one of the numbers with the same value,
     * it can be used to extend all previous subsets and create new non-duplicate subsets.
     * if a number from S is a duplicated number of some value, it cannot be
     * used to extend all previous subsets. Only part of them. 
     * The idea is that this number should help make some different subsets than its predecessor. 
     * So it only needs to extend subsets which contains its predecessor!!!
     * 
     * [1 2 2]
     * []
     * [], [1]
     * [], [1], [2], [1 2]
     * [], [1], [2], [1 2], [2 2], [1 2 2] (add 2 to subsets which have 2)
     */
    public static List<List<Integer>> subsetsWithDup2(int[] num) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res.add(new ArrayList<Integer>()); // empty set
        if (null == num || num.length == 0) return res;
        Arrays.sort(num); // sort first, to make the same elements near each other
        
        int j, prevSize = 0;
        for (int i = 0; i < num.length; i++) {
            if (i != 0 && num[i] == num[i - 1]) // dup number
                j = prevSize; // # of previous sets before last number !!!
            else
                j = 0;
            
            prevSize = res.size(); // # of previous sets
            /*add to previous sets with same num*/   
            for (; j < prevSize; j++) { // if dup number, loop from last oldsize to newsize. eg. [], [1], [2], [1 2]: from 2 to 4(j = 2, 3)
                List<Integer> temp = new ArrayList<Integer>(res.get(j));
                temp.add(num[i]);
                res.add(temp);
            }
        }
        return res;
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
