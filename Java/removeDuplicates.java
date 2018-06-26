import java.util.*;


public class removeDuplicates {
	public int RD(int[] nums){
		int res = 0;
		int index = 1;
		if(nums.length == 0 || nums == null)
			return res;
		for(; index < nums.length; index++){
			if(nums[index] != nums[index-1]){
				res++;
			}
		}
		return res+1;
	}
	
	public static void main(String agrs[]){
		removeDuplicates r = new removeDuplicates();
		int[] nums = {0,1,2,3};
		System.out.println(r.RD(nums));
	}
	
}
