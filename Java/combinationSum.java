import java.util.*;;

public class combinationSum {
	//backtracking
	public List<List<Integer>> Solution(int[] nums, int target){
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if(nums == null || nums.length == 0)
			return res;
		Arrays.sort(nums);
		
		help(nums, target, 0, new ArrayList<Integer>(), res);
		
		return res;
	}
	
	
	//backtracking
	private void help(int[] nums, int target, int pos, List<Integer> comb, List<List<Integer>> res) {
		System.out.print("*" + target + "p" + pos + comb.toString() + "*");
		if(target == 0) {
			res.add(new ArrayList<Integer>(comb));
			System.out.print("ret");
			return;
		}
		for(int i = pos; i < nums.length; i++) {
			int newT = target - nums[i];
			if(newT >= 0) {
				comb.add(nums[i]);
				help(nums, newT, i, comb, res); //adding i
				comb.remove(comb.size() - 1); // remove one, backtracking
			}
			else
				break;  // nums[i] is two big
		}
	}
	
	public static void main(String[] args) {
		combinationSum c = new combinationSum();
		int[] a = {2, 3, 6, 7};
		c.Solution(a, 9);
	}
	
	
}
