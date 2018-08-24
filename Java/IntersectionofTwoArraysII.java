/*
Given two arrays, write a function to compute their intersection.

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]
Note:

Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.
Follow up:

What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?

 */
import java.util.*;
public class IntersectionofTwoArraysII {
	
	// hashmap time: O(n) space: O(n)
	public int[] intersect(int[] nums1, int[] nums2) {
		HashMap<Integer, Integer> map = new HashMap<>();
		List<Integer> result = new ArrayList<>();
		
		if(nums1.length < nums2.length) { // make sure that nums1 always has longer length 
			int [] temp = nums1;
			nums1 = nums2;
			nums2 = temp;
		}
		
		// freq count for nums1
		for(int i = 0; i < nums1.length; i++) {
			map.put(nums1[i], map.getOrDefault(nums1[i], 0) + 1);
		}
		
		for(int i = 0; i < nums2.length; i++) {
			if(map.containsKey(nums2[i]) && map.get(nums2[i]) > 0) {  // !&& map.get(nums2[i]) > 0 since the value could be 0, means that there is no more freq could be matched in nums1. Or we can do map.remove(num2[i], 0)
				result.add(nums2[i]);
				map.put(nums2[i], map.get(nums2[i]) - 1);
			}
		}
		
		for (int num: nums2) {
            if (map.containsKey(num)){
                result.add(num);
                map.put(num, map.get(num) - 1);
                map.remove(num, 0);  // Removes the entry for the specified key only if it is currently mapped to the specified value
            }
        }
		
		
		int[] res = new int[result.size()];
		for(int i = 0; i < res.length; i++) {
			res[i] = result.get(i);
		}
		
		return res;
	}
	
	/*
	 * two pointers, for sorted array
	 */
	public int[] intersect2(int[] nums1, int[] nums2) {
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		//List<Integer> res = new ArrayList<>();
		int[] res = new int[Math.max(nums1.length,nums2.length)];
		
		int i = 0, j = 0, count = 0;
		while(i < nums1.length && j < nums2.length) {
			if(nums1[i] == nums2[j]) {
				//res.add(nums1[i]);
				res[count] = nums1[i];
				i++;
				j++;
				count++;
			}
			else if(nums1[i] < nums2[j]){
				i++;
			}
			else
				j++;
		}
		/*
		int[] result = new int[res.size()];
		for(int k = 0; k < res.size(); k++)
			result[k] = res.get(k);
		
		return result;
		*/
		
		//copyOf is a good method          
        return Arrays.copyOf(res,count);
	}
	
	/*
	 follow up question 3:
	 if only nums2 cannot fit in memory, put all elements of nums1 into a HashMap, read chunks of array that fit into the memory, and record the intersections.

	 if both nums1 and nums2 are so huge that neither fit into the memory, sort them individually (external sort), then read 2 elements from each array at a time in memory, record intersections.
	 */
	
}





















