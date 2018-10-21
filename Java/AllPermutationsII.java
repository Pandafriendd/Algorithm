import java.util.*;

public class AllPermutationsII {
	public List<String> permutations(String set) {
	    // Write your solution here
	    List<String> res = new ArrayList<>();
	    if (set == null) {
	      return res;
	    }
	    char[] array = set.toCharArray();
	    
	    helper(array, 0, res);
	    return res;
	  }
	  
	  private void helper(char[] array, int index, List<String> res) {
	    // base case
	    if (index == array.length) {
	      StringBuilder sb = new StringBuilder();
	      for (Character c : array) {
	        sb.append(c);
	      }
	      res.add(sb.toString());
	      // res.add(new String(array));
	      return;
	    }
	    
	    // recursive rule
	    Set<Character> tempSet = new HashSet<>();  // used
	    for (int i = index; i < array.length; i++) {
	      if (!tempSet.contains(array[i])) {
	        tempSet.add(array[i]);
	        swap(array, index, i);
	        helper(array, index + 1, res);
	        swap(array, index, i);
	      }
	    }
	  }
	  
	  private void swap(char[] array, int i, int j) {
	    char t = array[i];
	    array[i] = array[j];
	    array[j] = t;
	  }
}
