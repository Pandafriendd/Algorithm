import java.util.Arrays;

public class threeSumClosest {
	public int threeSumClosest(int[] nums, int target) {
		
     int res = 0;
	        if(nums == null || nums.length == 0)
	            return res;
	        
	 Arrays.sort(nums);
	        
	 res = nums[0] + nums[1] + nums[nums.length - 1];
	 for(int i = 0; i < nums.length - 2; i++){
	        int low = i + 1;
	        int high = nums.length - 1;
	        int sum = 0;
	            
	        while(low < high){
	            sum = nums[i] + nums[low] + nums[high];
	                
	                if(sum == target) {
	                	res = target;
	                	break;
	                }
	                else if (sum > target) {
	                	
	                	high--;
	                }
	                else {
	                	
	                	low++;
	                }
	                int a = Math.abs(sum - target);
		            int b = Math.abs(res- target);
		            res = a >= b ? res : sum;
	            }
	            
	        }
	        
	        return res;
 }
}
