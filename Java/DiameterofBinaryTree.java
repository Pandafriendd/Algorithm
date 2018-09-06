/*
 * Given a binary tree, you need to compute the length of the diameter of the tree. 
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree. 
 * This path may or may not pass through the root.
 * 
 * Example:
Given a binary tree 
          1
         / \
        2   3
       / \     
      4   5    
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Note: The length of path between two nodes is represented by the number of edges between them.
 */


import java.util.*;
public class DiameterofBinaryTree {
	
	public class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x){
			val = x;
		}
	}
	
	//For every node, length of longest path which pass it = MaxDepth of its left subtree + MaxDepth of its right subtree.
	
	//The tricky/smart part is that when calling maxDepth in diameterOfBinaryTree, it does NOT assign the value to max. 
	//The max is only updated in maxDepth function and the return value of maxDepth is only for calculating the depth/height during its own recursive calls.
	
	//Because we post-order traversing the tree, if leftTreeDiameter or rightTreeDiameter is greater, 
	//we have already stored the max value in diameter variable.
	
	// space o(height of tree), worse case o(n)
	
	int max = 0;
    
    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return max;
    }
    
    private int maxDepth(TreeNode root) {
        if (root == null) return 0;
        
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        
        max = Math.max(max, left + right);
        
        return Math.max(left, right) + 1;
    }
	
}
