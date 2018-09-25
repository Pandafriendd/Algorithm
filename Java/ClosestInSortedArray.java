/*
Given a target integer T and an integer array A sorted in ascending order, find the index i in A such that A[i] is closest to T.

Assumptions

There can be duplicate elements in the array, and we can return any of the indices with same value.
Examples

A = {1, 2, 3}, T = 2, return 1
A = {1, 4, 6}, T = 3, return 1
A = {1, 4, 6}, T = 5, return 1 or 2
A = {1, 3, 3, 4}, T = 2, return 0 or 1 or 2
Corner Cases

What if A is null or A is of zero length? We should return -1 in this case.
 */
public class ClosestInSortedArray {
	public int closest(int[] array, int target) {
	    // Write your solution here
	    if(array == null || array.length == 0)  // corner cases
	      return -1;
	    int res = binarySearch(array, target);
	    return res;
	  }
	  
	  private int binarySearch(int[] array, int target) {
	    int l = 0, r = array.length - 1;
	    while(l < r - 1) {
	      int mid = l + (r - l) / 2;
	      if(array[mid] == target) {
	        return mid;
	      }
	      if(array[mid] > target) {
	        r = mid;
	      } 
	      else {  //array[mid] < target
	        l = mid;
	      }
	    }
	    
	    return (Math.abs(array[l] - target) < Math.abs(array[r] - target)) ? l : r;
	  }
}
