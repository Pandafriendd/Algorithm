int low = 0, high = A.length;
while(low<high) {
	mid=low+(high-low)/2; // low<=mid, mid<high
	if (nums[mid]>=target) high=mid; // high always decreases (even high-low==1)
	else low=mid+1; // low always increases
}
return low;

//mine
class Solution {
    public int searchInsert(int[] nums, int target) {
        for(int i = 0; i < nums.length; i++)
            if(target <= nums[i])
                return i;
        return nums.length;
    }
}