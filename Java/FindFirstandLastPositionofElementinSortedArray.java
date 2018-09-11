import java.util.Arrays;

/*
Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
 */

public class FindFirstandLastPositionofElementinSortedArray {
	
	// using java lib Arrays.binarySearch is not correct, since if the range contains multiple elements with the specified value, there is no guarantee which one will be found.
	// O(logn)
	public int[] searchRange(int[] nums, int target) {
		int res[] = {-1, -1};
		if(nums == null || nums.length == 0)
			return res;
		
		int index = binarySearch(nums,0, nums.length - 1, target);
		if(index != -1) {
			int left = index;
			int right = index;
			res[0] = left;
			res[1] = right;
			while((left = binarySearch(nums, 0, left - 1, target)) != -1)
				res[0] = left;
			while((right = binarySearch(nums, right + 1, nums.length - 1, target)) != -1)
				res[1] = right;
		}
		
		return res;
	}
	
	private int binarySearch(int[] nums, int left, int right, int target) {
		while(left <= right) {  // !!!! <=
			int mid = left + (right - left) / 2;
			if(nums[mid] == target) {
				return mid;
			}
			else if(nums[mid] < target) {
				left = mid + 1;
			}
			else { // nums[mid] > target
				right = mid - 1;
			} 
		}
		
		return -1;
	}
	
	
	
	// trickyyyyyy!!!!
	public int[] searchRange2222(int[] nums, int target) {
        double left = target - 0.5, right = target + 0.5; // !!!!
        int l = bs(nums, left);
        int r = bs(nums, right);
        
        if(l == r) 
        	return new int[]{-1, -1};
        
        return new int[] {l, r - 1};
	}
    
	public int bs(int[] nums, double target) {
        int l = 0, h = nums.length - 1;
        while(l <= h){
            int m = l + (h - l) / 2;
            if(target > nums[m]) 
            	l = m + 1;
            else 
            	h = m - 1;
        }
        return l;
	}
	
	 /**
     * Suppose we have a binary search helper method
     * With array, start index, end index, and target as arguments
     * We can first search for the target in the whole array
     * If found, then search for its starting position
     * Then search for its ending position
     * Update range with search result and return
     */
    public int[] searchRange2(int[] A, int target) {
        int[] range = {-1, -1};
        if (A == null || A.length == 0) return range;
        int index = binarySearch1(A, 0, A.length - 1, target);
        if (index != -1) {
            int left = index;
            int right = index;
            range[0] = left; // if no more occurrence, set left and right first
            range[1] = right;
            while ((left = binarySearch1(A, 0, left - 1, target)) != -1) range[0] = left;
            while ((right = binarySearch1(A, right + 1, A.length - 1, target))  != -1) range[1] = right;
        }
        return range;
    }
    
    private int binarySearch1(int[] A, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (A[mid] == target) return mid;
            else if (A[mid] > target) right = mid - 1;
            else left = mid + 1;
        }
        return -1;
    }
	
	public static void main(String[] args) {
		FindFirstandLastPositionofElementinSortedArray f = new FindFirstandLastPositionofElementinSortedArray();
		
		int[] a = f.searchRange(new int[] {2,4,4}, 4);
		System.out.println(Arrays.toString(a));
	}
	
	
	/*
	  appraoch: linear search O(n)
	  Given that we did find a valid left index, we can do a second linear scan, 
	  but this time from the right. In this case, the first instance of target encountered will be the rightmost one 
	  (and because a leftmost one exists, there is guaranteed to also be a rightmost one). We then simply return a list containing the two located indices.
	 */
	
	public int[] searchRange3(int[] nums, int target) {
        int[] targetRange = {-1, -1};

        // find the index of the leftmost appearance of `target`.
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                targetRange[0] = i;
                break;
            }
        }

        // if the last loop did not find any index, then there is no valid range
        // and we return [-1, -1].
        if (targetRange[0] == -1) {
            return targetRange;
        }

        // find the index of the rightmost appearance of `target` (by reverse
        // iteration). it is guaranteed to appear.
        for (int j = nums.length-1; j >= 0; j--) {
            if (nums[j] == target) {
                targetRange[1] = j;
                break;
            }
        }

        return targetRange;
    }
	
}
