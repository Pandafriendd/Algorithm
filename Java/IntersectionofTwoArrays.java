/*
Given two arrays, write a function to compute their intersection.

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]
Note:

Each element in the result must be unique.
The result can be in any order.
 */

import java.util.*;
public class IntersectionofTwoArrays {
	// app1: sorted array using two pointers Time complexity: O(nlogn)
	 public int[] intersection(int[] nums1, int[] nums2) {
	        Arrays.sort(nums1);
	        Arrays.sort(nums2);
	        Set<Integer> set = new HashSet<>();
	        
	        int i = 0, j = 0;
	        while(i < nums1.length && j < nums2.length){
	            if(nums1[i] == nums2[j]){
	                set.add(nums1[i]);
	                i++;
	                j++;
	            }
	            else if(nums1[i] < nums2[j])
	                i++;
	            else
	                j++;
	        }
	        
	        int[] res = new int[set.size()];
	        int k = 0;
	        for(int num : set)
	            res[k++] = num;
	        
	        return res;
	    }
	 
	 // app2: using hashset!!! O(n)
	 public int[] intersection2(int[] nums1, int[] nums2) {
	        Set<Integer> set = new HashSet<>();
	        Set<Integer> intersect = new HashSet<>();
	        
	        for (int i = 0; i < nums1.length; i++) {
	            set.add(nums1[i]);
	        }
	        
	        for (int i = 0; i < nums2.length; i++) {
	            if (set.contains(nums2[i])) {
	                intersect.add(nums2[i]);
	            }
	        }
	        
	        int[] result = new int[intersect.size()];
	        int i = 0;
	        for (Integer num : intersect) {
	            result[i++] = num;
	        }
	        return result;
	    }
	 
	 // app3: binary search O(nlogn)
	 public int[] intersection3(int[] nums1, int[] nums2) {
	        Set<Integer> set = new HashSet<>();
	        Arrays.sort(nums2);
	        for (Integer num : nums1) {
	            if (binarySearch(nums2, num)) {
	                set.add(num);
	            }
	        }
	        int i = 0;
	        int[] result = new int[set.size()];
	        for (Integer num : set) {
	            result[i++] = num;
	        }
	        return result;
	    }
	    
	    public boolean binarySearch(int[] nums, int target) {
	        int low = 0;
	        int high = nums.length - 1;
	        while (low <= high) {
	            int mid = low + (high - low) / 2;
	            if (nums[mid] == target) {
	                return true;
	            }
	            if (nums[mid] > target) {
	                high = mid - 1;
	            } else {
	                low = mid + 1;
	            }
	        }
	        return false;
	    }
}
