
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
	  
	  
	  public static void main(String[] args) {
		  System.out.println(Arrays.toString(subSets("abc").toArray()));
	  }
}
