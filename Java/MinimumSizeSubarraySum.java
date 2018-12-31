class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums.length == 0 || nums == null) {
            return 0;
        }
        
        int minLen = Integer.MAX_VALUE;
        for (int start = 0; start < nums.length; start++) {
            int cur = 0;
            for (int end = start; end < nums.length; end++) {
                cur += nums[end];
                if (cur >= s) {
                    minLen = Math.min(minLen, end - start + 1);
                    break;
                }
            }
        }
        
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
