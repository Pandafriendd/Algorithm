import java.util.*;

public class Subsets {
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
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
	
}
