
import java.util.*;

//backtracking
class permutations {
    public static void main(String[] args) {
    	permutations p = new permutations();
        //System.out.println(p.permute(new int[]{1, 3, 2}));
    	//p.permute(new int[] {1, 2, 3});
    	p.permute(new int[] {1, 2, 3});
    }
    
    // the BEST solution!!!
    public List<List<Integer>> permuteBYSwap(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        
        helper(nums, 0, res);
        return res;
    }
    
    private void helper(int[] nums, int index, List<List<Integer>> res) {
        // base case
        if (index == nums.length) {
            List<Integer> list = new ArrayList<>();  
            for (int i : nums) {
                list.add(i);
            }
            res.add(list);
            return;
        }
        
        // recursive rules
        for (int i = index; i < nums.length; i++) {
            swap(nums, index, i);
            helper(nums, index + 1, res);
            swap(nums, index, i);
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    
    
    
    
    public List<List<Integer>> permute(int[] nums) { 
    	   List<List<Integer>> list = new ArrayList<>();
    	   // Arrays.sort(nums); // not necessary
    	   helper(list, new ArrayList<Integer>(), nums);
    	   return list;
    	}

    private void helper(List<List<Integer>> list, List<Integer> tempList, int [] nums){
    	    System.out.print("*" + tempList.toString() + "*");
    		
    	    if(tempList.size() == nums.length){ //tempList length == nums.len
    	      System.out.print(" a result found! ");
    	      list.add(new ArrayList<>(tempList));
    	    } 
    	    else{
    	      for(int i = 0; i < nums.length; i++){ 
    	         if(tempList.contains(nums[i])) 
    	        	 continue; // element already exists, skip
    	         System.out.print(" add " + nums[i] + " ");
    	         tempList.add(nums[i]);
    	         helper(list, tempList, nums);
    	         System.out.print("remove:" + tempList.get(tempList.size() - 1) + " ");
    	         tempList.remove(tempList.size() - 1);  	         
    	      }
    	   }
    } 
    
    private static void print(int[] num) {
        for (int i = 0; i < num.length; i++) {
            System.out.print(num[i] + " ");
        }
        System.out.println();
    }
    
    /**
     * another solution
     * store whether a number is used in a boolean array
     * add used number to a list
     */
    class Solution { 
        boolean [] isUsed;
        int numLength;
        ArrayList<ArrayList<Integer>> output;
        ArrayList<Integer> al;

        public ArrayList<ArrayList<Integer>> permute(int[] num) {
            numLength = num.length;
            al = new ArrayList <Integer>();
            output = new ArrayList<ArrayList<Integer>>();
            isUsed = new boolean[num.length];
            doPermutation(0, num);
            return output;
        }
        public void doPermutation(int index, int[] num) {
            // base case
            if (index == numLength) {
                output.add((ArrayList<Integer>)al.clone());
                return;
            }
            for (int i = 0; i < numLength; i++) {
                if (!isUsed[i]) {
                    al.add(num[i]); // mark
                    isUsed[i] = true; // mark
                    doPermutation(index + 1, num);
                    isUsed[i] = false; // reset
                    al.remove(index); // reset
                }
            }
        }
    }
}
