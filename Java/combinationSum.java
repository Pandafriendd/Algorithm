import java.util.*;;

public class combinationSum {
	//backtracking
	public List<List<Integer>> Solution(int[] nums, int target){
		//List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<List<Integer>> res = new ArrayList<>();  // easier 
		if(nums == null || nums.length == 0)
			return res;
		Arrays.sort(nums);
		
		helper(nums, target, 0, new ArrayList<Integer>(), res);
		
		return res;
	}
	
	
	//backtracking
	private void helper(int[] nums, int target, int pos, List<Integer> comb, List<List<Integer>> res) {
		System.out.print("*" + target + "p" + pos + comb.toString());
		if(target == 0) {
			res.add(new ArrayList<Integer>(comb));
			System.out.print("res found!");
			return;
		}
		for(int i = pos; i < nums.length; i++) {
			int newT = target - nums[i];
			if(newT >= 0) {
				comb.add(nums[i]);
				helper(nums, newT, i, comb, res); //still adding i
				System.out.print("remove:" + comb.get(comb.size() - 1).toString());
				comb.remove(comb.size() - 1); // remove one, backtracking
			}
			else
				break;  // nums[i] is two big
		}
	}
	
	public static void main(String[] args) {
		combinationSum c = new combinationSum();
		int[] a = {3, 4, 5};
		c.Solution(a, 9);
	}
	
	
}
