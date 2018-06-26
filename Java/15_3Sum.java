//final solution
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new LinkedList();
        if(nums == null || nums.length == 0)
                return res;
        Arrays.sort(nums);
        
        for(int i = 0; i < nums.length - 2; i++){
            if (i - 1 >= 0 && nums[i] == nums[i - 1]) continue;
            
            int low = i + 1, high = nums.length - 1;    
            while(low < high){
                int sum = nums[i] + nums[low] + nums[high];
                
                if(sum == 0){
                    res.add(Arrays.asList(nums[i], nums[low], nums[high]));
                    while(nums[low] == nums[low + 1] && low + 1 < high)
                        low++;
                    while(nums[high] == nums[high - 1] && high -1 > low)
                        high--;
                    low++;
                    high--;
                }
                
                else if(sum < 0)
                    low++;
                else
                    high--;     
            }
        }
        
        return res;
    }
}


// my first solution, but exceeded the memory and time limited.
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int newTarget = 0;
        int low = 0, high = nums.length - 1;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        
        while(high > low){
            
            newTarget = nums[high] + nums[low];
			
            if(newTarget >= 0){
                for(int i = low + 1; i <= 0; i++){
                    if(nums[i] + newTarget == 0){
                        res.add(Arrays.asList(nums[low], nums[i], nums[high]);
                    }
                    high--;     
                }
            }
            else{
                for(int i = high - 1; i > 0; i--){
                    if(nums[i] + newTarget == 0){
                        res.add(Arrays.asList(nums[low], nums[i], nums[high]);                        
                    }
                    low++;
                }
            }
        }
        return res;
    }
}