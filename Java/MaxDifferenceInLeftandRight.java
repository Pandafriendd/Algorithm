

// find the node with max difference in the total number of descendants in its left subtree and right subtree

public class MaxDifferenceInLeftandRight {
	
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
		a = the total number of nodes in my left subtree
		b = the total number of nodes in my right subtree
	2.
		globalMax = max(globalMax, | a - b |)
	3. 
		return to parent: a + b + 1 (the total number of nodes of myself)
	 */
	
	int getTotal(TreeNode root, int[] globalMax, TreeNode[] maxDiffNode) {
		if (root == null) {
			return 0;
		}
		
		int leftTotal = getTotal(root.left, globalMax, maxDiffNode);
		int rightTotal = getTotal(root.right, globalMax, maxDiffNode);
		// globalMax[0] = Math.max(globalMax[0], Math.abs(leftTotal - rightTotal));
		if (globalMax[0] < Math.abs(leftTotal - rightTotal)) {
			maxDiffNode[0] = root;
			globalMax[0] = Math.abs(leftTotal - rightTotal);
		}
		
		return leftTotal + rightTotal + 1;
	}
}
