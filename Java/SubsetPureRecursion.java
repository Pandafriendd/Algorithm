
import java.util.*;

public class SubsetPureRecursion {
	
	// pure recursion
	  public static List<String> subSets(String set) {
	    List<StringBuilder> sbList = new ArrayList<>();
	    if (set == null) {
	      return new ArrayList<>();
	    }
	    
	    subsetHelper(set, 0, sbList);
	    
	    List<String> res = new ArrayList<>();
	    for (StringBuilder sb : sbList) {
	      res.add(sb.toString());
	    }
	    return res;
	  }
	  
	  private static void subsetHelper(String set, int index, List<StringBuilder> sbList) {
	    
	    // base case: if set is "" (set.length() == 0), return ""
	    if (index == set.length()) {
	      StringBuilder sb = new StringBuilder();
	      sb.append("");
	      sbList.add(sb);
	      return;
	    }
	    
	    // recursive rule
	    subsetHelper(set, index + 1, sbList);
	    
	    int size = sbList.size();
	    for (int i = 0; i < size; i++) {
	      StringBuilder sb = new StringBuilder(sbList.get(i));
	      sb.append(set.charAt(index));
	      sbList.add(sb);
	      
	      // sbList.add(sbList.get(i).append(set.charAt(index))); // this is WRONG !!!!! since sbList.get(i) would should be modified
	    }
	  }
	  
	  public static void main(String[] args) {
		  System.out.println(subSets("abc"));
	  }
}
