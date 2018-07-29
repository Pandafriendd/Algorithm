/*
 * Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42
 */

import java.util.*;
public class BinaryTreeMaximumPathSum {
	
	public class TreeNode{
		int val = 0;
		TreeNode left;
		TreeNode right;
		TreeNode(int x){
			val = x;
		}
	}
	
	 

	    /**
	     * DFS
	     * Post order traversal
	     * Get path sum of left and right sub trees
	     * curMax of this level can be root's value v or v+left or v+right (only one branch)
	     * max sum can be biggest of prevMax, curMax, and left + right + root.val
	     */
		int max;
	    public int maxPathSum(TreeNode root) {
	        if (root == null) return 0;
	        max = root.val;
	        helper(root);
	        return max;
	    }

	    /**
	     * Post order traversal
	     */
	    int helper(TreeNode root) {
	        if (root == null) return 0;
	        int left = helper(root.left);
	        int right = helper(root.right);
	        
	        // calculate current max, only one branch
	        int curMax = Math.max(root.val, Math.max(left, right) + root.val);
	        // update max
	        max = Math.max(max, Math.max(curMax, left + right + root.val));
	        
	        return curMax; // note that return curMax here for result of upper level
	    }
	
}
