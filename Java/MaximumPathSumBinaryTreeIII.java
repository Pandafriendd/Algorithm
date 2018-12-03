

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
	
	
	/*
	 The 3-steps solution
	 1. 
	 	max sum from left, including left, <= 0 ignore
	 	max sum from right, including right, <= 0 ignore
	 2. globalMax = max(globalaMax, root.key + max(left, right))
	 3. return parent: root.key + max(left, right)
	 */
	public int maxPathSum2(TreeNode root) {
	    int[] globalMax = new int[] {Integer.MIN_VALUE};
		helper(root, globalMax);
		return globalMax[0];
	}
	
	private int helper(TreeNode root, int[] globalMax) {
		if (root == null) {
			return 0;
		}
		
		int left = helper(root.left, globalMax);
		left = left > 0 ? left : 0;
		int right = helper(root.right, globalMax);
		right = right > 0 ? right : 0;
		
		int mySum = Math.max(left, right) + root.key;
		globalMax[0] = Math.max(globalMax[0], mySum);
		
		return mySum;
	}
	
	
	
}
