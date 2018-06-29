import java.util.*;

public class combinationSumII {
	public List<List<Integer>> Solution(int[] nums, int target){
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if(nums.length == 0 || nums == null)
			return res;
		
		Arrays.sort(nums);
		
		helper(nums, target, 0, new ArrayList<Integer>(), res);
		
		return res;
	}
	
	private void helper(int[] nums, int t, int p, List<Integer> comb, List<List<Integer>> res) {
		System.out.print("*" + t + "p" + p + comb.toString() + "*");

		if(t == 0) {
			System.out.print("!!!ret!!!");
			res.add(new ArrayList<Integer>(comb));
			return;
		}
		
		for(int i = p; i < nums.length; i++) { // !!! i = p
			
			int newT = t - nums[i];
			
			if(newT >= 0) { // = 0 is fine since helper will check if t == 0
				comb.add(nums[i]);
				helper(nums, newT, i + 1, comb, res);
				comb.remove(comb.size() - 1);
			}
			else {
				break;
			}
			
			while(i < nums.length - 1 && nums[i] == nums[i + 1]) //skip dup in nums, this code should be here since dup element could be answer
				i++;
		}
	}
	
	
	
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
	    List<List<Integer>> res = new ArrayList<>();
	    List<Integer> list = new ArrayList<>();
	    if(candidates == null){
	      return res;
	    }
	    Arrays.sort(candidates);
	    dfs(0, target, candidates, list, res);
	    return res;
	  }
	  
	  private void dfs(int level, int target, int[]candidates, List<Integer> list, List<List<Integer>>res){
		//level determine the number of layers of the recursion 
	    if(level == candidates.length){
	      if(target == 0){
	        res.add(new ArrayList<Integer>(list));
	      }
	      return;
	    }//base case
	    //for faster execution
	    if(target < 0){
	        return;
	    }
	    //Each level has two cases:
	    //case1: add candidate[level]
	    list.add(candidates[level]);
	    dfs(level+1, target-candidates[level], candidates, list, res);
	    list.remove(list.size()-1);
	    while(level < candidates.length-1 && candidates[level] == candidates[level+1])//deal with duplicate
	      level++;
	     //case2: do not add candidate[level]
	    dfs(level+1, target, candidates, list, res);
	    
	  }
	
	public static void main(String[] args) {
		combinationSumII c = new combinationSumII();
		int[] a = {2, 3, 6, 7};
		c.Solution(a, 9);
	}
}
