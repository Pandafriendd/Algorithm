class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();  //key--value, value--index
        for(int i = 0; i < nums.length; i++)
            map.put(nums[i],i);
        for(int i = 0; i < nums.length; i++){
            int newTarget = target - nums[i];
            if(map.containsKey(newTarget) && i != map.get(newTarget))
                return new int[] {i, map.get(newTarget)};
        }
        return null;
    }
}