import java.util.Arrays;

public class SolutionOfThreeSumClosest {
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
	                	while(nums[high] == nums[high - 1] && high - 1 > low)
	                		high--;
	                	high--;
	                }
	                else {
	                	while(nums[low] == nums[low + 1] && low + 1 < high)
	                		low++;
	                	low++;
	                }
	                int a = Math.abs(sum - target);
		            int b = Math.abs(res- target);
		            res = a >= b ? res : sum;
	            }
	            
	        }
	        System.out.println("*********"+res);
	        return res;
		 
		 
		 
//		 //brute force!!
//		 int res = 0;
//	     if(nums == null || nums.length == 0)
//	          return res;
//	    
//	     res = nums[0] + nums[1] + nums[nums.length - 1];
//	        
//	     for(int i = 0; i < nums.length - 2; i++){
//	    	 System.out.println("&&&&&&&&&&"+i);
//	         int sum = 0;
//		     for(int j = i + 1; j < nums.length; j++) {
//		    	 for(int k = j+1; k < nums.length; k++) {
//		    		 sum = nums[i] + nums[j] + nums[k];
//		    		 System.out.println("sum"+sum);
//		    		 int a = Math.abs(sum - target);
//			         int b = Math.abs(res - target);
//			         res = (a >= b) ? res : sum;
//		    	 }
//		     }         
//	     }
//	     System.out.println("*********"+res);
//	     return res;
	        
	        
	 }
	 
	 public static void main(String[] args) {
		 SolutionOfThreeSumClosest s = new SolutionOfThreeSumClosest();
		 int[] nums = {1,2,4,8,16,32,64,128};
		 int target = 82;
		 int res = s.threeSumClosest(nums, target);
	 }
}
