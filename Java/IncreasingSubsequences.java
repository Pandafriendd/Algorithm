public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        helper(new LinkedList<Integer>(), 0, nums, res);
        return res; 
    }
    private void helper(LinkedList<Integer> list, int index, int[] nums, List<List<Integer>> res){
        if(list.size()>1) res.add(new LinkedList<Integer>(list));
        Set<Integer> used = new HashSet<>();
        for(int i = index; i<nums.length; i++){
            if(used.contains(nums[i])) continue;
            if(list.size()==0 || nums[i]>=list.peekLast()){
                used.add(nums[i]);
                list.add(nums[i]); 
                helper(list, i+1, nums, res);
                list.remove(list.size()-1);
            }
        }
    }


    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res =new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        
        helper(nums, 0, new ArrayList<>(), res);
        return res;
    }
    
    private void helper(int[] nums, int index, List<Integer> tempList, List<List<Integer>> res) {
        // base case
        if (tempList.size() >= 2) {
            res.add(new ArrayList<>(tempList));
        } 
        
        // recurisve rule
        Set<Integer> used = new HashSet<>();
        for (int i = index; i < nums.length; i++) {       
            if ((tempList.size() == 0 || nums[i] >= tempList.get(tempList.size() - 1)) && used.add(nums[i])) {
                tempList.add(nums[i]);
                helper(nums, index + 1, tempList, res);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        helper(new ArrayList<Integer>(), 0, nums, res);
        return res; 
    }
    private void helper(ArrayList<Integer> list, int index, int[] nums, List<List<Integer>> res){
        if(list.size()>1) res.add(new ArrayList<Integer>(list));
        Set<Integer> used = new HashSet<>();
        for(int i = index; i<nums.length; i++){
            if(used.contains(nums[i])) continue;
            if(list.size()==0 || nums[i]>=list.get(list.size() - 1)){
                used.add(nums[i]);
                list.add(nums[i]); 
                helper(list, i+1, nums, res);
                list.remove(list.size()-1);
            }
        }
    }
