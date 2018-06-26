import java.util.Arrays;

//e.g.6,5,4,8,7,5,1 > 6,5,5,8,7,4,1 > 6,5,5,1,4,8,7
//从后往前找升序，若有升序说明可以permutation
//找到升序的位置，在其之后找到比它大的数中最小的数，交换位置，然后 Arrays.sort(nums, index, nums.len)
//注意各种边界情况!!!!

public class nextPermustation {
	public void Solution(int[] nums) {
		
		if(nums.length <= 1 || nums == null) //!!! nums.len <= 1 !!	
			return ;
		int index = nums.length - 1;
		while(index >= 1 && nums[index] <= nums[index - 1]){  //!!! index >= 1 !!!!
			index--;
		}
		
		if(index == 0) {
			Arrays.sort(nums);
			return ;
		}
		
		
		int small = nums[index] - nums[index - 1];
		int secondindex = index; //!!!the index of the smallest number among numbers that bigger than nums[index]
		for(int i = nums.length - 1; i >= index; i--) {
			if(nums[i] > nums[index - 1] && small > nums[i] - nums[index - 1]) {
				small = nums[i] - nums[index - 1];
				secondindex = i;
			}	
		}

		int tmp = nums[index - 1];
		nums[index -1] = nums[secondindex];
		nums[secondindex] = tmp;
		Arrays.sort(nums, index, nums.length);
		
	}
	
	public static void main(String[] args) {
		nextPermustation n = new nextPermustation();
		int[] a = {6,5,4,8,7,5,1}, b = {1,2,3};
		n.Solution(b);
		System.out.println(Arrays.toString(b));
	}
}
