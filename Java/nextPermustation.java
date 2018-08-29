/*
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
 */

import java.util.Arrays;
public class nextPermustation {
	
	//e.g.6,5,4,8,7,5,1 > 6,5,5,8,7,4,1 > 6,5,5,1,4,8,7
	//1. 从后往前找，若升序，继续往前，直到找到降序的位置，若有降序说明可以permutation
	//2. 找到该降序的位置，在其之后找到比它大的数中最小的数，
	//3. 交换位置，然后 Arrays.sort(nums, index, nums.len)
	//注意各种边界情况!!!!
	public void Solution(int[] nums) {
		if(nums.length <= 1 || nums == null) //!!! nums.len <= 1 !!	
			return ;
		
		int index = nums.length - 1;
		while(index - 1 >= 0 && nums[index] <= nums[index - 1]){  //find the last increasing index from the end
			index--;
		}		
		if(index == 0) {  // mean that there is no decreasing element, cannot have next permutation, so set it to the first one
			Arrays.sort(nums);
			return ;
		}
		
		int small = nums[index] - nums[index - 1]; // index: the last increasing index, index - 1: first deceasing index
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
		// swap(nums, index - 1, secondindex);
		Arrays.sort(nums, index, nums.length);
		
	}
	
	 /**
     * O(n) Time
     * Move pointer to second last element of ascending order 
     * From that index, find the farthest element that is bigger
     * Swap these two elements
     * Reverse the order from the next index
     */
    public void nextPermutation(int[] num) {
        if (num == null || num.length == 0) return;
        for (int i = num.length - 2; i >= 0; i--) {
            if (num[i] < num[i + 1]) {
                int j = num.length - 1;
                for (; j > i; j--) if (num[j] > num[i]) break;
                swap(num, i, j);
                reverse(num, i + 1);
                return;
            }
        }
        reverse(num, 0); 
        return;
    }
    
    private void swap(int[] num, int i, int j) {
        int t = num[i];
        num[i] = num[j];
        num[j] = t;
    }
    
    private void reverse(int[] num, int s) {
        int e = num.length - 1;
        while (s < e) {
            swap(num, s, e);
            s++;
            e--;
        }
    }
	
    /*Process:
    1. Start looking from left to find an index whose number is strictly greater than the number before it
    2. If no such number found, reverse the array and return
    3. if such index is found, store it in swapindex1
    4. Now, again traverse form end of the array, to find the number greater than the number at index swapIndex1
    5. swap the numbers at swapIndex1 and swapIndex2
    6. Now reverse the array after swapIndex1
    */
    public void nextPermutation333(int[] nums) {
        if(nums.length == 1)
            return;
        int i = nums.length-1;
        
        while(i-1 >=0 && nums[i-1] >= nums[i])
            i--;
        
        if(i == 0){
            reverse(nums,0,nums.length-1);
            return;
        }      
   
        int swapIndex1 = i-1;
        int j = nums.length-1;
        while(j > i && nums[j] <= nums[swapIndex1] ){
            j--;
        }    
        //swap the number at swapIndex1 with swapIndex2
        int swapIndex2 = j;
        int hold = nums[swapIndex1];
        nums[swapIndex1] = nums[swapIndex2];
        //place hold at the correct position i.e swapIndex2
        nums[swapIndex2] =hold;
        reverse(nums,swapIndex1+1,nums.length-1);
        
    }
    void reverse(int[]nums, int i, int j){
        int p = i;
        int q = j;
        while(p <= q){
            int temp = nums[p];
            nums[p] = nums[q];
            nums[q] =temp;
            p++;
            q--;
        }
    }


    
	public static void main(String[] args) {
		nextPermustation n = new nextPermustation();
		int[] a = {6,5,4,8,7,5,1}, b = {1,2,3};
		n.Solution(b);
		System.out.println(Arrays.toString(b));
	}
}
