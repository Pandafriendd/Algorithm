
import java.util.*;
public class Subset {
	public static List<String> subSets(String set) {
	    // Write your solution here.
	    List<String> res = new ArrayList<>();
	    if(set == null) {
	      return res;
	    }
	    
	    subsetHelper(set, res, 0, new StringBuilder());
	    return res;
	  }
	  
	  private static void subsetHelper(String set, List<String> res, int index, StringBuilder sb) {
	    // base case
	    if(index == set.length()) {
	      res.add(sb.toString());
	      return;
	    }
	    
	    // recursive rule, at each level, determine the char at current index to be picked or not
	 
	    sb.append(set.charAt(index)); // pick the char at current index
	    subsetHelper(set, res, index + 1, sb);  
	    sb.deleteCharAt(sb.length() - 1);
	    
	    subsetHelper(set, res, index + 1, sb);  // not pick the char at current index
	    
	  }
	
	
	  
	/* 
eg: input a b c

           abc
     /      |    \
   a(b c)  b(c)  c()
   /   \     |
ab(c)  ac()  bc()
  |
abc

in this recursion tree, every node is a solution
Cn1 + Cn2 + ... + Cnn, so time is O(2^n). space is O(n)
*/
public List<List<Integer>> subsets(int[] nums) {
	List<List<Integer>> res = new ArrayList<>();
	List<Integer> temp =  new ArrayList<>();
	helper(res, temp, 0, nums);
	return res;
}

private void helper(List<List<Integer>> res, List<Integer> temp, int index, int[] nums) {
	res.add(new ArrayList<>(temp));

	/* base case, atucally we don't need base case. we need to call res.add first
	if (index == nums.length) {
		return;
	}
	res.add(new ArrayList<>(temp));
	*/
	
	for (int i = index; i < nums.length; i++) {
		temp.add(nums[i]);
		// helper(res, temp, index + 1, nums); !!!!! wrong!!!! i + 1
		helper(res, temp, i + 1, nums);
		temp.remove(temp.size() - 1);
	}
}
	
	
	
	
	
	  
	  public static void main(String[] args) {
		  System.out.println(Arrays.toString(subSets("abc").toArray()));
	  }
	    
	  
	  // using pure recursive
	  public List<String> subSets111(String set) {
		    List<String> res = new ArrayList<>();
		    if (set == null) {
		      return res;
		    }
		    
		    subsetHelper(set, 0, res);
		    
		    return res;
	}
		  
	private void subsetHelper(String set, int index, List<String> res) {
		    
		    // base case: if set is "" (set.length() == 0), return ""
		    if (index == set.length()) {
		      res.add("");
		      return;
		    }
		    
		    // recursive rule
		    subsetHelper(set, index + 1, res);
		    
		    int size = res.size();
		    for (int i = 0; i < size; i++) {
		      res.add(res.get(i) + set.charAt(index));   // not very efficient
		    }
	}	  
}
