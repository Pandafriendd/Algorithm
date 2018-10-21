import java.util.Arrays;

public class SearchinRotatedSortedArray {

	// idea: there must be one half of array that is still sorted
	public int search(int[] nums, int target) {
        if (nums ==  null || nums.length == 0) {
            return -1;
        }
        
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] >= nums[left]) {  // left is sorted
                if (target == nums[mid]) {
                    return mid;
                }
                else if (target < nums[mid] && target >= nums[left]) {
                    right = mid - 1;
                }
                else { // t > mis[mid] || t < nums[l], search on right
                    left = mid + 1;
                }
            }
            else {  // right is sorted
                if (target == nums[mid]) {
                    return mid;
                }
                else if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                }
                else {  // t < nums[mid] || t > nums[r]
                    right = mid - 1;
                }
            }
        }
        
        return -1;
    }
	
	
	public static void main(String[] args) {
		SearchinRotatedSortedArray s =  new SearchinRotatedSortedArray();
		int[] a = {4,5,6,7,0,1,2};
		int res = s.search(a, 0);
		System.out.println(res);
	}
}
