import java.util.*;
public class fourSum {
	public List<List<Integer>> Solution(int[] nums, int target) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if(nums.length < 4 || nums == null)
			return res;
		
		Arrays.sort(nums);
		
		for(int i = 0; i < nums.length - 3; i++) {
			if(i > 0 && nums[i] == nums[i - 1])
				continue; //skip duplicate;
			for(int j = i + 1; j < nums.length - 2; j++) {
				if(j > i + 1 && nums[j] == nums[j - 1])
					continue; //skip duplicate ?? j > i
				
				int newTar = target - nums[i] - nums[j];
				int l = j + 1;
				int r = nums.length - 1;
				while(l < r) {
					if(l > j + 1 && nums[l] == nums[l - 1]) {
						l++;
						continue; // skip du;
					}
					if(r < nums.length - 1 && nums[r] == nums[r + 1]) {
						r--;
						continue;
					}
					int sum = nums[l] + nums[r];
					if(sum < newTar)
						l++; // ????????
					else if(sum > newTar)
						r--;
					else {
						res.add(new ArrayList<Integer>(Arrays.asList(nums[i], nums[j], nums[l], nums[r])));
						l++;
						r--;
					}
					
				}
			}
			
			
		}
		
		return res;
	}
	
	
}
