

/*
Given a binary tree in which each node contains an integer number. 
Find the maximum possible subpath sum
(both the starting and ending node of the subpath should be on the same path from root to one of the leaf nodes, 
and the subpath is allowed to contain only one node).

Assumptions

The root of given binary tree is not null
Examples

   -5

  /    \

2      11

     /    \

    6     14

           /

        -3

The maximum path sum is 11 + 14 = 25.
 */


public class MaximumPathSumBinaryTreeIII {
	
	public class TreeNode { 
		public int key; 
		public TreeNode left; 
		public TreeNode right; 
		public TreeNode(int key) { 
			this.key = key; 
		}
	}
	
	// recursion + DP, similar to largest subarray sum
	// time: O(n), space: O(height), n is the # of nodes in this tree
	public int maxPathSum(TreeNode root) {
	    int[] globalMax = new int[] {Integer.MIN_VALUE};
		helper(root, 0, globalMax);
		return globalMax[0];
	}
	
	private void helper(TreeNode root, int lastSum, int[] globalMax) {
		// base case
		if (root == null) {
			return;
		}
		
		if (lastSum > 0) {
			lastSum += root.key;
		} else { // lastSum <= 0
			lastSum = root.key;
		}
		
		globalMax[0] = Math.max(globalMax[0], lastSum);
		
		helper(root.left, lastSum, globalMax);
		helper(root.right, lastSum, globalMax);
	}
	
	
}
