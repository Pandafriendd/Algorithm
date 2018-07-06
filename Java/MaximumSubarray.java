
public class MaximumSubarray {
	
	public static void main(String[] args) {
		int[] nums = { -2, 1, -3, -3, 4, -1, 2, 1, -5, 4 };
		MaximumSubarray m = new MaximumSubarray();
		int[] a = m.solution3(nums);
		for(int i = 0; i < a.length; i++)
			System.out.println(a[i]);
	}
	
	/*
	 * beats 100% !!!!
	 * DP?? O(n) time, O(1) space
	 * if nums[i] < 0 && curMax + nums[i] < 0, should recalculate the max,exclude nums[i] and keep finding
	 * if nums[i] < 0 && curMax + nums[i] > 0, should continue, include nums[i] and keep finding
	 */
	public int solution(int[] nums) {
		int res = 0;
		if(nums == null || nums.length == 0)
			return res;
		
		res = nums[0];
		int curMax = nums[0];
		for(int i = 1; i < nums.length; i++) { //!!! i starts from 1
			curMax = Math.max(curMax + nums[i], nums[i]); //if curMax < 0, recalculate, curMax should be changed, curMax = nums[i]
			res = Math.max(curMax, res); // keep recording max for each round
			System.out.println("curMax=" + curMax + "Max=" + res);
		}
		
		return res;
	}
	
	/**
    * DP, O(n) Time, O(n) Space
    */
   int solution2(int[] nums) {
       if (nums == null || nums.length == 0) return 0;
       int[] s = new int[nums.length]; // save max sum so far in an array
       s[0] = nums[0];
       int max = nums[0]; 
       for (int i = 1; i < nums.length; i++) {
          s[i] = s[i - 1] > 0 ? (nums[i] + s[i - 1]) : nums[i];
          max = Math.max(max, s[i]); 
       }
       return max;
   }
   
   
   /**
    * Not asking sum, but the range
    * If nums[i] < 0, current sum + nums[i] >= 0, we can continue addition because 
    * the positive sum would still contribute to positiveness of the subarray. 
    * If nums[i] < 0, current sum + nums[i] < 0, the current subarray has to end.
    */
	public int[] solution3(int[] nums) {   // !!! return val is a array!!
		
		int beginTemp = 0; // save the temporary begining index
        int begin = 0; // begining index
        int end = 0; // ending index
        int maxSoFar = nums[0]; // max sum of previous sequence
        int maxEndingHere = nums[0]; // max sum of this group
        
        for (int i = 1; i < nums.length; i++) {
            if (maxEndingHere < 0) { // last A[i] is too small
                maxEndingHere = nums[i];
                beginTemp = i; // update begin temp
            } else {
                maxEndingHere += nums[i];
            }
            
            if (maxEndingHere >= maxSoFar) { // update max so far
                maxSoFar = maxEndingHere;
                begin = beginTemp; // save index range
                end = i;
            }
        }
        System.out.println("begin=" + begin + "end=" + end + "maxsofar=" + maxSoFar);
        return new int[] {begin, end, maxSoFar};
	}
}
