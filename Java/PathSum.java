/*
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \      \
7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 */


import java.util.*;
public class PathSum {
	
	public class TreeNode {
		      int val;
		      TreeNode left;
		      TreeNode right;
		      TreeNode(int x) { val = x; }
		  }
	
	
	/*
	O(N) time - Worst case, you check all elements and still find no path.
	O(height) space - height of the tree is the max space taken up at any point in the stack
	 */
	
	// my solution
	public boolean hasPathSum111(TreeNode root, int sum) {
        if(root == null) {
            return false;
        }
        
        HashSet<Integer> set = new HashSet<>();
        sumHelper(set, root, root.val);
        //System.out.println(set);
        return set.contains(sum);
    }
    
    private void sumHelper(HashSet<Integer> set, TreeNode node, int currentSum) {
        // base case
        if(node.left == null && node.right == null) {
            set.add(currentSum);
            return;
        }
        
        // recursion rules
        // now node.l != null || node.r != null
        if(node.left != null)
            sumHelper(set, node.left, currentSum + node.left.val);
        if(node.right != null)  // should be if !!!!
            sumHelper(set, node.right, currentSum + node.right.val);
    }
    
    
    // my solution based on other's opinion
    public boolean hasPathSum000(TreeNode root, int sum) {
        if(root == null) {
            return false;
        }
        
        return pathSumHelper(root, sum, 0);
    }
    
    private boolean pathSumHelper(TreeNode node, int sum, int currentSum) {
        if(node == null) {
            return false;
        }
        if(node.left == null && node.right == null) {
            return sum == currentSum + node.val;
        }
        
        // now node.l != null || node.r != null
        return pathSumHelper(node.left, sum, currentSum + node.val) || pathSumHelper(node.right, sum, currentSum + node.val);
    }
    
    
    
    // can be more elegant
    public boolean hasPathSum(TreeNode root, int sum) { // view sum as remaining sum
        if(root == null) 
        	return false;
    
        if(root.left == null && root.right == null && sum == root.val) 
        	return true;
    
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
    
    
    
    
    // other's solution
    public boolean hasPathSum1(TreeNode root, int sum) {
        if(root == null) return false;
        return pathSum(root, sum, 0);
    }
   private boolean pathSum(TreeNode root,int sum, int cursum){
     if(root == null) 
    	 return false;
     cursum += root.val;
     if(root.left == null && root.right == null && cursum == sum) 
    	 return true;
     return pathSum(root.left, sum, cursum) || pathSum(root.right, sum, cursum);
   }
}
