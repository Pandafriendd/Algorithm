/*
 Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
Note: 
You may assume k is always valid, 1 ¡Ü k ¡Ü array's length.
 */

import java.util.*;

public class KthLargestElementinanArray {
	/*
	 *  approach1: O(N lg K) running time + O(K) memory
	 */
	public int findKthLargest(int[] nums, int k) {
		Arrays.sort(nums);
		return nums[nums.length - k];
	}
	
	/*
	 approach2: use a min oriented priority queue that will store the K-th largest values.
	 O(N) best case / O(N^2) worst case running time + O(1) memory
	 */
	public int findKth2(int[] nums, int k) {
		final PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int val : nums) {
			pq.offer(val);
			
			if(pq.size() > k)
				pq.poll();
		}
		return pq.peek();
	}
	
	/*
	 use the selection algorithm (based on the partion method - the same one as used in quicksort)
	 O(n) time
	 */
	public int findKth3(int[] nums, int k) {
		int n = nums.length;
		int p = quickSelect(nums, 0, n - 1, n - k); // kth largest in a ascending array equal to (n-k+1)th smallest, which index is n-k
		return nums[p];
	}
	// return the index of the kth smallest number
	// use quick sort's idea
    // put nums that are <= pivot to the left
    // put nums that are  > pivot to the right
	private int quickSelect(int[] nums, int low, int high, int k) {
		int i = low, j = high, pivot = nums[high];
		
		//partition numbers into either side of pivot
		while(i < j) {
			if(nums[i++] > pivot)
				swap(nums, --i, --j); //--i makes sure we can still check a[i] after the swap, and --j makes sure we won't overwrite the ones that are already done.
		}
		swap(nums, i, high);
		
		//how many elements were greater than pivot
		if(i == k) // pivot is the one!
			return i;
		else if(i > k) // len of left halve > k (pivot is too big) so it must be on the left
			return quickSelect(nums, low, i - 1, k);
		else // pivot is too small, so it must be on the right
			return quickSelect(nums, i + 1, high, k);
	}
	private void swap(int[] nums, int i, int j) {
		int t = nums[i];
		nums[i] = nums[j];
		nums[j] = t;
	}
	
}
