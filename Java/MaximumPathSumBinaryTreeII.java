

/*
Given a binary tree in which each node contains an integer number. Find the maximum possible sum from any node to any node 
(the start node and the end node can be the same). 

Assumptions

​The root of the given binary tree is not null
Examples

   -1

  /    \

2      11

     /    \

    6    -14

one example of paths could be -14 -> 11 -> -1 -> 2

another example could be the node 11 itself

The maximum path sum in the above binary tree is 6 + 11 + (-1) + 2 = 18
 */


public class MaximumPathSumBinaryTreeII {
	public class TreeNode { 
		public int key; 
		public TreeNode left; 
		public TreeNode right;
		public int leftTotal;
		public TreeNode(int key) { 
			this.key = key; 
		}
	}
	
	/*
	the 3-steps:
	1. 
		a = max path sum of left subtree, must include root.left node (>0, otherwise a = 0)
		b = max path sum of right subtree, must include root.right node (>0, otherwise b = 0)
	2.
		globalMax = max(globalMax, root + a + b)
	3. 
		return to parent: root + max(a, b)
	 */
	// time: O(n), space: O(height)
	public int maxPathSum(TreeNode root) {
		int[] globalMax = new int[] {Integer.MIN_VALUE};
		helper(root, globalMax);
		return globalMax[0];
	}
	
	private int helper(TreeNode root, int[] globalMax) {
		// base case
		if (root == null) {
			return 0;
		}
		
		int leftMaxSum = helper(root.left, globalMax);
		leftMaxSum = leftMaxSum > 0 ? leftMaxSum : 0;
		int rightMaxSum = helper(root.right, globalMax);
		rightMaxSum = rightMaxSum > 0 ? rightMaxSum : 0;
		
		globalMax[0] = Math.max(globalMax[0], root.key + leftMaxSum + rightMaxSum);
		
		return Math.max(leftMaxSum, rightMaxSum) + root.key;
	}
}
