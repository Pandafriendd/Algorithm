import java.util.*;


public class permutaionsII {
	public static void main(String[] args) {
		permutaionsII p = new permutaionsII();
		System.out.println(p.permuteUni(new int[] {1,1,2}));
	}
	
	
	// the LOF's solution
	// time: O(n!)  space: O(n^2)
	public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null) {
            return res;
        }
        
        helper(nums, res, 0);
        return res;
    }
    
    private void helper(int[] nums, List<List<Integer>> res, int index) {
        // base case
        if (index == nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int i : nums) {
                list.add(i);
            }
            res.add(list);
            return;
        }
        
        // recursive base
        Set<Integer> set = new HashSet<>();  // make sure the element that swaps to index position is not duplicated
        for (int i = index; i < nums.length; i++) {
            if (!set.contains(nums[i])) {
                set.add(nums[i]);
                swap(nums, index, i);
                helper(nums, res, index + 1);
                swap(nums, index, i);
            }
        }
    }
    
    
	
	//backtracking
	public List<List<Integer>> permuteUni(int[] nums) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if(nums == null || nums.length == 0)
			return res;
		
		Arrays.sort(nums);
		helper(nums, 0, res);
		
		return res;
	}
	
	private void helper(int[] nums, int pos, List<List<Integer>> res) {
		if(pos == nums.length) {
			List<Integer> row = new ArrayList<Integer>();
			for(int i : nums)
				row.add(i);
			res.add(row);
			return;
		}
		for(int i = pos; i < nums.length; i++) {
			//skip if we have duplicates of current element before i
			boolean skip = false;
			for(int j = pos; j < i; j++) {
				if(nums[j] == nums[i]) {
					skip = true;
					break;
				}		
			}
			if(skip)
				continue;
			swap(nums, pos, i);
			helper(nums, pos + 1, res);
			swap(nums, pos, i); //reset
		}
	}
	
	private void swap(int[] nums, int index1, int index2) {  //tricky way!!
		if(index1 == index2)
			return;
		nums[index1] = nums[index2] - nums[index1];
		nums[index2] = nums[index2] - nums[index1];
		nums[index1] = nums[index2] + nums[index1];
	}
	
	
	/**
     * Lexicography Order next permutation
     * Find the next permutation in lexicographic order.
     *http://en.wikipedia.org/wiki/Permutation#Generation_in_lexicographic_order
     */
    public static List<List<Integer>> permuteUniB(int[] num) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (num == null || num.length == 0) return res;
        Arrays.sort(num);
        List<Integer> row = new ArrayList<Integer>();
        for (int a : num) row.add(a);
        res.add(new ArrayList<Integer>(row)); // first permutation
        while (nextPermutation(row)) { // if there is next permutation
            res.add(new ArrayList<Integer>(row));
        }
        return res;
    } 
    
    /**
     * e.g.: 1234 -> 1243, 1243 -> 1324
     * Traverse backward to get 3
     * Then traverse forward to get furthest number bigger than 3
     * Swap these two digits and reverse from next to last
     */
    public static boolean nextPermutation(List<Integer> row) {
        int last = row.size() - 1;
        for (int pos = last - 1; pos >= 0; pos--) {
            if (row.get(pos) < row.get(pos + 1)) {
                int smallIdx = pos;
                int biggerIdx = pos + 1;
                for (int i = pos + 1; i <= last; i++) 
                    if (row.get(i) > row.get(pos)) biggerIdx = i;
                swap(row, smallIdx, biggerIdx);
                reverse(row, pos + 1, last);
                return true;
            }
        }
        return false;
    }
    
    public static void swap(List<Integer> row, int a, int b) {
        int t = row.get(a);
        row.set(a, row.get(b));
        row.set(b, t);
    }
    
    public static void reverse(List<Integer> row, int s, int e) {
        while (s < e) {
            swap(row, s, e);
            s++;
            e--;
        }
    }
}
