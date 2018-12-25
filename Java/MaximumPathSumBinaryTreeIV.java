
/*
Given a binary tree in which each node element contains a number. 
Find the maximum possible sum from one leaf node to another leaf node. 
The maximum sum path may or may not go through root. Expected time complexity is O(n).
 */

public class MaximumPathSumBinaryTreeIV {
	public class TreeNode {
		int key;
		TreeNode left;
		TreeNode right;
		TreeNode(int k) {
			key = k;
		}
	}
	
	/*
	the 3-steps:
	1. 
		from children:
		left = max SINGLE path sum of left subtree, must include the root.left node and a leaf node 
		right = max SINGLE path sum of right subtree, must include the root.right node a leaf node
	2.
		globalMax = max(globalMax, root + left + right)
	3. 
		return to parent: root + max(left, right)
	
	a tip: cover all cases:
	1          1          1           1
   / \        / \        / \         / \
  1   1    null  1      1  null   null  null
  
  time: O(n), space: O(height)
	 */
	public int maxPathSum(TreeNode root) {
		int[] globalMax = new int[] {Integer.MIN_VALUE};
		helper(root, globalMax);
		return globalMax[0];
	}
	
	private int helper(TreeNode root, int[] globalMax) {
		if (root == null) {
			return 0;
		}
		
		int left = helper(root.left, globalMax);
		int right = helper(root.right, globalMax);
		
		// !!! when a node has left subtree and right subtree or a node is leaf node, update globalMax if possible 
		if (root.left != null && root.right != null || root.left == null && root.right == null) {
			globalMax[0] = Math.max(globalMax[0], root.key + left + right);
		}
		
		return root.key + Math.max(left, right);
	}

}
