/*
et the list of keys in a given binary search tree in a given range[min, max] in ascending order, both min and max are inclusive.

Examples

        5

      /    \

    3        8

  /   \        \

 1     4        11

get the keys in [2, 5] in ascending order, result is  [3, 4, 5]

Corner Cases

What if there are no keys in the given range? Return an empty list in this case.
 */

import java.util.*;
public class GetKeysInBinarySearchTreeInGivenRange {
	
	  public class TreeNode {
	    public int key;
	    public TreeNode left;
	    public TreeNode right;
	    public TreeNode(int key) {
	      this.key = key;
	    }
	  }
	
	  
	  // O(n) 
	public List<Integer> getRange0(TreeNode root, int min, int max) {
	    // Write your solution here
	    List<Integer> res = new ArrayList<>();
	    if(root == null) {
	      return res;
	    }
	    
	    res.addAll(getRange(root.left, min, max));
	    if(root.key >= min && root.key <= max) {
	      res.add(root.key);
	    }
	    res.addAll(getRange(root.right, min, max));
	    
	    return res;
	  }
	  
	  // optimal solution
	  // O(height + # of nodes in the range of [min, max]), worst case O(n)
	  public List<Integer> getRange(TreeNode root, int min, int max) {
	    // Write your solution here
	    List<Integer> res = new ArrayList<>();
	    if(root == null) {
	      return res;
	    }
	    
	    //add some restriction to optimize inorder traversal
	    if(root.key > min) {
	    	res.addAll(getRange(root.left, min, max));
	    }
	
	    if(root.key >= min && root.key <= max) {
	      res.add(root.key);
	    }
	    
	    if(root.key < max) {
	    	res.addAll(getRange(root.right, min, max));
	    }
	    
	    return res;
	  }
}
