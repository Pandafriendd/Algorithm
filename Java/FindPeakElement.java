/*
A peak element is an element that is greater than its neighbors.

Given an input array nums, where nums[i] ¡Ù nums[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that nums[-1] = nums[n] = -¡Þ. 

Example 1:

Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.
Example 2:

Input: nums = [1,2,1,3,5,6,4]
Output: 1 or 5 
Explanation: Your function can return either index number 1 where the peak element is 2, 
             or index number 5 where the peak element is 6.
 */
public class FindPeakElement {
	
	//approach 1: linear scan  time: O(n)
	//Whenever, we find a number nums[i], we only need to check if it is larger than the next number nums[i+1] for determining if nums[i] is the peak element.
	//The reasoning behind this can be understood by taking the following three cases which cover every case into which any problem can be divided.
	public int findPeakElement(int[] nums) {
		for(int i = 0; i < nums.length - 1; i++) { // !!! i < nums.length - 1
			if(nums[i] > nums[i + 1])  // !!
				return i;
		}
		return nums.length - 1;
	}
	
	/*
	 * approach 2: recursive binary search O(logn)
 In this case, we use a modification of this simple Binary Search to our advantage. We start off by finding the middle element, midmid from the given numsnums array. If this element happens to be lying in a descending sequence of numbers. or a local falling slope(found by comparing nums[i]nums[i] to its right neighbour), it means that the peak will always lie towards the left of this element. Thus, we reduce the search space to the left of midmid(including itself) and perform the same process on left subarray.

If the middle element, midmid lies in an ascending sequence of numbers, or a rising slope(found by comparing nums[i]nums[i] to its right neighbour), it obviously implies that the peak lies towards the right of this element. Thus, we reduce the search space to the right of midmid and perform the same process on the right subarray.

In this way, we keep on reducing the search space till we eventually reach a state where only one element is remaining in the search space. This single element is the peak element.
	 */
	public int findPeakElement2(int[] nums) {
		return search(nums, 0, nums.length - 1);
	}
	
	private int search(int[] nums, int l, int r) {
		if(l == r)
			return l;
		int mid = l + (r - l) / 2;
		if(nums[mid] > nums[mid + 1])   // peak at left halve
			return search(nums, l, mid);
		return search(nums, mid + 1, r);  // else peak at right halve
	}
	
	
	/*
	 Approach 3: Iterative Binary Search
	 */
	 public int findPeakElement3(int[] nums) {
	        int l = 0, r = nums.length - 1;
	        while (l < r) {
	            int mid = (l + r) / 2;
	            if (nums[mid] > nums[mid + 1]) // should be left
	                r = mid;
	            else  // should be right
	                l = mid + 1;
	        }
	        return l;
	    }
}
