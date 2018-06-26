import java.util.*;

public class threeSum {
	 public List<List<Integer>> threeSum(int[] nums) {
	        List<List<Integer>> res = new ArrayList();
	        if(nums == null || nums.length == 0)
	                return res;
	        Arrays.sort(nums);
	        
	        for(int i = 0; i < nums.length - 2; i++){
	            if (i - 1 >= 0 && nums[i] == nums[i - 1]) continue;
	            
	            int low = i + 1, high = nums.length - 1;    
	            while(low < high){
	                int sum = nums[i] + nums[low] + nums[high];
	                
	                if(sum == 0){
	                    res.add(Arrays.asList(nums[i], nums[low], nums[high]));
	                    while(nums[low] == nums[low + 1] && low + 1 < high)
	                        low++;
	                    while(nums[high] == nums[high - 1] && high -1 > low)
	                        high--;
	                    low++;
	                    high--;
	                }
	                
	                else if(sum < 0)
	                    low++;
	                else
	                    high--;     
	            }
	        }
	        
	        return res;
	    }
	
	public static void main(String[] args){
		
		int[] nums = {-1, 0, 1, 2, -1, -4};
		int a = 5/2;
		threeSum t = new threeSum();
		System.out.print(t.threeSum(nums));
		System.out.print(a);
	}

}
