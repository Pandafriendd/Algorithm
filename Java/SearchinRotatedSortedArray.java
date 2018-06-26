import java.util.Arrays;

public class SearchinRotatedSortedArray {
	public int Solution(int[] nums, int target) {
		if(nums.length == 0 || nums == null)
            return -1;
        int l = 0;
        int r = nums.length - 1;
        int m;
        while(l <= r){
        	 m = l + (r - l) / 2;
            if(nums[m] == target)
                return m;
            if(nums[m] >= nums[l]){  // left is sorted // !!!! =
                if(target < nums[m] && target >= nums[l]) { // t is in left-sorted //!!!!target >= nums[l] !! =
                    r = m - 1;
                }
                else { // t is not in left-sorted
                	l = m + 1;
                }
            }
            else { // right is sorted
            	if(target > nums[m] && target <= nums[r]) { // t is in right-sorted //!!!!
            		l = m + 1;
            	}
            	else { // t is not in right-sorted
            		r = m - 1;
            	}
            }
        }
        return -1;
	}
	
	
	public static void main(String[] args) {
		SearchinRotatedSortedArray s =  new SearchinRotatedSortedArray();
		int[] a = {4,5,6,7,0,1,2};
		int res = s.Solution(a, 0);
		System.out.println(res);
	}
}
