/*
Given a target integer T and an integer array A sorted in ascending order, find the index of the first occurrence of T in A or return -1 if there is no such index.

Assumptions

There can be duplicate elements in the array.
Examples

A = {1, 2, 3}, T = 2, return 1
A = {1, 2, 3}, T = 4, return -1
A = {1, 2, 2, 2, 3}, T = 2, return 1
Corner Cases

What if A is null or A of zero length? We should return -1 in this case.
 */
public class FirstOccurrence {
	public int firstOccur(int[] array, int target) {
	    // Write your solution here
	    if(array.length == 0 || array == null) {
	      return -1;
	    }
	    
	    return binarySearch(array, target);
	  }
	  
	  private int binarySearch(int[] array, int target) {
	    int left = 0, right = array.length - 1;
	    while(left < right - 1) {   // if left neighbors right --> terminate
	      int mid = left + (right - left) / 2;
	      if(array[mid] >= target) {
	        right = mid;
	      }
	      else {  // array[mid] < target
	        left = mid + 1;
	      }
	    }

	    if(array[left] == target)
	      return left;
	    else if(array[right] == target)
	      return right;
	    else
	      return -1; 
	  }
}
