/*
Given a target integer T, a non-negative integer K and an integer array A sorted in ascending order, find the K closest numbers to T in A.

Assumptions

A is not null
K is guranteed to be >= 0 and K is guranteed to be <= A.length
Return a size K integer array containing the K closest numbers(not indices) in A, sorted in ascending order by the difference between the number and T. 
Examples

A = {1, 2, 3}, T = 2, K = 3, return {2, 1, 3} or {2, 3, 1}
A = {1, 4, 6, 8}, T = 3, K = 3, return {4, 1, 6}
 */


public class KClosestInSortedArray {
	
	
	// time: O(logn + k)  if k is too large, say k >> n, it will take O(k), which is linear time
	public int[] kClosest(int[] array, int target, int k) {
	    // Write your solution here
	    return binarySearch(array, target, k);
	  }
	  
	  private int[] binarySearch(int[] array, int target, int k) {
	    int[] res = new int[k];
	    int l = 0, r = array.length - 1;
	    
	    while(l < r - 1) {
	      int mid = l + (r - l) / 2; 
	      if(array[mid] > target) {
	        r = mid;
	      }
	      else { // array[mid] < target
	        l = mid;   //  l = mid + 1 is not right, since l may not be the largest smaller, maybe bigger than target, and this is not what we want
	      }
	    }
	    
	    // now l is largest smaller than or equal to target, or l could be the smallest larger than target.  r is l + 1
	    int i = 0;
	    while(i < k) { // move left pointer smaller if 1. right pointer is already out of bound 2. right isn't out, left isn't out, and array[left] is closer to target 
	    	if(r >= array.length || l >= 0 && target - array[l] < array[r] - target) {   // !!!!!!! this condition very important 
	    		res[i++] = array[l--];
	    	}
	    	else {
	    		res[i++] = array[r++];
		}
	    }  
	    return res;
	  }
	  
	  // follow up: k is too large
	  /**
	   * use the idea that solves kth smallest in two sorted array
	   * 1. binarySearch to find left and right
	   * 2. split to two arrays: A[0 ... left], B[right ... len - 1], 
	   * 3. calculate Math.abs(A/B[i] - target) to find distance to target, and A and B will be sorted arrays
	   * 4. find kth smallest in two sorted array 
	   * 
	   * Time: O(logn + logk)
	   */
	  
	  
	  
	  public static void main(String[] args) {
		  KClosestInSortedArray k = new KClosestInSortedArray();
		  //k.kClosest(new int[] {1,3,3,6,9,9,12,15}, 10, 5);
		  // try this case: t = -3, a= {1,3,5}
		  k.kClosest(new int[] {1,3,5,7,9}, -4, 3);
	  }
}
